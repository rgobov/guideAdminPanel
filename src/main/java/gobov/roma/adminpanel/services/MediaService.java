package gobov.roma.adminpanel.services;

import gobov.roma.adminpanel.model.Media;
import gobov.roma.adminpanel.model.PointOfInterest;
import gobov.roma.adminpanel.repository.media.MediaRepository;
import gobov.roma.adminpanel.repository.point.PointOfInterestRepository;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class MediaService {
    private final MediaRepository mediaRepository;
    private final PointOfInterestRepository poiRepository;
    private final MinioClient minioClient;

    public MediaService(MediaRepository mediaRepository, PointOfInterestRepository poiRepository, MinioClient minioClient) {
        this.mediaRepository = mediaRepository;
        this.poiRepository = poiRepository;
        this.minioClient = minioClient;
    }

    public Media uploadMedia(Long poiId, MultipartFile file, String type) {
        PointOfInterest poi = poiRepository.findById(poiId)
                .orElseThrow(() -> new IllegalArgumentException("Point of Interest not found"));

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String bucketName = "media-bucket";

        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file to MinIO", e);
        }

        Media media = new Media();
        media.setPointOfInterest(poi);
        media.setType(type);
        media.setUrl("http://minio-server:9000/" + bucketName + "/" + fileName);
        media.setSize(file.getSize());

        return mediaRepository.save(media);
    }
}