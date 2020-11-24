package br.ufms.cpcx.mauricio.service;

import br.ufms.cpcx.mauricio.dto.ProdutoDTO;
import br.ufms.cpcx.mauricio.entity.Produto;
import br.ufms.cpcx.mauricio.entity.Usuario;
import br.ufms.cpcx.mauricio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    public List<?> buscarTodosProdutosPorStream(String descricao, BigDecimal precoMin, BigDecimal precoMax, List<Produto> listaProdutos, Usuario usuario) {
        List<?> buscarTodosProdutosStream;

        buscarTodosProdutosStream = (List<?>) listaProdutos
                .stream()
                .filter(produto -> produto.getDescricao().equals(descricao))
                .filter(produto -> produto.getPrecoVendaFisica().compareTo(precoMin) >= 0)
                .filter(produto -> produto.getPrecoVendaFisica().compareTo(precoMax) <= 0)
                .map(produto -> new ProdutoDTO(produto, usuario.getPessoa().getTipoPessoa()))
                .collect(Collectors.toList());

        return buscarTodosProdutosStream;
    }


    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).get();
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto alterar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }
}
