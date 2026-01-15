package io.github.mrocha01.spring_react_api.infra.repository;

import io.github.mrocha01.spring_react_api.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

// representa a camada de acesso ao banco
public interface imageRepository extends JpaRepository<Image, String> {
}
