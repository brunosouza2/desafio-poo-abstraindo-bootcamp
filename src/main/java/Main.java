import br.com.dominio.Curso;
import br.com.dominio.Mentoria;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        var curso = new Curso("Especialista Java", "Especialização Java", 60);
        var mentoria = new Mentoria("Melhorando em Backend",
                "Subindo na cerreira backend java", LocalDate.now());

        System.out.println(curso);
        System.out.println(mentoria);
    }

}
