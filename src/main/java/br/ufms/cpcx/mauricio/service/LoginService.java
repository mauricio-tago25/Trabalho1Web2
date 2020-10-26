package br.ufms.cpcx.mauricio.service;

import br.ufms.cpcx.mauricio.entity.Produto;
import br.ufms.cpcx.mauricio.entity.Usuario;
import br.ufms.cpcx.mauricio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario login(String usuario, String senha) {
        Usuario usuarioLogin = usuarioRepository.buscarNomePelaSenha(usuario, senha);
        if (usuarioLogin != null) {
            return usuarioLogin;
        } else {
            throw new RuntimeException("Usuário ou Senha inválido!");
        }
    }

    public void loginAdmin(String usuario, String senha) {
        Usuario usuarioAdmin = usuarioRepository.buscarNomePelaSenha(usuario, senha);

        if (usuarioAdmin == null || !usuarioAdmin.getIsAdministrador()) {
            throw new RuntimeException("O usuário não é Adminitrador!");
        }
    }

    public List<Produto> buscarProdutosPorIdade(List<Produto> produtos, Usuario usuario) {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataNascimento = usuario.getPessoa().getDataNascimento();
        Integer idade = dataAtual.getYear() - dataNascimento.getYear();

        return produtos.stream().filter(produto -> produto.getIdadePermitida() >= idade).collect(Collectors.toList());
    }
}
