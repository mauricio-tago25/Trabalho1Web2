<template>
  <div>
    <h1>{{ titulo }}</h1>

    <b-form id="formulario-pessoa">
      <b-form-input v-model="pessoa.idResponsavel" placeholder="Id do Responsável"></b-form-input>
      <b-form-input v-model="pessoa.nome" placeholder="Nome" required></b-form-input>
      <b-form-input v-model="pessoa.apelido" placeholder="Apelido" required></b-form-input>
      <b-form-input v-model="pessoa.dataNascimento" placeholder="Data de Nascimento (YYYY-MM-DD)"
                    required></b-form-input>
      <b-form-input v-model="pessoa.situacaoPessoa" placeholder="ATIVA ou INATIVA"></b-form-input>
      <b-form-input v-model="pessoa.tipoPessoa" placeholder="FISICA ou JURIDICA"></b-form-input>

      <!--
            <b-form-select v-model="pessoa.situacaoPessoa" :options="opcaoSituacaoPessoa"></b-form-select>
            <div class="mt-3">Situação atual: <strong>{{ situacaoPessoaSelecionado }}</strong></div>

            <b-form-select v-model="pessoa.tipoPessoa" :options="opcaoTipoPessoa"></b-form-select>
            <div class="mt-3">Tipo: <strong>{{ tipoPessoaSelecionado }}</strong></div>
      -->
      <hr>
      <b-form-group v-if="pessoa.tipoPessoa==='FISICA'">
        <h2>Pessoa Física</h2>
        <b-form-input v-model="pessoa.cpf" placeholder="CPF"></b-form-input>
        <b-form-input v-model="pessoa.rg" placeholder="RG"></b-form-input>
      </b-form-group>

      <b-form-group v-else-if="pessoa.tipoPessoa==='JURIDICA'">
        <h2>Pessoa Jurídica</h2>
        <b-form-input v-model="pessoa.cnpj" placeholder="CNPJ"></b-form-input>
      </b-form-group>

      <b-button size="sm" @click="cadastrar">Cadastrar Pessoa</b-button>
      <b-button size="sm" @click="limpar">Limpar</b-button>
      <b-button size="sm" @click="voltar">Voltar</b-button>
    </b-form>

  </div>
</template>

<script>
export default {
  name: "CadastroPessoa",
  data() {
    return {
      titulo: 'Cadastrar Pessoa',
      tipoPessoaSelecionado: null,
      situacaoPessoaSelecionado: null,
      opcaoTipoPessoa: [
        {value: null, text: 'Selecione o tipo de pessoa'},
        {value: 'Pessoa Física', text: 'Física'},
        {value: 'Pessoa Jurídica', text: 'Jurídica'},
      ],
      opcaoSituacaoPessoa: [
        {value: null, text: 'Situação da pessoa'},
        {value: 'Ativa', text: 'Ativa'},
        {value: 'Inativa', text: 'Inativa'},
      ],
      pessoa: {
        idResponsavel: null,
        tipoPessoa: null,
        situacaoPessoa: null,
        nome: null,
        apelido: null,
        dataNascimento: null,
        cpf: null,
        rg: null,
        cnpj: null
      },
      usuario: {
        usuario: null,
        senha: null
      }
    }
  },
  methods: {
    cadastrar(evt) {
      evt.preventDefault();

      let anoNascimento = this.pessoa.dataNascimento.toString().split('-')[0]
      let anoAtual = new Date().getFullYear()

      if ((anoAtual - anoNascimento < 18) && (this.pessoa.idResponsavel === ' ') && (this.pessoa.tipoPessoa === 'Física')) {
        window.alert('Menor de idade precisa estar acompanhado de um responsável!')
      } else {

        this.$http.post('http://localhost:8080/api/pessoa',
            {
              idResponsavel: this.pessoa.idResponsavel,
              tipoPessoa: this.pessoa.tipoPessoa,
              situacaoPessoa: this.pessoa.situacaoPessoa,
              nome: this.pessoa.nome,
              apelido: this.pessoa.apelido,
              dataNascimento: this.pessoa.dataNascimento,
              cpf: this.pessoa.cpf,
              rg: this.pessoa.rg,
              cnpj: this.pessoa.cnpj
            }, {
              headers: {
                usuario: sessionStorage.getItem('usuario'),
                senha: sessionStorage.getItem('senha')
              }
            })
            .then(response => {
              window.alert('Pessoa Cadastrada com sucesso!')
            })
      }
    },
    limpar() {
      this.pessoa.idResponsavel = ''
      this.pessoa.tipoPessoa = ''
      this.pessoa.situacaoPessoa = ''
      this.pessoa.nome = ''
      this.pessoa.apelido = ''
      this.pessoa.dataNascimento = ''
      this.pessoa.cpf = ''
      this.pessoa.rg = ''
      this.pessoa.cnpj = ''
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