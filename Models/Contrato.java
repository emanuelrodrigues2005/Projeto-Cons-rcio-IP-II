package Models;

import Enums.StatusContratoEnum;

import java.time.LocalDate;
import java.util.ArrayList;

public class Contrato {
    private Cliente cliente;
    private GrupoConsorcio grupoAssociado;
    private int parcelasPagas;
    private double saldoDevedor;
    private double saldoDevolucao;
    private LocalDate dataContemplação;
    private StatusContratoEnum statusContrato;
    private ArrayList<Boleto> listaBoletosPagos;
    private ArrayList<Boleto> listaBoletosAtrasados;

    public Contrato(Cliente cliente, GrupoConsorcio grupoAssociado) {
        this.cliente = cliente;
        this.grupoAssociado = grupoAssociado;
        this.parcelasPagas = 0;
        this.saldoDevedor = grupoAssociado.getValorTotal() + (grupoAssociado.getValorTotal() * grupoAssociado.getTaxaAdmin());
        this.statusContrato = StatusContratoEnum.ATIVO;
        this.saldoDevolucao = 0;
        this.listaBoletosPagos = new ArrayList<>();
        this.listaBoletosAtrasados = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public GrupoConsorcio getGrupoAssociado() {
        return grupoAssociado;
    }

    public int getParcelasPagas() {
        return parcelasPagas;
    }

    public void setParcelasPagas(int numParcelas) {
        this.parcelasPagas = numParcelas;
    }

    public double getSaldoDevedor() {
        return saldoDevedor;
    }

    public void setSaldoDevedor(double novoSaldo) {
        this.saldoDevedor = novoSaldo;
    }

    public LocalDate getDataContemplação() {
        return dataContemplação;
    }

    public void setDataContemplação(LocalDate dataContemplação) {
        this.dataContemplação = dataContemplação;
    }

    public StatusContratoEnum getStatusContrato() {
        return statusContrato;
    }

    public void setStatusContrato(StatusContratoEnum status) {
        this.statusContrato = status;
    }

    public ArrayList<Boleto> getListaBoletosPagos() {
        return listaBoletosPagos;
    }

    public ArrayList<Boleto> getListaBoletosAtrasados() {
        return listaBoletosAtrasados;
    }

    public void addBoleto(Boleto novoBoleto) {
        this.listaBoletosPagos.add(novoBoleto);
    }


    public double getSaldoDevolucao() {
        return saldoDevolucao;
    }

    public void setSaldoDevolucao(double novoSaldoDevolucao) {
        this.saldoDevolucao = novoSaldoDevolucao;
    } 

    public String toString() {
        return "Cliente: " + this.getCliente().getNome() + "\nCPF: " + this.getCliente().getCpf() + "\nGrupo associado: " + this.getGrupoAssociado().getNomeGrupo() + "\nStatus: " + this.getStatusContrato() + "\nParcelas pagas: " + this.getParcelasPagas() + "\nSaldo devedor: " + this.getSaldoDevedor() + "\n\n";
    }
}