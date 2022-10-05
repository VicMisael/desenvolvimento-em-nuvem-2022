package br.ufc.nuvem.patrimoniomanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bem")
@Entity
public class Bem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBem;

    @Column(name = "name")
    private String name;

    @Column(name = "localizacao")
    private String localizacao;

    @Column(name = "dirImageBem")
    private String dirImagemBem;

    @Column(name = "bemUrl")
    private String bemUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Usuario usuario;

    @Transient
    private Long usuarioId;

    @PostLoad
    void setUsuarioId() {
        usuarioId = usuario.getCodigoUsuario();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Bem bem = (Bem) o;
        return idBem != null && Objects.equals(idBem, bem.idBem);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
