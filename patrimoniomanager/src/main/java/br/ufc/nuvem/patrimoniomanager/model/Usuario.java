package br.ufc.nuvem.patrimoniomanager.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Usuario {
    @Id
    @NonNull
    Long id;

    @Column(name = "name",unique = true,nullable = false)
    String nome;

    @Column(name = "imageUsuario",unique = true,nullable = true)
    String imgUsuario;

    @OneToMany(targetEntity = Bem.class, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    List<Bem> bens = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Usuario usuario = (Usuario) o;
        return id != null && Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
