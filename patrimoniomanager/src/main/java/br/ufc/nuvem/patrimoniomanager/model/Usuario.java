package br.ufc.nuvem.patrimoniomanager.model;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
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
    Long codigoUsuario;

    @Column(name = "nomeCompleto",nullable = false)
    String nome;

    @Column(name = "email",nullable = false)
    String email;

    @Column(name="indentificacao",unique = true)
    String identificacao;

    @Column(name="senha")
    String senha;

    @OneToMany(targetEntity = Bem.class, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    List<Bem> bens = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Usuario usuario = (Usuario) o;
        return codigoUsuario != null && Objects.equals(codigoUsuario, usuario.codigoUsuario);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
