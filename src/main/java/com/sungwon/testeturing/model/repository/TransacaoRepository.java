package com.sungwon.testeturing.model.repository;

import com.sungwon.testeturing.model.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    @Query(value = "SELECT * FROM tb_transacao" +
                   "WHERE conta_origem_id := contaOrigem", nativeQuery = true)
    List<Transacao> findByContaOrigem(@Param("contaOrigem") String contaOrigem);

    @Query(value = "SELECT * FROM tb_transacao" +
            "WHERE conta_destino_id := contaDestino", nativeQuery = true)
    List<Transacao> findByContaDestino(@Param("contaDestino") String contaDestino);

    @Query(value = "SELECT * FROM tb_transacao" +
            "WHERE (conta_destino_id := conta) OR (conta_origem_id := conta)", nativeQuery = true)
    List<Transacao> findAllByConta(@Param("conta") String conta);
}
