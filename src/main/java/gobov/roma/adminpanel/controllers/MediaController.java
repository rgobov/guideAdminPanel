package gobov.roma.adminpanel.controllers;

import gobov.roma.adminpanel.model.Media;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaController {
    private final gobov.roma.adminpanel.services.MediaService mediaService;

    @PostMapping("/upload")
    public ResponseEntity<Media> uploadMedia(
            @RequestParam("file") MultipartFile file,
            @RequestParam Long poiId,
            @RequestParam String type) {
        Media media = mediaService.uploadMedia(poiId, file, type);
        return ResponseEntity.ok(media);
    }
}