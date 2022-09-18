package br.ufc.nuvem.patrimoniomanager.repository;

import br.ufc.nuvem.patrimoniomanager.model.Pessoa;
import org.springframework.data.repository.CrudRepository;

public interface PessoaRepository extends CrudRepository<Pessoa,Long> {
}
