<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h4>Alunos da Turma {{ turmaNome }}
          <router-link to="/" class="btn btn-secondary float-end">Voltar</router-link>
        </h4>
      </div>
      <div class="card-body">
        <table class="table table-bordered">
          <thead>
            <tr>
              <th>ID</th>
              <th>NOME</th>
              <th>AÇÕES</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(aluno, index) in alunos" :key="index">
              <td>{{ aluno.id }}</td>
              <td>{{ aluno.nome }}</td>
              <td>
                <button @click="removerAluno(aluno.id)" class="btn btn-danger">Remover da Turma</button>
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
export default {
  name: 'VerAlunos',
  data() {
    return {
      alunos: [],
      turmaNome: ''
    };
  },
  mounted() {
    this.carregarAlunos();
  },
  methods: {
carregarAlunos() {
  const turmaId = this.$route.params.id;
  axios.get(`http://localhost:8080/turmas/${turmaId}/alunos`)
    .then(res => {
      this.alunos = res.data;
      this.turmaNome = res.data.length > 0 ? res.data[0].nomeTurma : '';
    })
    .catch(error => {
      console.error('Erro ao obter alunos da turma:', error);
    });
},
removerAluno(alunoId) {
  if (confirm('Tem certeza que deseja remover este aluno da turma?')) {
    const turmaId = this.$route.params.id;
    axios.put(`http://localhost:8080/alunos/${alunoId}`, {
        turmaId: 1 
    })
      .then(() => {
        console.log('Aluno removido da turma com sucesso!');
        this.carregarAlunos(); 
      })
      .catch(error => {
        console.error('Erro ao remover aluno da turma:', error);
      });
  }
}
}

};
</script>
