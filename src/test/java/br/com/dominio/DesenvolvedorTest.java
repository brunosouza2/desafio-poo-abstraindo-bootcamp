package br.com.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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


}