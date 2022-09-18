package br.ufc.nuvem.patrimoniomanager.model;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "bem")
@Entity
public class Bem {
    @Id
    Long id;

    @Column(name = "nome")
    String nome;

    @Enumerated(EnumType.ORDINAL)
    Tipo tipo;

    @Column(name = "dirImageBem")
    String dirImagemBem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    Pessoa pessoa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Bem bem = (Bem) o;
        return id != null && Objects.equals(id, bem.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
