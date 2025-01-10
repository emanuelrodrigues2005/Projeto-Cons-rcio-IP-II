package Repository;

import java.util.ArrayList;

import Enums.StatusBoletoEnum;
import Enums.StatusContratoEnum;
import Models.Contrato;
import Models.GrupoConsorcio;
import Models.Cliente;
import Models.Boleto;

public class ContratoRepository {
    private ArrayList<Contrato> contratos;
    

    public ContratoRepository() {
        this.contratos = new ArrayList<>();
    }

    public void getAllContratos() {
        if (contratos.isEmpty()) {
            System.out.println("Nenhum contrato foi criado");
        } else {
            for(Contrato contrato : contratos) {
                System.out.println(contrato);
            }
        }
        System.out.println("Todos os contratos foram listados acima.");
    }

    public void getAllContratosByCPF_S(String cpf) {
        for (Contrato contrato : contratos) {
            if (contrato.getCliente().getCpf().equalsIgnoreCase(cpf)) {
                System.out.println(contrato.toString());
            } 
        }
        System.out.println("Todos os contratos do cliente foram listados acima.");
    }
    
    public Contrato getContratoByCPFNomeGrupo(String cpf, String nomeGrupo) {
        for (Contrato contrato : contratos) {
            if (contrato.getCliente().getCpf().equalsIgnoreCase(cpf) && contrato.getGrupoAssociado().getNomeGrupo().equalsIgnoreCase(nomeGrupo)) {
                return contrato;
            }
        }
        System.out.println("Cliente não encontrado");
        return null;
    }

    public String getContratosByNomeGrupo(String nomeGrupo) {
        for (Contrato contrato : contratos) {
            if(contrato.getGrupoAssociado().getNomeGrupo().equalsIgnoreCase(nomeGrupo)) {
                int counter = 0;
                for(Contrato contratoListado : contrato.getGrupoAssociado().getListaContratos()) {
                    System.out.println(contratoListado);
                    counter++;
                }
                if (counter > 0) {
                    return "Todos os contratos do grupo foram listados acima.";
                }
                return "O grupo não possui contratos ativos.";
            }
        }
        return "Grupo não encontrado.";
    }

    public void createContrato(Cliente cliente, GrupoConsorcio grupoAssociado) {
        Contrato contrato = new Contrato(cliente, grupoAssociado);
        if (getContratoByCPFNomeGrupo(cliente.getCpf(), grupoAssociado.getNomeGrupo()) == null)  {
            contratos.add(contrato);
            grupoAssociado.getListaContratos().add(contrato);
            cliente.getContratos().add(contrato);
            System.out.printf("Contrato do cliente %s, pertencente ao grupo %s foi criado com sucesso.\n", cliente.getNome(), grupoAssociado.getNomeGrupo());
        } else {
            System.out.println("Contrato pertencente a este cliente neste grupo já existe.");
        }
    }

    private void updateContrato(String cpf, int novasParcelas, double novoSaldoDevedor, Boleto boleto) {
        for(Contrato contrato : contratos) {
            if(contrato.getCliente().getCpf().equalsIgnoreCase(cpf)) {
                if (novasParcelas > contrato.getParcelasPagas()) {
                    contrato.setParcelasPagas(novasParcelas);
                }
                if (novoSaldoDevedor < contrato.getSaldoDevedor()) {
                    contrato.setSaldoDevedor(novoSaldoDevedor);
                }
                if (boleto != null) {
                    if (contrato.getListaBoletosAtrasados().contains(boleto)) {
                        contrato.getListaBoletosAtrasados().remove(boleto);
                    }
                    contrato.addBoleto(boleto);
                }
            }
        }
    }

    public void pagarParcela(String cpf, String nomeGrupo, Boleto boleto) {
        if (getContratoByCPFNomeGrupo(cpf, nomeGrupo) != null & !(getContratoByCPFNomeGrupo(cpf, nomeGrupo).getListaBoletosPagos().contains(boleto))) {
            if (boleto.getStatusBoleto() == StatusBoletoEnum.PAGO) {
                Contrato contrato = getContratoByCPFNomeGrupo(cpf, nomeGrupo);
                updateContrato(cpf, contrato.getParcelasPagas() + 1, contrato.getSaldoDevedor() - contrato.getGrupoAssociado().getValorParcela(), boleto);
                System.out.println("A parcela foi paga com sucesso.");
            } else if (boleto.getStatusBoleto() == StatusBoletoEnum.PENDENTE) {
                System.out.println("O boleto ainda está pendente de pagamento. Operação negada");
            } else if (boleto.getStatusBoleto() == StatusBoletoEnum.ATRASADO) {
                Contrato contrato = getContratoByCPFNomeGrupo(cpf, nomeGrupo);
                boleto.setStatusBoleto(StatusBoletoEnum.PAGO);
                updateContrato(cpf, contrato.getParcelasPagas() + 1, contrato.getSaldoDevedor() - contrato.getGrupoAssociado().getValorParcela(), boleto);
                System.out.println("O boleto atrasado foi pago com uma multa.");
            } 
        } else {
            System.out.println("O cliente não foi encontrado ou o boleto já foi pago.");
        }
    }

    public void cancelarContrato(String cpf, String nomeGrupo) {
        if (getContratoByCPFNomeGrupo(cpf, nomeGrupo) != null) {
            Contrato contrato = getContratoByCPFNomeGrupo(cpf, nomeGrupo);
            contrato.setStatusContrato(StatusContratoEnum.ENCERRADO);
            double saldoDevolucao = (contrato.getGrupoAssociado().getValorTotal() / contrato.getGrupoAssociado().getNumeroParticipantes()) * contrato.getParcelasPagas();
            contrato.setSaldoDevolucao(saldoDevolucao);
            System.out.printf("Contrato cancelado com sucesso, suas parcelas pagas serão devolvidas após o término do consórcio.\nTotal a ser devolvido: %.2f\n", saldoDevolucao);
        } else {
            System.out.println("Contrato não encontrado não pôde ser cancelado.");
        }
    }

    public void deleteContrato(String cpf, String nomeGrupo) {
        Contrato contrato = getContratoByCPFNomeGrupo(cpf, nomeGrupo);
        if (contrato != null) {
            contratos.remove(contrato);
            System.out.println("Contrato removido com sucesso.");
        } else {
            System.out.println("Contrato não encontrado não pôde ser removido.");
        }
    }
}