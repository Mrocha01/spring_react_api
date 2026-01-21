package io.github.mrocha01.spring_react_api.application.images;

import io.github.mrocha01.spring_react_api.domain.entity.Image;
import io.github.mrocha01.spring_react_api.domain.service.ImageService;
import io.github.mrocha01.spring_react_api.infra.repository.imageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final imageRepository imageRepository;

    @Override
    @Transactional // Garante Atomicidade no banco, em caso de erro = rollback
    public Image save(Image image) {
        return imageRepository.save(image);
    }
}
