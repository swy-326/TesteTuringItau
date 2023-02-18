package com.sungwon.testeturing.model.repository;

import com.sungwon.testeturing.model.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
}
