package escola.apiescola.controller;

import escola.apiescola.model.Alunos;
import escola.apiescola.model.Turmas;
import escola.apiescola.repository.AlunosRepository;
import escola.apiescola.repository.TurmasRepository;
import escola.apiescola.service.alunos.DTOAtualizacaoAlunos;
import escola.apiescola.service.alunos.DTOCadastrarAlunos;
import escola.apiescola.service.alunos.DTOListagemAlunos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("alunos")
@CrossOrigin(origins = "http://localhost:3000")
public class AlunosController {
    @Autowired
    private AlunosRepository repository;
    @Autowired
    private TurmasRepository turmasRepository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DTOCadastrarAlunos dados, UriComponentsBuilder uriBuilder){
        Turmas turma = turmasRepository.findById(dados.turmaId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        Alunos aluno = new Alunos(dados);
        aluno.setTurmaId(dados.turmaId());
        repository.save(aluno);
        URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(new DTOListagemAlunos(aluno));
    }

    @GetMapping
    public ResponseEntity<List<DTOListagemAlunos>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page =  repository.findAllByAtivoTrue(paginacao).stream().map(DTOListagemAlunos::new).toList();
        return ResponseEntity.ok(page);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOListagemAlunos> obterPorId(@PathVariable Long id) {
        Optional<Alunos> alunoOptional = repository.findById(id);
        if (alunoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        DTOListagemAlunos alunoDTO = new DTOListagemAlunos(alunoOptional.get());
        return ResponseEntity.ok(alunoDTO);
    }

    @GetMapping("/turmas/{id}/alunos")
    public ResponseEntity<List<Alunos>> listarAlunosPorTurma(@PathVariable Long id) {
        List<Alunos> alunos = repository.findByTurmaId(id);
        return ResponseEntity.ok(alunos);
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarInformacoes(@PathVariable Long id, @RequestBody @Valid DTOAtualizacaoAlunos dados) {
        Optional<Alunos> alunoOptional = repository.findById(id);
        if (alunoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Alunos aluno = alunoOptional.get();
        aluno.atualizarInformacoes(dados);
        repository.save(aluno);
        return ResponseEntity.ok(new DTOListagemAlunos(aluno));
    }





    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var alunos = repository.getReferenceById(id);
        alunos.excuir();
        return ResponseEntity.noContent().build();
    }

}
