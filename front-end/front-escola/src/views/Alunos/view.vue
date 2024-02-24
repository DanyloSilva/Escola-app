<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h4>Alunos
          <router-link to="alunos/add" class="btn btn-primary float-end">Adicionar Aluno</router-link>
        </h4>
      </div>
      <div class="card-body">
        <table class="table table-bordered">
          <thead>
            <tr>
              <th>ID</th>
              <th>NOME</th>
              <th>MATRICULA</th>
              <th>DATA DE NASCIMENTO</th>
              <th>TURMA</th>
              <th>AÇÕES</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(aluno, index) in alunos" :key="index">
              <td>{{ aluno.id }}</td>
              <td>{{ aluno.nome }}</td>
              <td>{{ aluno.matricula }}</td>
              <td>{{ aluno.dataNascimento }}</td>
              <td>{{ aluno.nomeTurma }}</td>
              <td>
                <router-link :to="{ name: 'EditAlunos', params: { id: aluno.id } }" class="btn btn-success">Editar</router-link>&nbsp;&nbsp;
                <button @click="deletarAluno(aluno.id)" class="btn btn-danger ">Deletar</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { RouterLink } from 'vue-router';

export default {
  name: 'alunos',
  components: {
    RouterLink
  },
  data() {
    return {
      alunos: []
    };
  },
  mounted() {
    this.getAlunos();
  },
  methods: {
    getAlunos() {
      axios.get('http://localhost:8080/alunos')
        .then(res => {
          this.alunos = res.data;
        })
        .catch(error => {
          console.error('Erro ao obter alunos:', error);
        });
    },
    deletarAluno(id) {
      if (confirm('Tem certeza que deseja excluir este aluno? A excluçao e logica caso queira regatar entre em contato com os desenvolvedores.')) {
        axios.delete(`http://localhost:8080/alunos/${id}`)
          .then(() => {
            console.log('Aluno excluído com sucesso!');
            this.getAlunos();
          })
          .catch(error => {
            console.error('Erro ao excluir aluno:', error);
          });
      }
    }
  }
};
</script>

  