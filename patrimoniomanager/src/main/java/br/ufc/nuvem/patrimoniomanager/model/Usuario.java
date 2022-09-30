package br.ufc.nuvem.patrimoniomanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long codigoUsuario;

    @Column(name = "nomeCompleto", nullable = false)
    String nome;

    @Column(name = "email", nullable = false)
    String email;

    @Column(name = "indentificacao", unique = true)
    String identificacao;


    @Column(name = "folderName")
    private String folderName;

    @Column(name = "senha")
    @JsonIgnore
    String senha;

    @Column
    Role role;

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
