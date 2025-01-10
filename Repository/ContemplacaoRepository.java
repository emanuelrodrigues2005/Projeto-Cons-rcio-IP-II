package Repository;

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
                System.out.println(contemplacao.getContratoContemplacao()
                + "(Data: " + contemplacao.getDataContemplacao() + ")\nId: " + contemplacao.getIdContemplacao());
            }
        }
    }
    public void getContemplacao(Contrato contrato){
        Contemplacao temp = null;
        for(Contemplacao contemplacao : contemplacoes){
            if(contemplacao.getContratoContemplacao() == contrato) {
                temp = contemplacao;
            }
        }
        if(temp != null){
            System.out.println(temp);
        } else {
            System.out.println("\nNão existe registro de contemplações para este contrato!\n");
        }
    }
    public Contemplacao getContemplacaoById(int idContemplacao) {
        for(Contemplacao contemplacao : contemplacoes){
            if(contemplacao.getIdContemplacao() == idContemplacao) {
                return contemplacao;
            }
        }
        System.out.println("Contemplação não encontrada");
        return null;
    }

    public void updateContemplacao(int idContemplacao, LocalDate dataContemplacao) {
        if(dataContemplacao != null){
            getContemplacaoById(idContemplacao).setDataContemplacao(dataContemplacao);
            System.out.println("Data atualizada com sucesso.");
            return;
        }
        System.out.println("Houve um erro no preenchimento de dados.");
    }

    public void deleteContemplacao(int idContemplacao) {
        for(Contemplacao contemplacao : contemplacoes){
            if(contemplacao.getIdContemplacao() == idContemplacao) {
                contemplacoes.remove(contemplacao);
                System.out.println("Contemplação removida com sucesso.");
                return;
            }
        }
        System.out.println("Não foi possível remover a contemplação desejada.");
    }
}
