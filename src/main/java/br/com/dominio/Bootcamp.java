package br.com.dominio;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toUnmodifiableSet;


public class Bootcamp {

    public static final int PRAZO_MINIMO = 45;
    private String nome;
    private String descricao;
    private LocalDate dataInicial = LocalDate.now();
    private final LocalDate dataFinal = LocalDate.now().plusDays(PRAZO_MINIMO);
    private Set<Desenvolvedor> desenvolvedoresInscritos;
    private Set<Conteudo> conteudos;

    public Bootcamp(String nome, String descricao, LocalDate dataInicial) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicial = dataInicial;
        this.desenvolvedoresInscritos = new HashSet<>();
        this.conteudos = new LinkedHashSet<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public Set<Desenvolvedor> getDesenvolvedoresInscritos() {
        return desenvolvedoresInscritos.stream().collect(toUnmodifiableSet());
    }

    public Set<Conteudo> getConteudos() {
        return conteudos.stream().collect(toUnmodifiableSet());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bootcamp bootcamp)) return false;
        return Objects.equals(getNome(), bootcamp.getNome()) && Objects.equals(getDescricao(), bootcamp.getDescricao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getDescricao());
    }

    @Override
    public String toString() {
        return "Bootcamp{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataInicial=" + dataInicial +
                ", dataFinal=" + dataFinal +
                ", desenvolvedoresInscritos=" + desenvolvedoresInscritos +
                ", conteudos=" + conteudos +
                '}';
    }
}
