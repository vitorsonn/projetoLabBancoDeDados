package model;

import enums.Cargo;

public class Politico {
    private int id;
    private String nome;
    private String partido;
    private Cargo cargo;
    private String cpf;

    public Politico() {}

    public Politico(int id, String nome, String partido, Cargo cargo, String cpf) {
        this.id = id;
        this.nome = nome;
        this.partido = partido;
        this.cargo = cargo;
        this.cpf = cpf;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getPartido() { return partido; }
    public void setPartido(String partido) { this.partido = partido; }

    public Cargo getCargo() { return cargo; }
    public void setCargo(Cargo cargo) { this.cargo = cargo; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    @Override
    public String toString() {
        return nome + " (" + partido + " - " + cargo + ")";
    }
}