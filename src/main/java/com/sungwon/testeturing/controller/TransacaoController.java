package com.sungwon.testeturing.controller;

import com.sungwon.testeturing.model.dto.PixDTO;
import com.sungwon.testeturing.model.dto.TedDocDTO;
import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.model.entity.Transacao;
import com.sungwon.testeturing.model.enums.TipoTransacao;
import com.sungwon.testeturing.security.CustomUserDetails;
import com.sungwon.testeturing.service.ContaService;
import com.sungwon.testeturing.service.TransacaoService;
import com.sungwon.testeturing.utils.PixDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private ContaService contaService;

    @Autowired
    private PixDTOValidator pixDTOValidator;


    @GetMapping("/pix")
    public String novaTransacaoPix(Model model, @RequestParam Long id, @AuthenticationPrincipal CustomUserDetails userDetails){
        model.addAttribute("transacao", new PixDTO());
        model.addAttribute("contaId", id);
        return "transacao/pix";
    }

    @PostMapping("/pix")
    public String processandoNovaTransacaoPix(@ModelAttribute("transacao") @Valid PixDTO pixDTO, BindingResult bindingResult,
                                              @RequestParam Long id,
                                              @AuthenticationPrincipal CustomUserDetails userDetails,
                                              Model model){

        pixDTO.setIdContaOrigem(id);
        pixDTOValidator.validate(pixDTO, bindingResult);
        if (bindingResult.hasErrors())
            return "transacao/pix";

        Transacao transacao = new Transacao();
        transacao.setValorTransacao(pixDTO.getValorTransacao());
        transacao.setTipoTransacao(TipoTransacao.PIX);


        Conta contaOrigem = contaService.findById(id).get();
        transacao.setContaOrigem(contaOrigem);

        Conta contaDestino = contaService.findByChavePix(pixDTO.getChavePix()).get();
        transacao.setContaDestino(contaDestino);

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
}
