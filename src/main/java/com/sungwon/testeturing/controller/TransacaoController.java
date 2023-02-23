package com.sungwon.testeturing.controller;

import com.sungwon.testeturing.model.dto.PixDTO;
import com.sungwon.testeturing.model.dto.TedDocDTO;
import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.model.entity.Transacao;
import com.sungwon.testeturing.model.enums.TipoTransacao;
import com.sungwon.testeturing.security.CustomUserDetails;
import com.sungwon.testeturing.service.ContaService;
import com.sungwon.testeturing.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping(value = "/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private ContaService contaService;

    @GetMapping("/pix")
    public String novaTransacaoPix(Model model, @RequestParam Long id, @AuthenticationPrincipal CustomUserDetails userDetails){
        model.addAttribute("transacao", new PixDTO());
        model.addAttribute("contaId", id);
        return "transacao/pix";
    }

    @GetMapping("/ted")
    public String novaTransacaoTed(Model model, @RequestParam Long id){
        model.addAttribute("transacao", new TedDocDTO());
        return "transacao/ted";
    }

    @GetMapping("/doc")
    public String novaTransacaoDoc(Model model, @RequestParam Long id){
        model.addAttribute("transacao", new TedDocDTO());
        return "transacao/doc";
    }

    @PostMapping("/pix")
    public String processandoNovaTransacaoPix(PixDTO pixDTO, @RequestParam Long id,
                                              @AuthenticationPrincipal CustomUserDetails userDetails,
                                              Model model){

        Transacao transacao = new Transacao();
        transacao.setValorTransacao(pixDTO.getValorTransacao());
        transacao.setTipoTransacao(TipoTransacao.PIX);

        System.out.println("==========================" + id + "============================");

        Optional<Conta> contaOrigemOptional = contaService.findById(id);
        /*
        if (contaOrigemOptional.isEmpty()){
            // return erro
        }
        */
        Conta contaOrigem = contaOrigemOptional.get();
        transacao.setContaOrigem(contaOrigem);

        Optional<Conta> contaDestinoOptional = contaService.findByChavePix(pixDTO.getChavePix());
        /*
        if (contaDestinoOptional.isEmpty()){
            // return erro
        }
        */
        Conta contaDestino = contaDestinoOptional.get();
        transacao.setContaDestino(contaDestino);

        // invalidar transacao pra mesma conta

        transacaoService.save(transacao);

        // atualizar o saldo das contas
        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(pixDTO.getValorTransacao()));
        contaDestino.setSaldo(contaDestino.getSaldo().add(pixDTO.getValorTransacao()));
        contaService.update(contaOrigem);
        contaService.update(contaDestino);

        // redirecionar para pagina de sucesso
        model.addAttribute("saldoEmissor", String.format("%,.2f", contaOrigem.getSaldo()));
        model.addAttribute("saldoReceptor", String.format("%,.2f", contaDestino.getSaldo()));
        return "transacao/sucesso";
    }

}
