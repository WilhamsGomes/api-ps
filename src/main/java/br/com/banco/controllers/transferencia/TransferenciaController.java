package br.com.banco.controllers.transferencia;

import br.com.banco.models.TransferenciaModal;
import br.com.banco.repositories.transferencia.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    //Rota sem filtros
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<TransferenciaModal>> getTransferencias(){
        List<TransferenciaModal> transferencias = transferenciaRepository.findAll();
        return new ResponseEntity<List<TransferenciaModal>>(transferencias, HttpStatus.OK);
    }

    //Rota para fornecer dados de acordo com o número da conta bancária
    @GetMapping("/contas")
    @ResponseBody
    public ResponseEntity<List<TransferenciaModal>> getTransferenciasPorConta(@RequestParam(name = "conta_id") Long conta_id){
        List<TransferenciaModal> transferencias = transferenciaRepository.findByConta(conta_id);
        return new ResponseEntity<List<TransferenciaModal>>(transferencias, HttpStatus.OK);
    }

    /*
    //Rota para fornecer dados de acordo com a conta bancária E operador
    @GetMapping("/contas/operadores")
    @ResponseBody
    public ResponseEntity<List<TransferenciaModal>> getTransferenciasPorContaEOperador(@RequestParam(name = "conta_id") Long conta_id,
                                                                                       @RequestParam(name = "nome_operador_transacao") String nome_operador_transacao){
        List<TransferenciaModal> transferencias = transferenciaRepository.findByContaAndOperador(conta_id, nome_operador_transacao.trim().toUpperCase());
        return new ResponseEntity<List<TransferenciaModal>>(transferencias, HttpStatus.OK);
    }
    */

    //Rota para fornecer dados por período de datas
    @GetMapping("/data")
    @ResponseBody
    public ResponseEntity<List<TransferenciaModal>> getTransferenciasPorPeriodo(@RequestParam(name = "dataInicio")  String dataInicio,
                                                                             @RequestParam(name = "dataFinal") String  dataFinal){

        LocalDate dataDeInicio = LocalDate.parse(dataInicio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dataDoFinal = LocalDate.parse(dataFinal, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<TransferenciaModal> transferencias = transferenciaRepository.findByData(dataDeInicio, dataDoFinal);
        return new ResponseEntity<List<TransferenciaModal>>(transferencias, HttpStatus.OK);
    }

    //Rota para fornecer dados de acordo com o operador
    @GetMapping("/operadores")
    @ResponseBody
    public ResponseEntity<List<TransferenciaModal>> getTransferenciasPorOperador(@RequestParam(name = "nome_operador_transacao") String nome_operador_transacao){
        List<TransferenciaModal> transferencias = transferenciaRepository.findByOperador(nome_operador_transacao.trim().toUpperCase());
        return new ResponseEntity<List<TransferenciaModal>>(transferencias, HttpStatus.OK);
    }

    //Rota para fornecer dados de acordo com o período e operador
    @GetMapping("/data/operador")
    @ResponseBody
    public ResponseEntity<List<TransferenciaModal>> getTransferenciasPorPeriodoEOperador(@RequestParam(name = "dataInicio")  String dataInicio,
                                                                                         @RequestParam(name = "dataFinal") String  dataFinal,
                                                                                         @RequestParam(name = "nome_operador_transacao") String nome_operador_transacao){

        LocalDate dataDeInicio = LocalDate.parse(dataInicio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dataDoFinal = LocalDate.parse(dataFinal, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<TransferenciaModal> transferencias = transferenciaRepository.findByDataOperador(dataDeInicio, dataDoFinal, nome_operador_transacao.trim().toUpperCase());
        return new ResponseEntity<List<TransferenciaModal>>(transferencias, HttpStatus.OK);
    }

}
