package br.com.dominio;

import java.time.LocalDate;
import java.util.Objects;

public class Mentoria extends Conteudo implements Comparable<Mentoria> {

    private LocalDate data;

    public Mentoria(String titulo, String descricao, LocalDate data) {
        super(titulo, descricao);
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public double calcularXp() {
        return XP_PADRAO * 20d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mentoria mentoria)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getData(), mentoria.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getData());
    }

    @Override
    public String toString() {
        return "Mentoria{" +
                "titulo='" + getTitulo() + '\'' +
                ", descricao='" + getDescricao() + '\'' +
                ", data='" + getData() + '\'' +
                '}';
    }

    @Override
    public int compareTo(Mentoria o) {
        return getTitulo().compareTo(o.getTitulo());
    }
}
