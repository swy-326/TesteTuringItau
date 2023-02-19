package com.sungwon.testeturing.model.repository;

import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.model.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query(value = "SELECT * FROM tb_conta " +
            "WHERE usuario_ref = :usuarioRef", nativeQuery = true)
    List<Conta> findByUsuarioRef(@Param("usuarioRef") String usuarioRef);
}
