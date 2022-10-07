package br.ufc.nuvem.patrimoniomanager.repository;

import br.ufc.nuvem.patrimoniomanager.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Table;

@Table
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    Usuario findByIdentificacao(String identificacao);

    boolean existsUsuarioByIdentificacao(String identificacao);
}
