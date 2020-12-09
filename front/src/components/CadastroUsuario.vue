<template>
  <div class="cadastro-usuario">
    <h1>{{ titulo }}</h1>

    <b-form>
      <b-form-group>
        <b-form-input placeholder="Insira Nome" v-model="usuario.usuario" required></b-form-input>
        <b-form-input placeholder="Insia senha" v-model="usuario.senha" required></b-form-input>
      </b-form-group>

      <div class="admin-checkbox">
        <b-form-checkbox v-model="adm" value="Administrador" unchecked-value="Não é Administrador">
          Admin
        </b-form-checkbox>

        <div>Status: <strong>{{ adm }}</strong></div>
      </div>
    </b-form>

    <b-button size="sm" @click="cadastrar">Cadastrar</b-button>
    <b-button size="sm" @click="voltar">Voltar</b-button>
  </div>
</template>

<script>
export default {
  name: "CadastroUsuario",
  data() {
    return {
      titulo: 'Cadastro de Usuário',
      adm: 'Não é Administrador',
      usuario: {
        isAdministrador: '',
        login: '',
        senha: '',
        pessoa: {
          idResponsavel: '',
          tipoPessoa: '',
          situacaoPessoa: '',
          nome: '',
          apelido: '',
          dataNascimento: '',
          cpf: '',
          rg: ''
        }
      }
    }
  },
  methods: {
    cadastrar(evt) {
      evt.preventDefault();

      if (this.usuario.usuario === ' ' || this.usuario.senha === ' ') {
        window.alert('Os campos devem ser preenchidos.')
        return
      }

      this.$http.post('http://localhost:8080/api/usuario',
          {
            'isAdministrador': this.usuario.isAdministrador,
            'login': this.usuario.login,
            'senha': this.usuario.senha,
            'pessoa': {
              'idResponsavel': this.usuario.pessoa.idResponsavel,
              'tipoPessoa': this.usuario.pessoa.tipoPessoa,
              'situacaoPessoa': this.usuario.pessoa.situacaoPessoa,
              'nome': this.usuario.pessoa.nome,
              'apelido': this.usuario.pessoa.apelido,
              'dataNascimento': this.usuario.pessoa.dataNascimento,
              'cpf': this.usuario.pessoa.cpf,
              'rg': this.usuario.pessoa.rg
            }
          },
          {
            headers: {
              usuario: sessionStorage.getItem('usuario'),
              senha: sessionStorage.getItem('senha')
            }
          }
      ).then(response => {
        console.log('ok')
        this.$router.push({name: 'Menu'});
      })

    },
    voltar(evt) {
      evt.preventDefault();
      this.$router.push({name: 'Home'});
    }
  }
}
</script>

<style scoped>
.cadastro-usuario {
  display: -ms-flexbox;
}

h1 {
  color: #bfbfbf;
}
</style>