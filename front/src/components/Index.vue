<template>
  <div class="home">
    <h1>{{ titulo }}</h1>

    <b-container class="bv-example-row">
      <b-form>
        <b-form-group id="loginFormulario"
                      label="Login:"
                      label-for="loginInput">

          <b-form-input id="loginInput"
                        v-model="usuario.usuario"
                        required>
          </b-form-input>
        </b-form-group>

        <b-form-group id="senhaFormulario"
                      label="Senha:"
                      label-for="senhaInput">
          <b-form-input id="senhaInput"
                        v-model="usuario.senha"
                        required>
          </b-form-input>
        </b-form-group>

        <div class="botoes">
          <b-button size="sm" variant="success" @click="logar">Logar</b-button>
          <b-button size="sm" variant="danger" type="reset" @click="limpar">Limpar</b-button>
          <b-button size="sm" @click="voltar">Voltar</b-button>
        </div>
      </b-form>
    </b-container>
  </div>
</template>

<script>
export default {
  name: 'home',
  data() {
    return {
      titulo: 'Index',
      usuario: {
        usuario: '',
        senha: ''
      }
    }
  },
  methods: {
    logar(evt) {
      evt.preventDefault();
      this.$http.get('http://localhost:8080/api/usuario', {
        headers: {
          usuario: this.usuario.usuario,
          senha: this.usuario.senha
        }
      }).then(response => {
        this.$router.push({name: 'MenuProduto'});
      }, response => {
        window.alert("Usuário ou senha incorretos!")
      });
    },
    limpar() {
      this.usuario.usuario = '';
      this.usuario.senha = '';
    },
    voltar(evt) {
      evt.preventDefault();
      this.$router.push({name: 'Menu'});
    }
  }
}
</script>

<style scoped>
h1 {
  color: #bfbfbf;
}
</style>
