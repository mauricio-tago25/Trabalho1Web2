package br.ufms.cpcx.mauricio.controller;

import br.ufms.cpcx.mauricio.entity.Pedido;
import br.ufms.cpcx.mauricio.service.LoginService;
import br.ufms.cpcx.mauricio.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private LoginService loginService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> buscarTodos(@RequestHeader("usuario") String usuario,
                                         @RequestHeader("senha") String senha) {
        loginService.login(usuario, senha);
        return new ResponseEntity(pedidoService.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id,
                                         @RequestHeader("usuario") String usuario,
                                         @RequestHeader("senha") String senha) {
        loginService.login(usuario, senha);
        return new ResponseEntity(pedidoService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> salvar(@RequestBody Pedido body,
                                    @RequestHeader("usuario") String usuario,
                                    @RequestHeader("senha") String senha) {
        loginService.loginAdmin(usuario, senha);
        return new ResponseEntity(pedidoService.salvar(body), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ResponseBody
    public ResponseEntity<?> alterar(@PathVariable("id") Long id,
                                     @RequestBody Pedido body,
                                     @RequestHeader("usuario") String usuario,
                                     @RequestHeader("senha") String senha) {
        loginService.loginAdmin(usuario, senha);
        return new ResponseEntity(pedidoService.alterar(body), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@PathVariable("id") Long id,
                                     @RequestHeader("usuario") String usuario,
                                     @RequestHeader("senha") String senha) {
        loginService.loginAdmin(usuario, senha);
        pedidoService.deletar(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
