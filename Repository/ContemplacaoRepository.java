public class ContemplacaoRepository {
    private ArrayList<Contemplacao> contemplacoes;
    
    //CREATE
    public void createContemplacao(Contrato contratoContemplacao, LocalDate dataContemplacao){
        if(contratoContemplacao != null && dataContemplacao != null){
        Contemplacao contemplacao = new Contemplacao(contratoContemplacao, dataContemplacao);
        contemplacoes.add(contemplacao);
        System.out.println("Contemplação criada e adicionada com sucesso!\n\n");
    }
    }
    //READ
    public void getAllContemplacoes(){
        if(contemplacoes.isEmpty()){
            System.out.println("\nNão há Contemplações registradas!\n")
        } else {
            for(Contemplacao contemplacao : contemplacoes){
                System.out.println("Contrato Contemplado: " + contemplacao.contratoContemplacao.cliente.getNome() 
                + " (Data: " + contemplacao.getDataContemplacao() + " )\n");
            }
        }
    }
    public void getContemplacao(Contrato contrato){
        Contemplacao temp = null;
        for(Contemplacao contemplacao : contemplacoes){
            if(contemplacao.getContratoContemplacao().equalsIgnoreCase(contrato)){
                temp = contemplacao;
            }
        }
        if(temp != null){
            System.out.println(temp);
        } else {
            System.out.println("\nNão existe registro de contemplações para este contrato!\n")
        }
    }
    //UPDATE
    public void updateContemplacao(Contrato contratoContemplacao, LocalDate dataContemplacao){
        int aux = 0;
       if(contratoContemplacao != null){
        contemplacoes.setContratoContemplacao(contratoContemplacao);
        aux++;
       }
       if(dataContemplacao != null){
        contemplacoes.setDataContemplacao(dataContemplacao);
        aux++;
       }
       if(aux == 2){
        System.out.println("Contemplação criada e realizada com sucesso!\n\n");
       } else {
        System.out.println("Não foi possível realizar contemplação! Dados inválidos!\n\n");
       }
    }
    //DELETE
    public void deleteContemplacao(Contrato contrato){
        Contemplacao temp = null;
        for(Contemplacao contemplacao : contemplacoes){
            if(contemplacao.getContratoContemplacao().equalsIgnoreCase(contrato)){
                temp = contemplacao;
            }
        }
        if(temp != null){
            contemplacoes.remove(temp);
            System.out.println("\nContemplação removida com sucesso!\n");
        } else {
            System.out.println("\nNão existe registro de contemplações para este contrato!\n")
        }
    }
}
