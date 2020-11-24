package br.ufms.cpcx.mauricio.repository;

import br.ufms.cpcx.mauricio.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT usuario FROM Usuario usuario WHERE usuario.login LIKE :nome AND usuario.senha LIKE :senha")
    Usuario buscarNomePelaSenha(@Param("nome") String nome, @Param("senha") String senha);
}
