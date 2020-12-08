<template>
  <div>
    <h1>{{ titulo }}</h1>

    <b-button size="sm">Listar</b-button>
    <b-button size="sm" @click="cadastrarPessoa">Cadastrar Pessoa</b-button>

    <b-button size="sm" @click="voltar">Voltar</b-button>
  </div>
</template>

<script>
export default {
  name: "Pessoa",
  data() {
    return {
      titulo: 'Pessoa'
    }
  },
  methods: {
    voltar(evt) {
      evt.preventDefault();
      this.$router.push({name: 'Menu'});
    },
    cadastrarPessoa(evt) {
      evt.preventDefault();
      this.$router.push({name: 'CadastroPessoa'});
    }
  }
}
</script>

<style scoped>
h1 {
  color: #bfbfbf;
}
</style>