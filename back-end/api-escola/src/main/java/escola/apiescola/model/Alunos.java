package escola.apiescola.model;
import escola.apiescola.repository.TurmasRepository;
import escola.apiescola.service.alunos.DTOAtualizacaoAlunos;
import escola.apiescola.service.alunos.DTOCadastrarAlunos;
import helps.ApplicationContextProvider;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Table(name = "alunos")
@Entity(name = "Alunos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Alunos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String matricula;
    private LocalDate data_nascimento;
    private  Boolean ativo = true;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turma_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Turmas turmas;


    @Column(name = "turma_id")
    private Long turmaId;
    public Alunos (DTOCadastrarAlunos dados){
        this.nome = dados.nome();
        this.matricula = dados.matricula();
        this.data_nascimento = dados.data_nascimento();
        this.turmaId = dados.turmaId();
    }

    public void atualizarInformacoes(DTOAtualizacaoAlunos dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.matricula() != null) {
            this.matricula = dados.matricula();
        }
        if (dados.data_nascimento() != null) {
            this.data_nascimento = dados.data_nascimento();
        }
        if (dados.turmaId() != null) {
            this.turmaId = dados.turmaId();
        }
    }




    public void excuir() { this.ativo = false;}
}
