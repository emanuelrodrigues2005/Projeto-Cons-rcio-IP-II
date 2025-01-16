

import java.time.LocalDate;
import java.util.ArrayList;

import Models.Contemplacao;
import Models.Contrato;

public class ContemplacaoRepository {
    private ArrayList<Contemplacao> contemplacoes;
    
    public ContemplacaoRepository() {
        this.contemplacoes = new ArrayList<>();
    }


    public void createContemplacao(Contrato contratoContemplacao){
        if (contratoContemplacao != null) {
            Contemplacao contemplacao = new Contemplacao(contratoContemplacao);
            contemplacoes.add(contemplacao);
            System.out.printf("\nContemplação criada e adicionada com sucesso!\n\n%sData: %s\n", contratoContemplacao.toString(), contemplacao.getDataContemplacao());
    }
    }

    public void getAllContemplacoes(){
        if(contemplacoes.isEmpty()){
            System.out.println("\nNão há Contemplações registradas!\n");
        } else {
            System.out.println("\nContratos Contemplados: \n");
            for(Contemplacao contemplacao : contemplacoes){
                System.out.println("/n" + contemplacao.getContratoContemplacao()
                + "(Data: " + contemplacao.getDataContemplacao() + ")\nId: " + contemplacao.getIdContemplacao());
            }
        }
    }
    public Contemplacao getContemplacaoByContrato(Contrato contrato){
        for(Contemplacao contemplacao : contemplacoes) {
            if(contemplacao.getContratoContemplacao() == contrato) {
                System.out.println("Contemplação associada a este contrato foi encontrada");
                return contemplacao;
            }
        }
        System.out.println("Contemplação associada a este contrato não foi encontrada");
        return null;
    }
    public Contemplacao getContemplacaoById(Contemplacao contemplacao_IN) {
        for(Contemplacao contemplacao : contemplacoes){
            if(contemplacao.getIdContemplacao() == contemplacao_IN.getIdContemplacao()) {
                return contemplacao;
            }
        }
        System.out.println("Contemplação não encontrada");
        return null;
    }

    public void updateContemplacao(Contemplacao contemplacao, LocalDate dataContemplacao) {
        if(dataContemplacao != null){
            getContemplacaoById(contemplacao).setDataContemplacao(dataContemplacao);
            System.out.println("Data atualizada com sucesso.");
            return;
        }
        System.out.println("Houve um erro no preenchimento de dados.");
    }

    public void deleteContemplacao(Contemplacao contemplacao_IN) {
        for(Contemplacao contemplacao : contemplacoes){
            if(contemplacao.getIdContemplacao() == contemplacao_IN.getIdContemplacao()) {
                contemplacoes.remove(contemplacao);
                System.out.println("Contemplação removida com sucesso.");
                return;
            }
        }
        System.out.println("Não foi possível remover a contemplação desejada.");
    }
}
