package br.com.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Bootcamp")
class BootcampTest {

    private Bootcamp bootcamp;

    @BeforeEach
    public void beforeEach() {
        bootcamp = new Bootcamp("Backend Java DIO - 2024",
                "Bootcamp focado no aprendizado da tecnologia java.", LocalDate.now());
    }

    @Test
    @DisplayName("Deve inscrever desenvolvedor no bootcamp")
    void deveInscreverDesenvolvedorNoBootcamp() {
        var desenvolvedor = new Desenvolvedor("Bruno Souza");
        var segundoDesenvolvedor = new Desenvolvedor("Luiz Souza");

        Desenvolvedor[] collectionEsperada = new Desenvolvedor[]{
                desenvolvedor,
                segundoDesenvolvedor,
        };

        bootcamp.inscreverDesenvolvedor(desenvolvedor);
        bootcamp.inscreverDesenvolvedor(segundoDesenvolvedor);

        Desenvolvedor[] desenvolvedoresEscritos = bootcamp.getDesenvolvedoresInscritos().stream().
                sorted().
                toList().
                toArray(new Desenvolvedor[0]);

        assertArrayEquals(collectionEsperada, desenvolvedoresEscritos);
    }



}