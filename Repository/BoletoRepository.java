package Repository;

import Models.Boleto;
import Models.Contrato;
import Enums.StatusBoletoEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoletoRepository {
    private List<Boleto> boletos = new ArrayList<>();

    public void create(Boleto boleto) {
        boletos.add(boleto);
    }

    public Boleto read(int idBoleto) {
        for (Boleto boleto : boletos) {
            if (boleto.getIdBoleto() == idBoleto) {
                return boleto;
            }
        }
        System.out.println("Nenhum boleto com ID " + idBoleto + " encontrado.");
        return null;
    }

    public void update(int idBoleto, Boleto boletoAtualizado) {
        for (Boleto boleto : boletos) {
            if (boleto.getIdBoleto() == idBoleto) {
                if (boletoAtualizado.getDataVencimento() != null) {
                    boleto.setDataVencimento(boletoAtualizado.getDataVencimento());
                }
                if (boletoAtualizado.getDataEmissao() != null) {
                    boleto.setDataEmissao(boletoAtualizado.getDataEmissao());
                }
                if (boletoAtualizado.getStatusBoleto() != null) {
                    boleto.setStatusBoleto(boletoAtualizado.getStatusBoleto());
                }
                System.out.println("Boleto com ID " + idBoleto + " atualizado com sucesso.");
                return;
            }
        }
        System.out.println("Nenhum boleto com ID " + idBoleto + " encontrado para atualização.");
    }

    public void delete(int idBoleto) {
        for (int i = 0; i < boletos.size(); i++) {
            Boleto boleto = boletos.get(i);
            if (boleto.getIdBoleto() == idBoleto) {
                boletos.remove(i);
                System.out.println("Boleto com ID " + idBoleto + " removido com sucesso.");
                return;
            }
        }
        System.out.println("Nenhum boleto com ID " + idBoleto + " encontrado para exclusão.");
    }

    // Lista todos os boletos
    public List<Boleto> listAll() {
        return new ArrayList<>(boletos);
    }

    // Verifica vencimento de todos os boletos no repositório
    public void verificarVencimentos() {
        for (Boleto boleto : boletos) {
            if (boleto.getStatusBoleto() == StatusBoletoEnum.PENDENTE && LocalDate.now().isAfter(boleto.getDataVencimento())) {
                boleto.setStatusBoleto(StatusBoletoEnum.ATRASADO);
                boleto.getContratoBoleto().getListaBoletosAtrasados().add(boleto);
                System.out.println("Boleto ID " + boleto.getIdBoleto() + " atualizado para ATRASADO.");
            } else if (boleto.getStatusBoleto() == StatusBoletoEnum.ATRASADO && LocalDate.now().isBefore(boleto.getDataVencimento())) {
                boleto.setStatusBoleto(StatusBoletoEnum.PENDENTE);
                boleto.getContratoBoleto().getListaBoletosAtrasados().remove(boleto);
                System.out.println("Boleto ID " + boleto.getIdBoleto() + " atualizado para PENDENTE.");
            }
        }
    }

    // Exibe informações de um boleto específico
    public void printarBoleto(int idBoleto) {
        for (Boleto boleto : boletos) {
            if (boleto.getIdBoleto() == idBoleto) {
                verificarVencimentos(); // Verifica vencimentos antes de exibir informações
                System.out.println("\n=== Informações do Boleto ===");
                System.out.println("Cpf do Cliente: " + (boleto.getContratoBoleto() != null ? boleto.getContratoBoleto().getCliente().getCpf() : "Nenhum contrato associado"));
                System.out.println("ID do Boleto: " + boleto.getIdBoleto());
                System.out.println("Parcela: " + boleto.getNumeroParcela());
                System.out.println("Valor a ser pago: R$ " + boleto.getValorBoleto());
                System.out.println("Data de Emissão: " + boleto.getDataEmissao());
                System.out.println("Data de Vencimento: " + boleto.getDataVencimento());
                System.out.println("Data de Pagamento: " + (boleto.getDataPagamento() != null ? boleto.getDataPagamento() : "Não pago"));
                System.out.println("Status do Boleto: " + boleto.getStatusBoleto());
                return;
            }
        }
        System.out.println("Boleto com ID " + idBoleto + " não encontrado.");
    }

    public void realizarPagamento(int idBoleto) {
        for (Boleto boleto : boletos) {
            if (boleto.getIdBoleto() == idBoleto) {
                Contrato contrato = boleto.getContratoBoleto();

                if (boleto.getStatusBoleto() == StatusBoletoEnum.PENDENTE) {
                    boleto.setStatusBoleto(StatusBoletoEnum.PAGO);
                    boleto.setDataPagamento(LocalDate.now());
                    contrato.getListaBoletosPagos().add(boleto);
                    System.out.println("Boleto pago com sucesso.");
                } else if (boleto.getStatusBoleto() == StatusBoletoEnum.ATRASADO) {
                    boleto.setStatusBoleto(StatusBoletoEnum.PAGO);
                    boleto.setDataPagamento(LocalDate.now());
                    contrato.getListaBoletosAtrasados().remove(boleto); // Remove da lista de atrasados
                    contrato.getListaBoletosPagos().add(boleto); // Adiciona à lista de pagos
                    System.out.println("Boleto atrasado pago com sucesso. Multa aplicada.");
                } else {
                    System.out.println("Este boleto já foi pago anteriormente.");
                }
                return;
            }
        }
        System.out.println("Boleto com ID " + idBoleto + " não encontrado.");
    }
}