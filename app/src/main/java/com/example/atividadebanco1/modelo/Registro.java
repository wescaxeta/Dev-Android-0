package com.example.atividadebanco1.modelo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Registro implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    public String nome;
    public String imagem;
    public String pensamento;

    public Registro() {
    }

    public Registro(int id, String nome, String imagem, String pensamento) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
        this.pensamento = pensamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getPensamento() {
        return pensamento;
    }

    public void setPensamento(String pensamento) {
        this.pensamento = pensamento;
    }
}
