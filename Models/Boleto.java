package Models;

import Enums.StatusBoletoEnum;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Boleto {
    private static final Set<Integer> idsUsados = new HashSet<>(); // Armazena IDs já gerados
    private static final Random random = new Random();
    // Atributos
    private int idBoleto;
    private Contrato contratoBoleto;
    private double valorBoleto;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private LocalDate dataEmissao;
    private StatusBoletoEnum statusBoleto;
    private int numeroParcela;

    // Construtor
    public Boleto(Contrato contratoBoleto, LocalDate dataEmissao, LocalDate dataVencimento, int numeroParcela) {
        this.idBoleto = gerarIdUnico();
        this.contratoBoleto = contratoBoleto;
        this.numeroParcela = numeroParcela;
        this.valorBoleto = contratoBoleto.getGrupoAssociado().getValorParcela();
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.statusBoleto = StatusBoletoEnum.PENDENTE; // Status inicial
        this.dataPagamento = null; // O boleto ainda não foi pago
    }

    // Metodo para gerar um ID único com 5 dígitos
    private int gerarIdUnico() {
        int id;
        do {
            id = 10000 + random.nextInt(90000); // Gera número aleatório entre 10000 e 99999
        } while (idsUsados.contains(id)); // Verifica se o ID já foi usado
        idsUsados.add(id); // Adiciona o ID na lista de usados
        return id;
    }

    // Getters e Setters
    public int getIdBoleto() { return idBoleto; }

    public Contrato getContratoBoleto() { return contratoBoleto; }

    public double getValorBoleto() { return valorBoleto; }

    public LocalDate getDataVencimento() { return dataVencimento; }

    public LocalDate getDataPagamento() { return dataPagamento; }

    public LocalDate getDataEmissao() { return dataEmissao; }

    public StatusBoletoEnum getStatusBoleto() { return statusBoleto; }

    public int getNumeroParcela() { return numeroParcela; }

    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }

    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }

    public void setDataEmissao(LocalDate dataEmsissao) { this.dataEmissao = dataEmsissao; }

    public void setStatusBoleto(StatusBoletoEnum statusBoleto) { this.statusBoleto = statusBoleto; }
}