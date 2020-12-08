<template>
  <div id="telaCategoria">
    <h1>Categorias</h1>
    <b-container fluid id="conteiner">
      <a href="#/">
        <b-button id="home" size="sm">Home</b-button>
      </a>
      <b-row class="viewCategoria">

        <b-modal id="modalEditarCategoria" title="Editar Categoria" hide-footer>

          <b-alert :show="editarSucesso" variant="success" dismissible>Categoria alterada com sucesso!</b-alert>
          <b-alert :show="editarErro" variant="danger" dismissible>Erro ao alterar categoria.</b-alert>

          <b-form-group id="categoriaFormularioEdicao"
                        label="Nome:"
                        label-for="categoriaInputEdicao">

            <b-form-input id="categoriaInputEdicao" sm="5"
                          v-model="categoria.nomeModal"
                          required>
            </b-form-input>
          </b-form-group>

          <b-button size="sm" @click="alterar">Alterar categoria</b-button>
          <b-button size="sm" @click="onResetModal">Cancelar</b-button>
        </b-modal>

        <b-col>

          <b-alert :show="msgSucessoInserir" variant="success" dismissible>Categoria cadastrada com sucesso!</b-alert>
          <b-alert :show="msgErroInserir" variant="danger" dismissible>Erro ao cadastrar uma categoria.</b-alert>

          <b-form-group id="categoriaFormulario"
                        label="Nome da Categoria:"
                        label-for="categoriaInput">
            <b-form-input id="categoriaInput" sm="5"
                          v-model="categoria.nome"
                          required
            >
            </b-form-input>

            <div id="botoesEdicao">
              <b-button size="sm" @click="salvar">Cadastrar categoria</b-button>
              <b-button size="sm" @click="onReset">Cancelar</b-button>
            </div>
          </b-form-group>
        </b-col>

        <b-col>

          <b-alert :show="msgErroExcluir" variant="danger" dismissible>Categoria possui produto(s)!</b-alert>
          <b-alert :show="msgSucessoExcluir" variant="success" dismissible>Categoria excluída com sucesso!</b-alert>

          <b-table
            show-empty
            stacked="md"
            :items="categoriasCadastradas"
            :fields="fields"
            @filtered="onFiltered"
          >
            <template slot="nome" slot-scope="row">
              {{ row.item.nome }}
            </template>

            <template slot="acoes" slot-scope="row">
              <b-button size="sm" v-b-modal.modalEditarCategoria @click="capturarCategoria(row.item)" class="mr-1">
                Editar
              </b-button>
              <b-button size="sm" @click="deletar(row.item)" class="mr-1">
                Excluir
              </b-button>

              <b-button @click="direcionarApaginaCategoria(row.item)" size="sm">Produtos</b-button>

            </template>

            <template slot="row-details" slot-scope="row">
              <b-card>
                <ul>
                  <li v-for="(value, key) in row.item" :key="key">{{ key }} : {{ value }}</li>
                </ul>
              </b-card>
            </template>
          </b-table>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        categoria: {
          id: '',
          nome: ''
        },
        nome: ' ',
        fields: [
          {key: 'nome', label: 'Nome', sortable: true, sortDirection: 'desc'},
          {key: 'acoes', label: 'Ações'}
        ],
        cadastroCategoria: null,
        categoriasCadastradas: [],
        msgSucessoInserir: false,
        msgSucessoExcluir: false,
        msgErroExcluir: false,
        categoriaAserAtualizada: {},
        nomeModal: '',
        editarSucesso: false,
        editarErro: false,
        msgErroInserir: false
      }
    },
    created() {
      this.$http.get('http://localhost:9000/categorias').then(response => {
        this.categoriasCadastradas = response.body;
      });
    },
    methods: {
      onResetModal() {
        this.categoria.nomeModal = ''
      },
      direcionarApaginaCategoria(item) {
        this.$router.push(item.id + '/produto');
      },
      limparCampos() {
        this.nomeModal = '';
      },
      capturarCategoria(item) {
        this.categoriaAserAtualizada = item;
      },
      onReset() {
        this.categoria.nome = '';
      },
      salvar(evt) {
        evt.preventDefault();
        this.$http.post('http://localhost:9000/categorias', this.categoria).then(response => {
          this.msgSucessoInserir = true;
          this.atualizarBanco();
        }, response => {
          this.msgErroInserir = true;
        });
      },
      buscar() {
        this.$http.get('http://localhost:9000/categorias').then(response => {
        })
      },
      alterar() {
        this.$http.put('http://localhost:9000/categorias/' + this.categoriaAserAtualizada.id, {
          id: this.categoriaAserAtualizada.id,
          nome: this.categoria.nomeModal
        }).then(response => {
          this.editarSucesso = true;
          this.atualizarBanco();
        }, () => {
          this.editarErro = true;
        })
      },
      deletar(item) {
        this.$http.delete('http://localhost:9000/categorias/' + item.id).then(response => {
          this.msgSucessoExcluir = true;
          this.atualizarBanco();
        }, () => {
          this.msgErroExcluir = true;
        })
      },
      atualizarBanco() {
        this.$http.get('http://localhost:9000/categorias').then(response => {
          this.categoriasCadastradas = response.body;
        });
      },
      onFiltered(filteredItems) {
        this.totalRows = filteredItems.length
        this.currentPage = 1
      }
    }
  }
</script>

<style scoped>
  #home {
    float: left;
    margin-left: 20px;
    margin-top: -80px;
  }

  #telaCategoria {
    margin-top: -60px;
    background-image: url("https://aeroleads.com/blog/wp-content/uploads/2017/12/static1.squarespace.com_.jpg");
    background-repeat: no-repeat;
    width: 100%;
    height: 100%;
    position: absolute;
    background-size: 100%;
  }

  #botoesEdicao {
    padding-top: 13px;
    float: right;
  }

  #conteiner {
    padding-top: 120px;
    background-image: radial-gradient(circle, #feffc1, #fffeda, #fffaeb);
    opacity: 0.85;
  }
</style>
