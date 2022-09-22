package br.ufc.nuvem.patrimoniomanager.model;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codArquivo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "dirImageBem")
    private String dirImagemBem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Usuario usuario;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Bem bem = (Bem) o;
        return codArquivo != null && Objects.equals(codArquivo, bem.codArquivo);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
