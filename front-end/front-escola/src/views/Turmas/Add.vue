<template>
  <div class="container mt-5">
    <div class="card">
      <div class="card-header text-center">
        <h4 class="mb-0">Adicionar Turma</h4>
      </div>
      <div class="card-body">
        <div v-if="erroCadastro" class="alert alert-danger" role="alert">
          Todos os campos devem ser preenchidos.
        </div>
        <div class="mb-3">
          <label for="nome">Nome da Turma</label>
          <input id="nome" type="text" class="form-control" v-model="turma.nome" required>
        </div>
        <div class="mb-3">
          <label for="horario_inicio">Horário de Início</label>
          <input id="horario_inicio" type="time" class="form-control" v-model="turma.horario_inicio" required>
        </div>
        <button class="btn btn-primary" @click="cadastrarTurma">Cadastrar Turma</button>
        <div v-if="cadastroRealizado" class="modal fade show d-block" role="dialog" style="background-color: rgba(0, 0, 0, 0.5);">
          <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
              <div class="modal-body text-center">
                <p class="h5 text-success">Cadastro realizado com sucesso!</p>
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
        nome: '',
        horario_inicio: ''
      },
      cadastroRealizado: false,
      erroCadastro: false
    };
  },
  methods: {
    cadastrarTurma() {
      if (!this.turma.nome || !this.turma.horario_inicio) {
        this.erroCadastro = true;
        return;
      }
      axios.post('http://localhost:8080/turmas', this.turma)
        .then(() => {
          console.log('Turma cadastrada com sucesso!');
          this.turma = {
            nome: '',
            horario_inicio: ''
          };
          this.cadastroRealizado = true;
          this.erroCadastro = false;
          setTimeout(() => {
            this.cadastroRealizado = false;
          }, 3000);
        })
        .catch(error => {
          console.error('Erro ao cadastrar turma:', error);
        });
    }
  }
}
</script>
