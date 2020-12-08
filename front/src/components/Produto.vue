<template>
  <div>

    <h1>{{ titulo }}</h1>

    <b-button size="sm">Listar</b-button>
    <b-button size="sm" @click="cadastrarProduto">Cadastrar Produto</b-button>

    <b-button size="sm" @click="voltar">Voltar</b-button>
  </div>
</template>

<script>
export default {
  name: "Produto",
  data() {
    return {
      titulo: 'Produto',
      usuario: {
        usuario: '',
        senha: ''
      },
      produto: {
        descricao: '',
        quantidadeEstoque: '',
        idadePermitida: '',
        precoCompra: '',
        precoVendaFisica: '',
        precoVendaJuridica: ''
      }
    }
  },
  methods: {
    cadastrarProduto(evt) {
      evt.preventDefault();
      this.$router.push({name: 'CadastroProduto'});

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