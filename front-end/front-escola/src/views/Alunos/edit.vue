<template>
  <div class="container">
    <h1>Editar Aluno</h1>
    <form @submit.prevent="editarAluno">
      <div class="mb-3">
        <label for="nome" class="form-label">Nome:</label>
        <input type="text" class="form-control" id="nome" v-model="aluno.nome">
      </div>
      <div class="mb-3">
        <label for="matricula" class="form-label">Matrícula:</label>
        <input type="text" class="form-control" id="matricula" v-model="aluno.matricula">
      </div>
      <div class="mb-3">
        <label for="dataNascimento" class="form-label">Data de Nascimento:</label>
        <input type="text" class="form-control" id="dataNascimento" v-model="aluno.dataNascimento">
      </div>
      <div class="mb-3">
        <label for="turma" class="form-label">Turma:</label>
        <select class="form-select" id="turma" v-model="aluno.turmaId">
          <option v-for="turma in turmas" :key="turma.id" :value="turma.id">{{ turma.nomeTurma }}</option>
        </select>
      </div>
      <button type="submit" class="btn btn-primary">Salvar</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'EditAluno',
  data() {
    return {
      aluno: {
        id: null,
        nome: '',
        matricula: '',
        dataNascimento: '',
        turmaId: null,
      },
      turmas: []
    };
  },
  mounted() {
    this.obterDetalhesAluno();
    this.carregarTurmas();
  },
  methods: {
    obterDetalhesAluno() {
      const alunoId = this.$route.params.id;
      axios.get(`http://localhost:8080/alunos/${alunoId}`)
        .then(res => {
          this.aluno = res.data;
        })
        .catch(error => {
          console.error('Erro ao obter detalhes do aluno:', error);
        });
    },
    editarAluno() {
      const alunoId = this.$route.params.id;
      axios.put(`http://localhost:8080/alunos/${alunoId}`, this.aluno)
        .then(() => {
          console.log('Aluno editado com sucesso!');
          this.$router.push({ name: 'Alunos' });
        })
        .catch(error => {
          console.error('Erro ao editar aluno:', error);
        });
    },
    carregarTurmas() {
      axios.get('http://localhost:8080/turmas')
        .then(res => {
          this.turmas = res.data;
        })
        .catch(error => {
          console.error('Erro ao carregar turmas:', error);
        });
    }
  }
};
</script>
