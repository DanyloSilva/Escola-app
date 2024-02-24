package escola.apiescola.controller;

import escola.apiescola.model.Alunos;
import escola.apiescola.model.Turmas;
import escola.apiescola.repository.AlunosRepository;
import escola.apiescola.repository.TurmasRepository;
import escola.apiescola.service.alunos.DTOAtualizacaoAlunos;
import escola.apiescola.service.alunos.DTOCadastrarAlunos;
import escola.apiescola.service.alunos.DTOListagemAlunos;
import escola.apiescola.service.turmas.DTOAtualizacaoTurmas;
import escola.apiescola.service.turmas.DTOCadastroTurmas;
import escola.apiescola.service.turmas.DTOListagemTurmas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("turmas")
@CrossOrigin(origins = "http://localhost:3000")
public class TurmasController {
    @Autowired
    private TurmasRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DTOCadastroTurmas dados, UriComponentsBuilder uriBuilder ){
        var turmas = new Turmas(dados);
        repository.save(turmas);
        var uri = uriBuilder.path("/alunos/{id}").buildAndExpand(turmas.getId()).toUri();

        return ResponseEntity.created(uri).body(new DTOListagemTurmas(turmas));

    }

    @GetMapping("/{id}/alunos")
    public ResponseEntity<List<DTOListagemAlunos>> listarAlunosPorTurma(@PathVariable Long id) {
        Optional<Turmas> turmaOptional = repository.findById(id);
        if (turmaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Turmas turma = turmaOptional.get();
        List<Alunos> alunos = turma.getAlunos();
        List<DTOListagemAlunos> alunosDTO = alunos.stream()
                .map(aluno -> new DTOListagemAlunos(aluno))
                .collect(Collectors.toList());

        return ResponseEntity.ok(alunosDTO);
    }


    @GetMapping
    public ResponseEntity<List<DTOListagemTurmas>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).stream().map(DTOListagemTurmas::new).toList();
        return ResponseEntity.ok(page);
    }


// pega so os id true
    //@GetMapping
   // public ResponseEntity<List<DTOListagemTurmas>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
  //      var page =  repository.findAllByAtivoTrue(paginacao).stream().map(DTOListagemTurmas::new).toList();
 //       return ResponseEntity.ok(page);

  //  }

    @GetMapping("/{id}")
    public ResponseEntity<DTOListagemTurmas> obterPorId(@PathVariable Long id) {
        Optional<Turmas> turmaOptional = repository.findById(id);
        if (turmaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        DTOListagemTurmas turmaDTO = new DTOListagemTurmas(turmaOptional.get());
        return ResponseEntity.ok(turmaDTO);
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DTOAtualizacaoTurmas dados) {
        var turmas = repository.getReferenceById(dados.id());
        turmas.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DTOListagemTurmas(turmas));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var turmas = repository.getReferenceById(id);
        turmas.excuir();
        return ResponseEntity.noContent().build();
    }
}
