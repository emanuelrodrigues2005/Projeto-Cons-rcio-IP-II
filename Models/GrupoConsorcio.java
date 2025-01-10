package Models;

import Enums.StatusGrupoConsorcioEnum;

import java.util.ArrayList;

public class GrupoConsorcio {
    private String nomeGrupo;
    private int numeroParticipantes;
    private double valorTotal;
    private double taxaAdmin;
    private double valorParcela;
    private ArrayList<Contrato> listaContratos;
    private ArrayList<Contrato> listaContratosContemplados;
    private StatusGrupoConsorcioEnum statusGrupoConsorcio;

    public GrupoConsorcio(String nomeGrupo, int numeroParticipantes, double valorTotal, double taxaAdmin) {
        this.nomeGrupo = nomeGrupo;
        this.numeroParticipantes = numeroParticipantes;
        this.valorTotal = valorTotal;
        this.taxaAdmin = taxaAdmin;
        this.valorParcela = ((getValorTotal() + getValorTotal() * getTaxaAdmin()) / getNumeroParticipantes());
        this.statusGrupoConsorcio = StatusGrupoConsorcioEnum.ATIVO;
        this.listaContratos = new ArrayList<>();
        this.listaContratosContemplados = new ArrayList<>();
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public int getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getTaxaAdmin() {
        return taxaAdmin;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public ArrayList<Contrato> getListaContratos() {
        return listaContratos;
    }

    public ArrayList<Contrato> getListaContratosContemplados() {
        return listaContratosContemplados;
    }

    public StatusGrupoConsorcioEnum getStatusGrupoConsorcio() {
        return statusGrupoConsorcio;
    }
}