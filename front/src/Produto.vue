<template>
  <div id="btn-buscar">
    <button @click="buscar">Buscar</button>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      produto: {
        id: '',
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
    buscar() {
      axios.get('http://localhost:8080/api/produto')
          .then(response => {
            console.log(response.data)
          })
          .catch(error => {
            console.log(error)
          })
    }
  }
}
</script>

<style scoped>

</style>