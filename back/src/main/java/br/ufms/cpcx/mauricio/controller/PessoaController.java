package br.ufms.cpcx.mauricio.controller;

import br.ufms.cpcx.mauricio.pojo.PessoaPojo;
import br.ufms.cpcx.mauricio.service.LoginService;
import br.ufms.cpcx.mauricio.service.PessoaService;
import br.ufms.cpcx.mauricio.entity.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private LoginService loginService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> buscarTodas() {
        return new ResponseEntity(pessoaService.buscarTodas(), HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> buscarTodasPessoasPorStream(@RequestHeader("usuario") String usuario,
                                                         @RequestHeader("senha") String senha,
                                                         @RequestParam(name = "idResponsavel", required = false) Long idResponsavel,
                                                         @RequestParam(name = "nomeResponsavel", required = false) String nomeResponsavel,
                                                         @RequestParam(name = "tipo", required = false) String tipo,
                                                         @RequestParam(name = "situacao", required = false) String situacao) {
        loginService.login(usuario, senha);
        return new ResponseEntity(pessoaService.buscarTodasStream(idResponsavel, nomeResponsavel, tipo, situacao), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id,
                                         @RequestHeader("usuario") String usuario,
                                         @RequestHeader("senha") String senha) {
        loginService.login(usuario, senha);
        return new ResponseEntity(pessoaService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> salvar(@RequestBody PessoaPojo body) {
        return new ResponseEntity(pessoaService.salvar(body), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> alterar(@PathVariable("id") Long id,
                                     @RequestBody Pessoa body,
                                     @RequestHeader("usuario") String usuario,
                                     @RequestHeader("senha") String senha) {
        loginService.loginAdmin(usuario, senha);
        return new ResponseEntity(pessoaService.alterar(body), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@PathVariable("id") Long id,
                                     @RequestHeader("usuario") String usuario,
                                     @RequestHeader("senha") String senha) {
        pessoaService.deletar(id);
        loginService.loginAdmin(usuario, senha);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
