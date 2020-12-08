import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Produto from '@/components/Produto'
import CadastroProduto from "@/components/CadastroProduto"
import Categoria from "@/components/Categoria"
import Home from '@/components/Home'
import Menu from '@/components/Menu'
import MenuProduto from "@/components/MenuProduto"
import Pessoa from '@/components/Pessoa'
import CadastroPessoa from "@/components/CadastroPessoa"
import CadastroUsuario from "@/components/CadastroUsuario"


Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            name: 'Home',
            component: Home
        },
        {
            path: '/menu',
            name: 'Menu',
            component: Menu
        },
        {
            path: '/index',
            name: 'Index',
            component: Index
        },
        {
            path: '/menuProduto',
            name: 'MenuProduto',
            component: MenuProduto
        },
        {
            path: '/produto',
            name: 'Produto',
            component: Produto
        },
        {
            path: '/cadastroProduto',
            name: 'CadastroProduto',
            component: CadastroProduto
        },
        {
            path: '/categoria',
            name: 'Categoria',
            component: Categoria
        },
        {
            path: '/pessoa',
            name: 'Pessoa',
            component: Pessoa
        },
        {
            path: '/cadastroPessoa',
            name: 'CadastroPessoa',
            component: CadastroPessoa
        },
        {
            path: '/cadastroUsuario',
            name: 'CadastroUsuario',
            component: CadastroUsuario
        }
    ]
})
