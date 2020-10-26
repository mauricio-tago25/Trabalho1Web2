package br.ufms.cpcx.mauricio.controller;

import br.ufms.cpcx.mauricio.entity.ItemPedido;
import br.ufms.cpcx.mauricio.service.ItemPedidoService;
import br.ufms.cpcx.mauricio.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/itemPedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private LoginService loginService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> buscarTodos(@RequestHeader("usuario") String usuario,
                                         @RequestHeader("senha") String senha) {
        loginService.login(usuario, senha);
        return new ResponseEntity(itemPedidoService.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id,
                                         @RequestHeader("usuario") String usuario,
                                         @RequestHeader("senha") String senha) {
        loginService.login(usuario, senha);
        return new ResponseEntity(itemPedidoService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> salvar(@RequestBody ItemPedido body,
                                    @RequestHeader("usuario") String usuario,
                                    @RequestHeader("senha") String senha) {
        loginService.loginAdmin(usuario, senha);
        return new ResponseEntity(itemPedidoService.salvar(body), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ResponseBody
    public ResponseEntity<?> alterar(@PathVariable("id") Long id,
                                     @RequestBody ItemPedido body,
                                     @RequestHeader("usuario") String usuario,
                                     @RequestHeader("senha") String senha) {
        loginService.loginAdmin(usuario, senha);
        return new ResponseEntity(itemPedidoService.alterar(body), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@PathVariable("id") Long id,
                                     @RequestHeader("usuario") String usuario,
                                     @RequestHeader("senha") String senha) {
        loginService.loginAdmin(usuario, senha);
        itemPedidoService.deletar(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
