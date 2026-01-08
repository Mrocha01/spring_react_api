package io.github.mrocha01.spring_react_api.application.images;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController // Controlador REST precisa dessa notation.
@RequestMapping("/v1/images") // URL
@Slf4j // annotation do Lombok que serve para criar automaticamente um logger da biblioteca
public class imagesController {

    @PostMapping
    public ResponseEntity<?> save(
            @RequestParam("file")MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("tags") List<String> tags
    ) {
    log.info("Saving image: {}, size: {} ", file.getOriginalFilename(), file.getSize());
    log.info("Nome definido: {}", name);
    log.info("Tags definido: {}", tags);
    return ResponseEntity.ok().build();
    }
}
