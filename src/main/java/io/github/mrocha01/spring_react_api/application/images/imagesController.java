package io.github.mrocha01.spring_react_api.application.images;

import io.github.mrocha01.spring_react_api.domain.entity.Image;
import io.github.mrocha01.spring_react_api.domain.enums.ImageExtension;
import io.github.mrocha01.spring_react_api.domain.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController // Controlador REST precisa dessa notation.
@RequestMapping("/v1/images") // URL
@Slf4j // annotation do Lombok que serve para criar automaticamente um logger da biblioteca
@RequiredArgsConstructor // Constructor Injection <---- @Autowired
public class imagesController {

    // injetando a ‘interface’, o spring busca pela classe de implementação
    private final ImageService imageService;
    private final ImageMapper imageMapper;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("tags") List<String> tags
    ) throws IOException {
    log.info("Saving image: {}, size: {} ", file.getOriginalFilename(), file.getSize());

    Image image = imageMapper.mapToImage(file, name, tags);
    Image savedImage = imageService.save(image);
    URI imageUri = buildImageURL(savedImage);

    return ResponseEntity.created(imageUri).body("Imagem salva com sucesso!");
    }

    // localhost:8080/v1/images/
    private URI buildImageURL(Image image) {
        String imagePath = "/" + image.getId();
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path(imagePath)
                .build()
                .toUri();
    }
}
