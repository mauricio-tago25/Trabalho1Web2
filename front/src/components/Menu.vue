<template>
  <div class="menu">
    <h1>{{ titulo }}</h1>

    <b-btn-group id="botoes">
      <b-button @click="index">Index</b-button>
      <b-button @click="pessoa">Pessoa</b-button>
      <b-button @click="produto">Produto</b-button>
      <b-button @click="categoria">Categoria</b-button>
    </b-btn-group>
  </div>
</template>

<script>
export default {
  name: 'Menu',
  data() {
    return {
      titulo: 'Menu'
    }
  },
  methods: {
    index(evt) {
      evt.preventDefault();
      this.$router.push({name: 'Index'});
    },
    pessoa(evt) {
      evt.preventDefault();
      this.$router.push({name: 'Pessoa'});
    },
    produto(evt) {
      evt.preventDefault();
      this.$router.push({name: 'Produto'});
    },
    categoria(evt) {
      evt.preventDefault();
      this.$router.push({name: 'Categoria'});
    }
  }
}
</script>

<style scoped>
h1 {
  color: #bfbfbf;
}

#botoes {
  padding-top: 50px;
}
</style>
