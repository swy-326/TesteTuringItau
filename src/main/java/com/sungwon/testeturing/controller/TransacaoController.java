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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;


@Slf4j
@Controller
@RequestMapping(value = "/transacao")
public class TransacaoController {

    private TransacaoService transacaoService;
    private ContaService contaService;
    private PixDTOValidator pixDTOValidator;
    private TedDocDTOValidator tedDocDTOValidator;

    @Autowired
    public TransacaoController(TransacaoService transacaoService, ContaService contaService, PixDTOValidator pixDTOValidator, TedDocDTOValidator tedDocDTOValidator){
        this.transacaoService = transacaoService;
        this.contaService = contaService;
        this.pixDTOValidator = pixDTOValidator;
        this.tedDocDTOValidator = tedDocDTOValidator;
    }



    @GetMapping("/pix")
    public String novaTransacaoPix(Model model, @RequestParam Long id, @AuthenticationPrincipal CustomUserDetails userDetails){
        model.addAttribute("transacao", new PixDTO());
        model.addAttribute("contaId", id);
        log.info("Carregando pagina de PIX");

        return "transacao/pix";
    }

    @PostMapping("/pix")
    public String processandoNovaTransacaoPix(@ModelAttribute("transacao") @Valid PixDTO pixDTO, BindingResult bindingResult,
                                              @RequestParam Long id,
                                              @AuthenticationPrincipal CustomUserDetails userDetails,
                                              Model model){

        pixDTO.setIdContaOrigem(id);
        pixDTOValidator.validate(pixDTO, bindingResult);
        if (bindingResult.hasErrors()){
            log.warn("Erro(s) no dado fornecido pelo usuario");
            return "transacao/pix";
        }


        // criar nova transacao e salvar no bd
        Transacao transacao = new Transacao();
        transacao.setValorTransacao(pixDTO.getValorTransacao());
        transacao.setTipoTransacao(TipoTransacao.PIX);

        Conta contaOrigem = contaService.findById(id).get();
        transacao.setContaOrigem(contaOrigem);

        Conta contaDestino = contaService.findByChavePix(pixDTO.getChavePix()).get();
        transacao.setContaDestino(contaDestino);
        transacaoService.save(transacao);

        log.info("Transacao salva com sucesso");

        // atualizar o saldo das contas
        atualizaSaldoContas(contaOrigem, contaDestino, pixDTO.getValorTransacao());
        log.info("Saldo da conta atualizada com sucesso");

        // redirecionar para a pagina de sucesso
        model.addAttribute("saldoEmissor", String.format("%,.2f", contaOrigem.getSaldo()));
        model.addAttribute("saldoReceptor", String.format("%,.2f", contaDestino.getSaldo()));
        log.info("Redirecionando para a pagina de sucesso da transacao PIX");

        return "transacao/sucesso";
    }

    @GetMapping("/ted")
    public String novaTransacaoTed(Model model, @RequestParam Long id, @AuthenticationPrincipal CustomUserDetails userDetails){
        model.addAttribute("transacao", new TedDocDTO());
        model.addAttribute("contaId", id);
        log.info("Carregando a pagina de TED");

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
        if (bindingResult.hasErrors()){
            log.warn("Erro(s) no dado fornecido pelo usuario");
            return "transacao/ted";
        }


        // criar nova transacao e salvar no bd
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
        log.info("Transacao salva com sucesso");

        // atualizar o saldo das contas
        atualizaSaldoContas(contaOrigem, contaDestino, tedDTO.getValorTransacao());
        log.info("Saldo da conta atualizada com sucesso");

        // redirecionar para a pagina de sucesso
        model.addAttribute("saldoEmissor", String.format("%,.2f", contaOrigem.getSaldo()));
        model.addAttribute("saldoReceptor", String.format("%,.2f", contaDestino.getSaldo()));
        log.info("Redirecionando para a pagina de sucesso da transacao TED");

        return "transacao/sucesso";
    }



    @GetMapping("/doc")
    public String novaTransacaoDoc(Model model, @RequestParam Long id, @AuthenticationPrincipal CustomUserDetails userDetails){
        model.addAttribute("transacao", new TedDocDTO());
        model.addAttribute("contaId", id);
        log.info("Carregando a pagina de DOC");

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
        if (bindingResult.hasErrors()){
            log.warn("Erro(s) no dado fornecido pelo usuario");
            return "transacao/doc";
        }


        // criar nova transacao e salvar no bd
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
        log.info("Transacao salva com sucesso");

        // atualizar o saldo das contas
        atualizaSaldoContas(contaOrigem, contaDestino, docDTO.getValorTransacao());
        log.info("Saldo da conta atualizada com sucesso");

        // redirecionar para a pagina de sucesso
        model.addAttribute("saldoEmissor", String.format("%,.2f", contaOrigem.getSaldo()));
        model.addAttribute("saldoReceptor", String.format("%,.2f", contaDestino.getSaldo()));
        log.info("Redirecionando para a pagina de sucesso da transacao DOC");

        return "transacao/sucesso";
    }


    private void atualizaSaldoContas(Conta contaOrigem, Conta contaDestino, BigDecimal valor){
        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
        contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
        contaService.update(contaOrigem);
        contaService.update(contaDestino);
    }
}
