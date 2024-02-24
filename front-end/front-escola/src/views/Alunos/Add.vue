<template>
  <div class="container mt-5">
    <div class="card">
      <div class="card-header text-center">
        <h4 class="mb-0">Adicionar Aluno</h4>
      </div>
      <div class="card-body">
        <div class="mb-3">
          <label for="nome">Nome</label>
          <input id="nome" type="text" class="form-control" v-model="aluno.nome" required>
        </div>
        <div class="mb-3">
          <label for="matricula">Matrícula</label>
          <input id="matricula" type="text" class="form-control" v-model="aluno.matricula" required>
        </div>
        <div class="mb-3">
          <label for="data_nascimento">Data de Nascimento</label>
          <input id="data_nascimento" type="text" class="form-control" v-model="aluno.data_nascimento" @input="formatarDataNascimento" required>
        </div>
        <div class="mb-3">
          <label for="turma">Turma</label>
          <select id="turma" class="form-control" v-model="aluno.turmaId">
            <option v-for="turma in turmas" :key="turma.id" :value="turma.id">{{ turma.nome }}</option>
          </select>
        </div>
        <button class="btn btn-primary" @click="cadastrarAluno">Cadastrar Aluno</button>
        <div v-if="erroCadastro" class="alert alert-danger mt-3" role="alert">
          Preencha todos os campos obrigatórios(Turmas não e Obrigatorio).
        </div>
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
      aluno: {
        nome: '',
        matricula: '',
        data_nascimento: '',
        turmaId: null
      },
      turmas: [],
      cadastroRealizado: false,
      erroCadastro: false
    };
  },
  mounted() {
    this.getTurmas();
  },
  methods: {
    getTurmas() {
      axios.get('http://localhost:8080/turmas')
        .then(res => {
          this.turmas = res.data;
        })
        .catch(error => {
          console.error('Erro ao obter turmas:', error);
        });
    },
    cadastrarAluno() {
      if (!this.aluno.nome || !this.aluno.matricula || !this.aluno.data_nascimento) {
        this.erroCadastro = true;
        return;
      }
      this.aluno.data_nascimento = this.formatarDataBackend(this.aluno.data_nascimento);
      
      axios.post('http://localhost:8080/alunos', this.aluno)
        .then(() => {
          console.log('Aluno cadastrado com sucesso!');
          this.aluno = {
            nome: '',
            matricula: '',
            data_nascimento: '',
            turmaId: null
          };
          this.cadastroRealizado = true; 
          this.erroCadastro = false; 
          setTimeout(() => {
            this.cadastroRealizado = false;
          }, 3000);
        })
        .catch(error => {
          console.error('Erro ao cadastrar aluno:', error);
        });
    },
    formatarDataNascimento() {
      let inputValue = this.aluno.data_nascimento;
      if (inputValue.length === 2 || inputValue.length === 5) {
        inputValue += '/';
      }
      this.aluno.data_nascimento = inputValue;
    },
    formatarDataBackend(data) {
      const partes = data.split('/');
      return `${partes[2]}-${partes[1]}-${partes[0]}`;
    }
  }
};
</script>
