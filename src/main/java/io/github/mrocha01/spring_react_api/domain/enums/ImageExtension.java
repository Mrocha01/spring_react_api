package io.github.mrocha01.spring_react_api.domain.enums;

import lombok.Getter;
import org.springframework.http.MediaType;

import java.util.Arrays;

@Getter
public enum ImageExtension {
    PNG(MediaType.IMAGE_PNG),
    GIF(MediaType.IMAGE_GIF),
    JPEG(MediaType.IMAGE_JPEG);

    private MediaType mediaType;

    ImageExtension(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public static ImageExtension valueOf(MediaType mediaType) {
        return Arrays.stream(ImageExtension.values())
                .filter(extension -> extension.mediaType.equals(mediaType))
                .findFirst()
                .orElse(null);
    }
}
