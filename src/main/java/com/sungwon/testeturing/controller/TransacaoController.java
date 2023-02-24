package com.sungwon.testeturing.controller;

import com.sungwon.testeturing.model.dto.PixDTO;
import com.sungwon.testeturing.model.dto.TedDocDTO;
import com.sungwon.testeturing.model.entity.Conta;
import com.sungwon.testeturing.model.entity.Transacao;
import com.sungwon.testeturing.model.enums.TipoTransacao;
import com.sungwon.testeturing.security.CustomUserDetails;
import com.sungwon.testeturing.service.ContaService;
import com.sungwon.testeturing.service.TransacaoService;
import com.sungwon.testeturing.validator.PixDTOValidator;
import com.sungwon.testeturing.validator.TedDocDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping(value = "/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private ContaService contaService;

    @Autowired
    private PixDTOValidator pixDTOValidator;

    @Autowired
    private TedDocDTOValidator tedDocDTOValidator;


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
        atualizaSaldoContas(contaOrigem, contaDestino, pixDTO.getValorTransacao());

        // redirecionar para pagina de sucesso
        model.addAttribute("saldoEmissor", String.format("%,.2f", contaOrigem.getSaldo()));
        model.addAttribute("saldoReceptor", String.format("%,.2f", contaDestino.getSaldo()));
        return "transacao/sucesso";
    }

    @GetMapping("/ted")
    public String novaTransacaoTed(Model model, @RequestParam Long id, @AuthenticationPrincipal CustomUserDetails userDetails){
        model.addAttribute("transacao", new TedDocDTO());
        model.addAttribute("contaId", id);
        return "transacao/ted";
    }

    @PostMapping("/ted")
    public String processandoNovaTransacaoPix(@ModelAttribute("transacao") @Valid TedDocDTO tedDTO, BindingResult bindingResult,
                                              @RequestParam Long id,
                                              @AuthenticationPrincipal CustomUserDetails userDetails,
                                              Model model) {

        tedDTO.setIdContaOrigem(id);
        tedDTO.setTipoTransacao(TipoTransacao.TED);
        tedDocDTOValidator.validate(tedDTO, bindingResult);
        if (bindingResult.hasErrors())
            return "transacao/ted";


        Transacao transacao = new Transacao();
        transacao.setValorTransacao(tedDTO.getValorTransacao());
        transacao.setTipoTransacao(TipoTransacao.TED);

        Conta contaOrigem = contaService.findById(id).get();
        transacao.setContaOrigem(contaOrigem);

        Conta contaDestino = contaService.findByBancoAgenciaConta(
                tedDTO.getNroBanco(), tedDTO.getNroAgencia(), tedDTO.getNroConta()
        ).get();
        transacao.setContaDestino(contaDestino);

        transacaoService.save(transacao);

        // atualizar o saldo das contas
        atualizaSaldoContas(contaOrigem, contaDestino, tedDTO.getValorTransacao());

        // redirecionar para pagina de sucesso
        model.addAttribute("saldoEmissor", String.format("%,.2f", contaOrigem.getSaldo()));
        model.addAttribute("saldoReceptor", String.format("%,.2f", contaDestino.getSaldo()));
        return "transacao/sucesso";
    }



    @GetMapping("/doc")
    public String novaTransacaoDoc(Model model, @RequestParam Long id, @AuthenticationPrincipal CustomUserDetails userDetails){
        model.addAttribute("transacao", new TedDocDTO());
        model.addAttribute("contaId", id);
        return "transacao/doc";
    }

    @PostMapping("/doc")
    public String processandoNovaTransacaoDoc(@ModelAttribute("transacao") @Valid TedDocDTO docDTO, BindingResult bindingResult,
                                              @RequestParam Long id,
                                              @AuthenticationPrincipal CustomUserDetails userDetails,
                                              Model model) {

        docDTO.setIdContaOrigem(id);
        docDTO.setTipoTransacao(TipoTransacao.DOC);
        tedDocDTOValidator.validate(docDTO, bindingResult);
        if (bindingResult.hasErrors())
            return "transacao/doc";


        Transacao transacao = new Transacao();
        transacao.setValorTransacao(docDTO.getValorTransacao());
        transacao.setTipoTransacao(TipoTransacao.TED);

        Conta contaOrigem = contaService.findById(id).get();
        transacao.setContaOrigem(contaOrigem);

        Conta contaDestino = contaService.findByBancoAgenciaConta(
                docDTO.getNroBanco(), docDTO.getNroAgencia(), docDTO.getNroConta()
        ).get();
        transacao.setContaDestino(contaDestino);

        transacaoService.save(transacao);

        // atualizar o saldo das contas
        atualizaSaldoContas(contaOrigem, contaDestino, docDTO.getValorTransacao());

        // redirecionar para pagina de sucesso
        model.addAttribute("saldoEmissor", String.format("%,.2f", contaOrigem.getSaldo()));
        model.addAttribute("saldoReceptor", String.format("%,.2f", contaDestino.getSaldo()));
        return "transacao/sucesso";
    }


    private void atualizaSaldoContas(Conta contaOrigem, Conta contaDestino, BigDecimal valor){
        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
        contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
        contaService.update(contaOrigem);
        contaService.update(contaDestino);
    }
}
