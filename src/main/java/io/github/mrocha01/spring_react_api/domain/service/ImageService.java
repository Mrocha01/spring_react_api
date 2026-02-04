package io.github.mrocha01.spring_react_api.domain.service;

import io.github.mrocha01.spring_react_api.domain.entity.Image;

import java.util.Optional;

public interface ImageService {
    Image save(Image image);

    Optional<Image> findById(String id);
}
