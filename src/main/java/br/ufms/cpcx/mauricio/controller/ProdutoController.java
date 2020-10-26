package br.ufms.cpcx.mauricio.controller;

import br.ufms.cpcx.mauricio.dto.ProdutoFisicoDTO;
import br.ufms.cpcx.mauricio.dto.ProdutoJuridicoDTO;
import br.ufms.cpcx.mauricio.entity.Usuario;
import br.ufms.cpcx.mauricio.enuns.ETipoPessoa;
import br.ufms.cpcx.mauricio.service.LoginService;
import br.ufms.cpcx.mauricio.service.ProdutoService;
import br.ufms.cpcx.mauricio.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private LoginService loginService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> buscarTodos(@RequestHeader("usuario") String usuario,
                                         @RequestHeader("senha") String senha) {
        Usuario consumidor = loginService.login(usuario, senha);
        List<Produto> produtos = loginService.buscarProdutosPorIdade(produtoService.buscarTodos(), consumidor);

        if (consumidor.getPessoa().getTipoPessoa().equals(ETipoPessoa.FISICA)) {

            List<ProdutoFisicoDTO> produtoFisicoDTOS = new ArrayList<>();

            for (Produto produto : produtos) {
                produtoFisicoDTOS.add(ProdutoFisicoDTO.produtoFisicoDTO(produto));
            }

            return new ResponseEntity(produtoFisicoDTOS, HttpStatus.OK);
        } else {

            List<ProdutoJuridicoDTO> produtoJuridicoDTOS = new ArrayList<>();

            for (Produto produto : produtos) {
                produtoJuridicoDTOS.add(ProdutoJuridicoDTO.produtoJuridicoDTO(produto));
            }

            return new ResponseEntity(produtoJuridicoDTOS, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id,
                                         @RequestHeader("usuario") String usuario,
                                         @RequestHeader("senha") String senha) {
        loginService.login(usuario, senha);
        return new ResponseEntity(produtoService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> salvar(@RequestBody Produto body,
                                    @RequestHeader("usuario") String usuario,
                                    @RequestHeader("senha") String senha) {
        loginService.loginAdmin(usuario, senha);
        return new ResponseEntity(produtoService.salvar(body), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> alterar(@PathVariable("id") Long id,
                                     @RequestBody Produto body,
                                     @RequestHeader("usuario") String usuario,
                                     @RequestHeader("senha") String senha) {
        loginService.loginAdmin(usuario, senha);
        return new ResponseEntity(produtoService.alterar(body), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@PathVariable("id") Long id,
                                     @RequestHeader("usuario") String usuario,
                                     @RequestHeader("senha") String senha) {
        loginService.loginAdmin(usuario, senha);
        produtoService.deletar(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
