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

import java.io.IOException;
import java.util.List;

@RestController // Controlador REST precisa dessa notation.
@RequestMapping("/v1/images") // URL
@Slf4j // annotation do Lombok que serve para criar automaticamente um logger da biblioteca
@RequiredArgsConstructor // Constructor Injection <---- @Autowired
public class imagesController {

    // injetando a ‘interface’, o spring busca pela classe de implementação
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("tags") List<String> tags
    ) throws IOException {
    log.info("Saving image: {}, size: {} ", file.getOriginalFilename(), file.getSize());

        // Faz o parse da ‘String’ para um Objeto MediaType, validando se a estrutura do MIME type está correta.
        MediaType type = MediaType.valueOf(file.getContentType());

    Image image = Image.builder()
            .name(name)
            .tags(String.join(",", tags))
            .size(file.getSize())
            .extension(ImageExtension.valueOf(type))
            .file(file.getBytes())
            .build();

    imageService.save(image);

    return ResponseEntity.status(201).body("Image salva com sucesso!");
    }
}
