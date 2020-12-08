package br.ufms.cpcx.mauricio.controller;

import br.ufms.cpcx.mauricio.dto.ProdutoDTO;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private LoginService loginService;

//  @GetMapping
//  @ResponseBody
//  public ResponseEntity<?> buscarTodos(@RequestHeader("usuario") String usuario,
//                                       @RequestHeader("senha") String senha) {
//      Usuario consumidor = loginService.login(usuario, senha);
//      List<Produto> produtosFiltradosPorIdade = loginService.buscarProdutosPorIdade(produtoService.buscarTodos(), consumidor);
//
//      List produtos = new ArrayList();
//
//
//      for (Produto produto : produtosFiltradosPorIdade) {
//          produtos.add(produto);
//      }
//
//      return new ResponseEntity(produtos, HttpStatus.OK);
//  }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> buscarTodosProdutosPorStream(@RequestHeader("usuario") String usuario,
                                                          @RequestHeader("senha") String senha,
                                                          @RequestParam(name = "descricao", required = false) String descricao,
                                                          @RequestParam(name = "precoMin", required = false) BigDecimal precoMin,
                                                          @RequestParam(name = "precoMax", required = false) BigDecimal precoMax) {
        Usuario consumidor = loginService.login(usuario, senha);

        List produtos = new ArrayList();
        List<Produto> produtosFiltradosPorIdade = loginService.buscarProdutosPorIdade(produtoService.buscarTodos(), consumidor);

        for (Produto produto : produtosFiltradosPorIdade) {
            produtos.add(produto);
        }

        return new ResponseEntity(produtoService.buscarTodosProdutosPorStream(descricao, precoMin, precoMax, produtos, consumidor), HttpStatus.OK);
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
