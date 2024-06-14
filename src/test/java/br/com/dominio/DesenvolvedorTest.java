package br.com.dominio;

import br.com.dominio.exception.DesenvolvedorNaoMatriculadoEmConteudoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Desenvolvedor")
class DesenvolvedorTest {

    private Bootcamp bootcamp;
    private Desenvolvedor desenvolvedor;


    @BeforeEach
    public void beforeEach() {
        bootcamp = new Bootcamp("Backend Java",
                "Mergulhe no aprendizado da tecnologia java", LocalDate.now());
        desenvolvedor = new Desenvolvedor("Bruno Souza");
    }

    @Nested
    class InscreverBootcamp {

        @Test
        @DisplayName("Cadastra conteúdos para o desenvolvedor")
        void deveCadastrarConteudosParaODesenvolvedor() {
            var cursoUm = new Curso("Especialista Angular", "Mergulhe em angular", 20);
            var cursoDois = new Curso("Especialista Java", "Mergulhe em java", 20);

            Conteudo[] conteudosEsperados = new Conteudo[] {
                    cursoUm,
                    cursoDois
            };

            bootcamp.adicionarConteudos(cursoUm, cursoDois);
            desenvolvedor.inscreverBootcamp(bootcamp);

            Conteudo[] conteudosCadastrados = desenvolvedor.getConteudosInscritos().stream()
                    .sorted()
                    .toList()
                    .toArray(new Conteudo[0]);

            assertArrayEquals(conteudosEsperados, conteudosCadastrados);
        }

        @Test
        @DisplayName("Cadastra vendedor no bootcamp")
        void deveCadastrarDesenvolvedorNoBootcamp() {
            var desenvolvedorEsperado = desenvolvedor;

            desenvolvedor.inscreverBootcamp(bootcamp);

            var desenvolvedorInscrito = bootcamp.getDesenvolvedoresInscritos().stream()
                    .filter(dev -> dev.equals(desenvolvedorEsperado))
                    .findFirst()
                    .orElseGet(() -> null);

            assertEquals(desenvolvedorEsperado, desenvolvedorInscrito);
        }

    }

    @Nested
    class Progressao {

        private Conteudo primeiroConteudo;
        private Conteudo segundoConteudo;

        @BeforeEach
        public void beforeEach() {
            primeiroConteudo = new Curso("Javascript Avançado", "Javascript super hiper avançado",
                    60);
            segundoConteudo = new Mentoria("Avançando na Carreira Backend Java", "Mentoria Completa",
                    LocalDate.now());

            bootcamp.adicionarConteudo(primeiroConteudo);
            bootcamp.adicionarConteudo(segundoConteudo);

            desenvolvedor.inscreverBootcamp(bootcamp);
        }

        @Test
        @DisplayName("Atualiza cursos inscritos e concluídos do usuário")
        void deveMoverCursoDeInscritoParaConcluido() {
            Conteudo primeiroConteudo = new Curso("Javascript Avançado", "Javascript super hiper avançado", 60);
            Conteudo segundoConteudo = new Mentoria("Avançando na Carreira Backend Java", "Mentoria Completa", LocalDate.now());

            bootcamp.adicionarConteudo(primeiroConteudo);
            bootcamp.adicionarConteudo(segundoConteudo);
            desenvolvedor.inscreverBootcamp(bootcamp);

            desenvolvedor.progredir();

            List<Conteudo> listaCursosInscritos = new ArrayList<>(desenvolvedor.getConteudosInscritos());
            List<Conteudo> listaCursosConcluidos = new ArrayList<>(desenvolvedor.getConteudosConcluidos());


            assertEquals(1, listaCursosConcluidos.size(), "Quantidade de cursos concluídos incorreta.");
            assertEquals(primeiroConteudo, listaCursosConcluidos.get(0), "Primeiro curso concluído incorreto.");

            assertEquals(1, listaCursosInscritos.size(), "Quantidade de cursos inscritos incorreta.");
            assertEquals(segundoConteudo, listaCursosInscritos.get(0), "Primeiro curso inscrito incorreto.");
        }


        @Test
        @DisplayName("Lança exceção quando o usuário não está inscrito em nenhum curso.")
        void lancaExceptionSeNaoHouverCursosInscritos() {
            var exceptionEsperada = DesenvolvedorNaoMatriculadoEmConteudoException.class;

            desenvolvedor = new Desenvolvedor("Luiz");
            bootcamp = new Bootcamp("Especialista Angular",
                    "Angular melhor framework", LocalDate.now());
            desenvolvedor.inscreverBootcamp(bootcamp);

            assertThrows(exceptionEsperada, () -> desenvolvedor.progredir());
        }
    }

    @Nested
    class XP {

        private Conteudo primeiroConteudo;
        private Conteudo segundoConteudo;

        @BeforeEach
        public void beforeEach() {
            primeiroConteudo = new Curso("Javascript Avançado", "Javascript super hiper avançado",
                    60);
            segundoConteudo = new Mentoria("Avançando na Carreira Backend Java", "Mentoria Completa",
                    LocalDate.now());

            bootcamp.adicionarConteudo(primeiroConteudo);
            bootcamp.adicionarConteudo(segundoConteudo);
            desenvolvedor.inscreverBootcamp(bootcamp);

            // Termina primeiro conteúdo
            desenvolvedor.progredir();
            // Termina segundo conteúdo
            desenvolvedor.progredir();
        }

        @Test
        @DisplayName("Deve calcular o XP obtido em todos os cursos")
        void deveCalcularTotalDeXp() {
            // xp padrão = 10d;
            // cálculo de xp de curso -> xp * cargaHoraria
            // cálculo de xp de mentoria -> xp * 20d
            double xpPrimeiroCurso = primeiroConteudo.calcularXp();
            double xpSegundoCurso = segundoConteudo.calcularXp();
            double totalXpEsperado =  xpSegundoCurso + xpPrimeiroCurso;

            double xpTotalDesenvolvedor = desenvolvedor.calcularTotalXp();

            assertEquals(totalXpEsperado, xpTotalDesenvolvedor,
                    "A quantidade total de XP do desenvolvedor não é a esperada.");
        }

    }
}