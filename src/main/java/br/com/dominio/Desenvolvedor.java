package br.com.dominio;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toUnmodifiableSet;

public class Desenvolvedor {

    private String nome;
    private Set<Conteudo> conteudosInscritos;
    private Set<Conteudo> conteudosConcluidos;

    public Desenvolvedor(String nome) {
        this.nome = nome;
        this.conteudosInscritos = new LinkedHashSet<>();
        this.conteudosConcluidos = new LinkedHashSet<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos.stream().collect(toUnmodifiableSet());
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return conteudosConcluidos.stream().collect(toUnmodifiableSet());
    }

    public void inscreverBootcamp(Bootcamp bootcamp) {}

    public void progredir() {}

    public void calcularTotalXp() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Desenvolvedor that)) return false;
        return Objects.equals(getNome(), that.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNome());
    }

    @Override
    public String toString() {
        return "Desenvolvedor{" +
                "nome='" + nome + '\'' +
                ", conteudosInscritos=" + conteudosInscritos +
                ", conteudosConcluidos=" + conteudosConcluidos +
                '}';
    }
}
