<template>
  <div class="cadastro-usuario">
    <h1>{{ titulo }}</h1>

    <b-form>
      <b-form-group>
        <b-form-input placeholder="Insira Nome" required></b-form-input>
        <b-form-input placeholder="Insia senha" required></b-form-input>
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
        usuario: '',
        senha: ''
      }
    }
  },
  methods: {
    cadastrar(evt) {
      evt.preventDefault();

      if (this.usuario.usuario !== ' ' || this.usuario.senha !== ' ') {
        window.alert('Os campos devem ser preenchidos.')
        return
      }

      this.$http.post('http://localhost:8080/api/usuario',
          {
            'nome': this.usuario.usuario,
            'senha': this.usuario.senha,
            'isAdm': this.adm.checked
          }
      ).then(response => {
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