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

    //End-point sem filtros (Retorna todas as transferencias, de todas as contas)
    @CrossOrigin
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<TransferenciaModal>> getTransferencias(){
        List<TransferenciaModal> transferencias = transferenciaRepository.findAll();
        return new ResponseEntity<List<TransferenciaModal>>(transferencias, HttpStatus.OK);
    }

    //End-point para fornecer todas as transferências, de acordo com o número da conta bancária
    @CrossOrigin
    @GetMapping("/contas")
    @ResponseBody
    public ResponseEntity<List<TransferenciaModal>> getTransferenciasPorConta(@RequestParam(name = "conta_id") Long conta_id){
        List<TransferenciaModal> transferencias = transferenciaRepository.findByConta(conta_id);
        return new ResponseEntity<List<TransferenciaModal>>(transferencias, HttpStatus.OK);
    }

    //End-point para fornecer transferências por período de datas
    @CrossOrigin
    @GetMapping("/contas/periodo")
    @ResponseBody
    public ResponseEntity<List<TransferenciaModal>> getTransferenciasPorPeriodo(@RequestParam(name = "conta_id") Long conta_id,
                                                                                @RequestParam(name = "dataInicio")  String dataInicio,
                                                                                @RequestParam(name = "dataFinal") String  dataFinal){

        LocalDate dataDeInicio = LocalDate.parse(dataInicio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dataDoFinal = LocalDate.parse(dataFinal, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if(dataDeInicio.isAfter(dataDoFinal)){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        List<TransferenciaModal> transferencias = transferenciaRepository.findByPeriodo(dataDeInicio, dataDoFinal, conta_id);
        return new ResponseEntity<List<TransferenciaModal>>(transferencias, HttpStatus.OK);
    }

    //End-point para fornecer dados de acordo com o operador
    @CrossOrigin
    @GetMapping("/contas/operadores")
    @ResponseBody
    public ResponseEntity<List<TransferenciaModal>> getTransferenciasPorOperador(@RequestParam(name = "conta_id") Long conta_id,
                                                                                 @RequestParam(name = "nome_operador_transacao") String nome_operador_transacao){

        List<TransferenciaModal> transferencias = transferenciaRepository.findByOperador(nome_operador_transacao.trim().toUpperCase(), conta_id);
        return new ResponseEntity<List<TransferenciaModal>>(transferencias, HttpStatus.OK);
    }

    //End-point para fornecer transferências de acordo com o período e operador
    @CrossOrigin
    @GetMapping("/contas/operadores/periodo")
    @ResponseBody
    public ResponseEntity<List<TransferenciaModal>> getTransferenciasPorPeriodoEOperador(@RequestParam(name = "conta_id") Long conta_id,
                                                                                         @RequestParam(name = "dataInicio")  String dataInicio,
                                                                                         @RequestParam(name = "dataFinal") String  dataFinal,
                                                                                         @RequestParam(name = "nome_operador_transacao") String nome_operador_transacao){

        LocalDate dataDeInicio = LocalDate.parse(dataInicio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dataDoFinal = LocalDate.parse(dataFinal, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if(dataDeInicio.isAfter(dataDoFinal)){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        List<TransferenciaModal> transferencias = transferenciaRepository.findByDataOperador(   dataDeInicio,
                                                                                                dataDoFinal,
                                                                                                nome_operador_transacao.trim().toUpperCase(),
                                                                                                conta_id
                                                                                            );
        return new ResponseEntity<List<TransferenciaModal>>(transferencias, HttpStatus.OK);
    }

}
