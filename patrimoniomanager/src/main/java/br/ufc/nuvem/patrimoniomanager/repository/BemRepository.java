package br.ufc.nuvem.patrimoniomanager.repository;

import br.ufc.nuvem.patrimoniomanager.model.Bem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table
public interface BemRepository extends JpaRepository<Bem, Long> {

    List<Bem> deleteBemsByUsuario_CodigoUsuario(Long id);

    List<Bem> findBemsByNameContainingIgnoreCase(String name);

    List<Bem> findBemsByUsuario_CodigoUsuario(Long userId);

    List<Bem> findBemsByLocalizacaoContainingIgnoreCase(String localizacao);

    List<Bem> findBemsByLocalizacaoContainingIgnoreCaseAndNameContainingIgnoreCase(String localizacao, String name);

    List<Bem> findBemsByUsuario_CodigoUsuarioAndNameContainingIgnoreCase(Long id, String name);

    List<Bem> findBemsByUsuario_CodigoUsuarioAndLocalizacaoIgnoreCase(Long id, String localizacao);

    List<Bem> findBemsByUsuario_CodigoUsuarioAndNameContainingIgnoreCaseAndLocalizacaoContainingIgnoreCase(Long id, String name, String localizacao);

}
