package escola.apiescola.model;
import escola.apiescola.service.alunos.DTOAtualizacaoAlunos;
import escola.apiescola.service.alunos.DTOCadastrarAlunos;
import escola.apiescola.service.turmas.DTOAtualizacaoTurmas;
import escola.apiescola.service.turmas.DTOCadastroTurmas;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

@Table(name = "turmas")
@Entity(name = "Turmas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Turmas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalTime horario_inicio;
    private boolean ativo;


    @OneToMany(mappedBy = "turmas", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Alunos> alunos;







    public Turmas (DTOCadastroTurmas dados){
        this.ativo = true;
        this.nome = dados.nome();
       this.horario_inicio = dados.horario_inicio();

    }
    public void atualizarInformacoes(DTOAtualizacaoTurmas dados) {

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.horario_inicio() != null) {
            this.horario_inicio = dados.horario_inicio();
        }



    }

    public void excuir() { this.ativo = false;}


    public Stream<Turmas> stream() {
        return Stream.of(this);
    }
    public Long getId() {
        return id;
    }

}

