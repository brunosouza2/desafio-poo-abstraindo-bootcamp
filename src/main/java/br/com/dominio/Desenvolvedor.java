package br.com.dominio;

import br.com.dominio.exception.DesenvolvedorNaoMatriculadoEmConteudoException;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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

    public void inscreverBootcamp(Bootcamp bootcamp) {
        this.conteudosInscritos.addAll(bootcamp.getConteudos());
        bootcamp.inscreverDesenvolvedor(this);
    }

    public void progredir() {
        // 1- O desenvolvedor deve concluir um conteúdo antes de ir para o proximo
        this.conteudosInscritos.stream()
                .findFirst()
                .ifPresentOrElse(this::atualizaProgresso, DesenvolvedorNaoMatriculadoEmConteudoException::new);
    }

    public double calcularTotalXp() {
        return this.conteudosConcluidos.stream()
                .mapToDouble(Conteudo::calcularXp)
                .sum();
    }

    private void atualizaProgresso(Conteudo conteudo) {
        this.conteudosConcluidos.add(conteudo);
        this.conteudosInscritos.remove(conteudo);
    }

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
