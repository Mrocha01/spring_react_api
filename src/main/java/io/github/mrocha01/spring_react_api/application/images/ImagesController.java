package io.github.mrocha01.spring_react_api.application.images;

import io.github.mrocha01.spring_react_api.domain.entity.Image;
import io.github.mrocha01.spring_react_api.domain.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController // Controlador REST precisa dessa notation.
@RequestMapping("/v1/images") // URL
@Slf4j // annotation do Lombok que serve para criar automaticamente um logger da biblioteca
@RequiredArgsConstructor // Constructor Injection <---- @Autowired
public class ImagesController {

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

    // Created passa a URI como URL nos Headers da resposta a requisição Post
    return ResponseEntity.created(imageUri).body("Imagem salva com sucesso!");
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        var possibleImage = imageService.findById(id);

        if (possibleImage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var imagem = possibleImage.get();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(imagem.getExtension().getMediaType());
        headers.setContentLength(imagem.getSize());
        headers.setContentDisposition(ContentDisposition
                .inline()
                .filename(imagem.getFileName())
                .build());

        return new ResponseEntity<>(imagem.getFile(), headers, HttpStatus.OK);
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
