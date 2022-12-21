package br.com.banco.controllers.user;

import br.com.banco.models.ContaModal;
import br.com.banco.repositories.user.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContaController {

    @Autowired /*Injeção de dependência*/
    private ContaRepository contaRepository;

    @GetMapping(value = "/users")
    @ResponseBody /*Retorna os dados para o corpo  da resposta*/
    public ResponseEntity<List<ContaModal>> getUsers(){
        List<ContaModal> users = contaRepository.findAll(); /*Executa a consulta*/
        return new ResponseEntity<List<ContaModal>>(users, HttpStatus.OK); /*Retorna a lista de contas em JSON*/
    }

    @GetMapping(value = "/userByName")
    @ResponseBody
    public ResponseEntity<List<ContaModal>> buscaPorNome (@RequestParam(name = "name") String name){

        List<ContaModal> users = contaRepository.buscarPorNome(name.trim().toUpperCase());
        return new ResponseEntity<List<ContaModal>>(users, HttpStatus.OK);

    }

}
