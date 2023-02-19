package com.sungwon.testeturing.controller;

import com.sungwon.testeturing.model.dto.TransacaoDTO;
import com.sungwon.testeturing.model.dto.TransacaoNovaDTO;
import com.sungwon.testeturing.model.entity.Transacao;
import com.sungwon.testeturing.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;
    /*
    @GetMapping("/")
    public String novaTransacao(Model model){

        return "transacao/index";
    }

    // processar nova transferencia
    @PostMapping("/processando")
    public String novaTransacao(TransacaoNovaDTO transacaoNovaDTO){

        Transacao transacao = transacaoNovaDTO.toTransacaoEntity();

        // buscar id da conta destino
        // buscar id da conta origem
        // salvar id da conta origem e destino na transacao
        // transacaoService.save();

        return "transacao/sucesso";
    }

*/
}
