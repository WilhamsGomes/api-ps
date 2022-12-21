package br.com.banco.repositories.transferencia;

import br.com.banco.models.ContaModal;
import br.com.banco.models.TransferenciaModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<TransferenciaModal, Long> {

    @Query(value = "select u from transferencia u where conta_id=?1")
    List<TransferenciaModal> findByConta(Long conta_id);

    @Query(value = "select u from transferencia u where upper(trim(u.nome_operador_transacao)) like %?1%")
    List<TransferenciaModal> findByOperador(String nome_operador_transacao);

    /*
    @Query(value = "select u from transferencia u where conta_id=?1 and upper(trim(u.nome_operador_transacao)) like %?2%")
    List<TransferenciaModal> findByContaAndOperador(Long conta_id,String nome_operador_transacao);
     */

    @Query(value = "select u from transferencia u where data_transferencia BETWEEN ?1 and ?2")
    List<TransferenciaModal> findByData(LocalDate dataInicio, LocalDate  dataFinal);

    @Query(value = "select u from transferencia u where (data_transferencia BETWEEN ?1 and ?2) and upper(trim(u.nome_operador_transacao)) like %?3%")
    List<TransferenciaModal> findByDataOperador(LocalDate dataInicio, LocalDate  dataFinal, String nome_operador_transacao);

}
