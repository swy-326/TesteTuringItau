package com.sungwon.testeturing.model.repository;

import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.model.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query(value = "SELECT * FROM tb_conta " +
            "WHERE usuario_ref = :usuarioRef", nativeQuery = true)
    List<Conta> findByUsuarioRef(@Param("usuarioRef") String usuarioRef);


    @Query(value = "SELECT * FROM tb_conta " +
            "WHERE chave_pix = :chavePix", nativeQuery = true)
    Optional<Conta> findByChavePix(@Param("chavePix") String chavePix);

    @Query(value = "SELECT * FROM tb_conta " +
            "WHERE (nro_banco = :nroBanco) AND (nro_agencia = :nroAgencia) AND (nro_conta = :nroConta)", nativeQuery = true)
    Optional<Conta> findByBancoAgenciaConta(
            @Param("nroBanco") String nroBanco,
            @Param("nroAgencia") String nroAgencia,
            @Param("nroConta") String nroConta);
}