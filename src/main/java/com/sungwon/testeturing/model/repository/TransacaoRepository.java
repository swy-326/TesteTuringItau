package com.sungwon.testeturing.model.repository;

import com.sungwon.testeturing.model.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
