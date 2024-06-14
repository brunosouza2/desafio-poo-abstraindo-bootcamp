package br.com.dominio;

import java.util.Objects;

public class Curso extends Conteudo implements Comparable<Curso> {

    private int cargaHoraria;

    public Curso(String titulo, String descricao, int cargaHoraria) {
        super(titulo, descricao);
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public double calcularXp() {
        return XP_PADRAO * getCargaHoraria();
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curso curso)) return false;
        if (!super.equals(o)) return false;
        return getCargaHoraria() == curso.getCargaHoraria();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCargaHoraria());
    }

    @Override
    public String toString() {
        return "Curso{" +
                "titulo='" + getTitulo() + '\'' +
                ", descricao='" + getDescricao() + '\'' +
                ", cargaHoraria=" + getCargaHoraria() +
                '}';
    }

    @Override
    public int compareTo(Curso o) {
        return getTitulo().compareTo(o.getTitulo());
    }
}
