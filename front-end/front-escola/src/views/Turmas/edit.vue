<template>
    <div class="container mt-5">
      <div class="card">
        <div class="card-header text-center">
          <h4 class="mb-0">Editar Turma</h4>
        </div>
        <div class="card-body">
          <div class="mb-3">
            <label for="nome">Nome</label>
            <input id="nome" type="text" class="form-control" v-model="turma.nome">
          </div>
          <div class="mb-3">
            <label for="horario_inicio">Horário de Início</label>
            <input id="horario_inicio" type="text" class="form-control" v-model="horario_inicio">
          </div>
          <button class="btn btn-primary" @click="editarTurma">Salvar Alterações</button>
          <div v-if="edicaoRealizada" class="modal fade show d-block" role="dialog" style="background-color: rgba(0, 0, 0, 0.5);">
            <div class="modal-dialog modal-dialog-centered">
              <div class="modal-content">
                <div class="modal-body text-center">
                  <p class="h5 text-success">Alterações salvas com sucesso!</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        turma: {
          id: null,
          nome: '',
        },
        horario_inicio: '08:00:00', 
        edicaoRealizada: false
      };
    },
    mounted() {
      this.getTurma();
    },
    methods: {
      getTurma() {
        const turmaId = this.$route.params.id; 
        axios.get(`http://localhost:8080/turmas/${turmaId}`)
          .then(res => {
            this.turma = res.data;
          })
          .catch(error => {
            console.error('Erro ao obter turma:', error);
          });
      },
      editarTurma() {
        const turmaEditada = {
          id: this.turma.id,
          nome: this.turma.nome,
          horario_inicio: this.horario_inicio
        };
  
        axios.put(`http://localhost:8080/turmas/${this.turma.id}`, turmaEditada)
          .then(() => {
            console.log('Turma editada com sucesso!');
            this.edicaoRealizada = true;
            setTimeout(() => {
              this.edicaoRealizada = false;
            }, 3000);
          })
          .catch(error => {
            console.error('Erro ao editar turma:', error);
          });
      }
    }
  }
  </script>
  