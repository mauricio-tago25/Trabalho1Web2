<template>
  <div>

    <h1>{{ titulo }}</h1>

    <b-form>
      <b-form-group id="descricaoFormulario" label="Descrição:" label-for="descricaoInput">
        <b-form-input id="descricaoInput" v-model="produto.descricao" required></b-form-input>
      </b-form-group>

      <b-form-group id="qtdeEstoqueFormulario" label="Quantidade no estoque:" label-for="qtdeEstoqueInput">
        <b-form-input id="qtdeEstoqueInput" v-model="produto.quantidadeEstoque" required></b-form-input>
      </b-form-group>

      <b-form-group id="idadePermitidaFormulario" label="Idade Permitida:" label-for="idadePermitidaInput">
        <b-form-input id="idadePermitidaInput" v-model="produto.idadePermitida" required></b-form-input>
      </b-form-group>

      <b-form-group id="precoCompraFormulario" label="Preço de Compra:" label-for="precoCompraInput">
        <b-form-input id="precoCompraInput" v-model="produto.precoCompra" required></b-form-input>
      </b-form-group>

      <b-form-group id="precoVendaFisicaFormulario" label="Preço de Venda Fisica:" label-for="precoVendaFisicaInput">
        <b-form-input id="precoVendaFisicaInput" v-model="produto.precoVendaFisica" required></b-form-input>
      </b-form-group>

      <b-form-group id="precoVendaJuridicaFormulario" label="Preço de Venda Jurídica:"
                    label-for="precoVendaJuridicaInput">
        <b-form-input id="precoVendaJuridicaInput" v-model="produto.precoVendaJuridica" required></b-form-input>
      </b-form-group>

      <b-button size="sm" variant="success" @click="cadastrarProduto">Cadastrar</b-button>
      <b-button size="sm" variant="danger" @click="limpar">Limpar</b-button>
      <b-button size="sm" @click="voltar">Voltar</b-button>

    </b-form>
  </div>
</template>

<script>
export default {
  name: "CadastroProduto",
  data() {
    return {
      titulo: 'Cadastro de Produto',
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
    cadastrarProduto() {
      this.$http.post('http://localhost:8080/api/produto',
          {
            'descricao': this.produto.descricao,
            'quantidadeEstoque': this.produto.quantidadeEstoque,
            'idadePermitida': this.produto.idadePermitida,
            'precoCompra': this.produto.precoCompra,
            'precoVendaFisica': this.produto.precoVendaFisica,
            'precoVendaJuridica': this.produto.precoVendaJuridica
          },
          {
            headers: {
              usuario: this.usuario.usuario,
              senha: this.usuario.senha
            }
          }
      ).then(response => {
        window.alert('Produto inserido com sucesso')
      }, response => {
        window.alert('Erro ao cadastrar produto.')
      })
    },
    limpar() {
      this.produto.descricao = '';
      this.produto.quantidadeEstoque = '';
      this.produto.idadePermitida = '';
      this.produto.precoCompra = '';
      this.produto.precoVendaFisica = '';
      this.produto.precoVendaJuridica = '';
    },
    voltar(evt) {
      evt.preventDefault();
      this.$router.push({name: 'Produto'});
    }
  }
}
</script>

<style scoped>
h1 {
  color: #bfbfbf;
}
</style>