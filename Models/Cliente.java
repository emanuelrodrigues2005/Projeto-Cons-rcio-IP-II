package Models;

import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private ArrayList<Contrato> contratos;

    public Cliente(String nome, String cpf, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.contratos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Contrato> getContratos() {
        return contratos;
    }
}