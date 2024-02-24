<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h4>Turmas
          <router-link to="turmas/add" class="btn btn-primary float-end">Adicionar Turma</router-link>
        </h4>
      </div>
      <div class="card-body">
        <table class="table table-bordered">
          <thead>
            <tr>
              <th>ID</th>
              <th>NOME</th>
              <th>HORARIO DE INICIO</th>
              <th>ATIVIDADE DA TURMA</th>
              <th>AÇÕES</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(turma, index) in turmas" :key="index">
              <td>{{ turma.id }}</td>
              <td>{{ turma.nome }}</td>
              <td>{{ turma.horario_inicio }}</td>
              <td>{{ turma.ativo ? 'Ativa' : 'Inativa' }}</td>
              <td>
                <router-link :to="{ name: 'EditTurmas', params: { id: turma.id } }" class="btn btn-success">Editar</router-link>&nbsp;&nbsp;
                <button @click="verAlunos(turma.id)" class="btn btn-info">Ver Alunos</button>&nbsp;&nbsp;
                <button @click="deletarTurma(turma.id)" class="btn btn-danger">Inativar turma</button>
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
  name: 'turmas',
  components: {
    RouterLink
  },
  data() {
    return {
      turmas: []
    };
  },
  mounted() {
    this.getTurmas();
  },
  methods: {
    getTurmas() {
      axios.get('http://java-app:8080/turmas')
        .then(res => {
          this.turmas = res.data;
        })
        .catch(error => {
          console.error('Erro ao obter turmas:', error);
        });
    },
    deletarTurma(id) {
      if (confirm('Tem certeza que deseja excluir esta turma? A exclusão é lógica. Caso queira resgatar entre em contato com os desenvolvedores.')) {
        axios.delete(`http://java-app:8080/turmas/${id}`)
          .then(() => {
            console.log('Turma excluída com sucesso!');
            this.getTurmas();
          })
          .catch(error => {
            console.error('Erro ao excluir turma:', error);
          });
      }
    },
    verAlunos(id) {
      this.$router.push({ name: 'VerAlunos', params: { id: id } });
    }
  }
};
</script>
