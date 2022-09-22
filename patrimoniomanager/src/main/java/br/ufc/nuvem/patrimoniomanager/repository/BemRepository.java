package br.ufc.nuvem.patrimoniomanager.repository;

import br.ufc.nuvem.patrimoniomanager.model.Bem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BemRepository extends JpaRepository<Bem,Long> {
}
