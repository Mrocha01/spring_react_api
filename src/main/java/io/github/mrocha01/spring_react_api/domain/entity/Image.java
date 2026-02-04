package io.github.mrocha01.spring_react_api.domain.entity;

import io.github.mrocha01.spring_react_api.domain.enums.ImageExtension;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity // essa classe representa uma tabela no banco
@Table // nome e regras estruturais (UniqueConstraint)
@EntityListeners(AuditingEntityListener.class) // Permitir que o Spring preencha automaticamente campos de auditoria, como datas de criação.
@Data // gera getters setters toString equals hashCode
@NoArgsConstructor // obrigatório para o hibernate instanciar a entidade
@AllArgsConstructor // construtor cm todso os campos
@Builder // facilita criação de objetos
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long size;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ImageExtension extension;

    @Column(nullable = false)
    @CreatedDate // LocalDateTime.now()
    private LocalDateTime uploadDate;

    @Column(nullable = false)
    private String tags;

    @Column(nullable = false)
    @Lob // “Esse campo pode armazenar uma abundância de dados = Large Object
    private byte[] file;

    public String getFileName() {
        return getName().concat(".").concat(getExtension().name());
    }
}
