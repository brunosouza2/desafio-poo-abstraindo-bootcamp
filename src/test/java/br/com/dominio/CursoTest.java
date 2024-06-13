package br.com.dominio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Curso")
class CursoTest {

    @Test
    @DisplayName("Calcula XP com base na carga horária")
    void deveCalcularXpComACargaHoraria() {
        var curso = new Curso("Especialista Java", "Mergulhando fundo em java", 60);
        // O curso calcula a xp de acordo com a carga horária, sendo: Xp padrão = 10d então XP_PADRAO * cargaHoraria
        double xpEsperado = 600d;

        double totalXp = curso.calcularXp();

        assertEquals(xpEsperado, totalXp);
    }

}