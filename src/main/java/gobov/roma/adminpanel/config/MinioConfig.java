package gobov.roma.adminpanel.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint("http://minio-server:9000")
                .credentials("minioadmin", "miniopassword")
                .build();
    }
}