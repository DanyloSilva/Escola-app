import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AlunosView from'../views/Alunos/view.vue'
import TurmasView from '../views/Turmas/view.vue'
import AddAlunos from '../views/Alunos/Add.vue'
import AddTurmas from '../views/Turmas/Add.vue'
import EditAlunos from '../views/Alunos/edit.vue'
import EditTurmas from '../views/Turmas/edit.vue'
import VerAlunos from '../views/Turmas/VerAlunos.vue';
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/turmas',
      name: 'turmas',
      component: TurmasView

    },
    {
      path: '/alunos',
      name: 'alunos',
      component: AlunosView
    },
    {
      path: '/alunos/add',
      name: 'AddAlunos',
      component: AddAlunos
    },
    {
      path: '/turmas/add',
      name: 'AddTurmas',
      component: AddTurmas
    },
    {
      path: '/alunos/edit/:id',
      name: 'EditAlunos',
      component: EditAlunos
    },
    ,
    {
      path: '/turmas/edit/:id',
      name: 'EditTurmas',
      component: EditTurmas
    },
    {
      path: '/turmas/:id/alunos',
      name: 'VerAlunos',
      component: VerAlunos
    }
  ]
})

export default router
