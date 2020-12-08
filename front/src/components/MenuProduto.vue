<template>
  <div>
    <b-form>
      <b-form-group id="loginFormulario" label="Login:" label-for="loginInput">
        <b-form-input id="loginInput"
                      v-model="usuario.usuario"
                      required>
        </b-form-input>
      </b-form-group>

      <b-form-group id="senhaFormulario" label="Senha:" label-for="senhaInput">
        <b-form-input id="senhaInput"
                      v-model="usuario.senha"
                      required>
        </b-form-input>
      </b-form-group>
    </b-form>

    <b-button @click="mostrarProduto">Buscar</b-button>
    <b-button @click="direcionarCadastroProduto">Novo Item</b-button>
  </div>
</template>

<script>
export default {
  name: "MenuProduto",
  data() {
    return {
      usuario: {
        usuario: '',
        senha: ''
      }
    }
  },
  methods: {
    mostrarProduto() {
      this.$http.get('http://localhost:8080/api/produto', {
        headers: {
          usuario: this.usuario.usuario,
          senha: this.usuario.senha
        }
      }).then(response => {

      })
    }, direcionarCadastroProduto() {
      this.$router.push({name: 'Produto'});
    }
  }
}
</script>

<style scoped>

</style>