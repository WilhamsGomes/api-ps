package br.com.banco.repositories.user;

import br.com.banco.models.ContaModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<ContaModal, Long> {

    @Query(value = "select u from conta u where upper(trim(u.nome_responsavel)) like %?1%")
    List<ContaModal> buscarPorNome(String nome);

}
