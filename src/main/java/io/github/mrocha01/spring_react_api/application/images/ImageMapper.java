package io.github.mrocha01.spring_react_api.application.images;

import io.github.mrocha01.spring_react_api.domain.entity.Image;
import io.github.mrocha01.spring_react_api.domain.enums.ImageExtension;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class ImageMapper {

    public Image mapToImage(MultipartFile file, String name, List<String> tags) throws IOException {

        // Faz o parse da ‘String’ para um Objeto MediaType, validando se a estrutura do MIME type está correta.
        MediaType type = MediaType.valueOf(file.getContentType());

        return Image.builder()
                .name(name)
                .tags(String.join(",", tags))
                .size(file.getSize())
                .extension(ImageExtension.valueOf(type))
                .file(file.getBytes())
                .build();
    }

    public ImageDTO mapToImageDTO(Image image, String url) {
        return ImageDTO.builder()
                .url(url)
                .extension(image.getExtension().name())
                .name(image.getName())
                .size(image.getSize())
                .uploadDate(image.getUploadDate().toLocalDate().atStartOfDay())
                .build();
    }
}
