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
      pessoa: {
        idResponsavel: '',
        nomeResponsavel: '',
        tipoPessoa: '',
        situacao: ''
      }
    }
  },
  methods: {
    buscar() {
      axios.get('http://localhost:8080/api/pessoa')
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