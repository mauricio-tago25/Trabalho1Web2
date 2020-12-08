package br.ufms.cpcx.mauricio.controller;

import br.ufms.cpcx.mauricio.entity.Usuario;
import br.ufms.cpcx.mauricio.service.LoginService;
import br.ufms.cpcx.mauricio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LoginService loginService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> buscarTodos(@RequestHeader("usuario") String usuario,
                                         @RequestHeader("senha") String senha) {
        loginService.login(usuario, senha);
        return new ResponseEntity(usuarioService.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id,
                                         @RequestHeader("usuario") String usuario,
                                         @RequestHeader("senha") String senha) {
        loginService.login(usuario, senha);
        return new ResponseEntity(usuarioService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> salvar(@RequestBody Usuario usuario) {
        return new ResponseEntity(usuarioService.salvar(usuario), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> alterar(@PathVariable("id") Long id,
                                     @RequestBody Usuario usuario,
                                     @RequestHeader("usuario") String login,
                                     @RequestHeader("senha") String senha) {
        loginService.loginAdmin(login, senha);
        return new ResponseEntity(usuarioService.alterar(usuario), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@PathVariable("id") Long id,
                                     @RequestHeader("usuario") String usuario,
                                     @RequestHeader("senha") String senha) {
        loginService.loginAdmin(usuario, senha);
        usuarioService.deletar(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
