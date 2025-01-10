package Models;

import java.time.LocalDate;
import Enums.StatusContratoEnum;
import java.util.Random;

public class Contemplacao {
	private int idContemplacao;
    private Contrato contratoContemplacao;
	private LocalDate dataContemplacao;
	
	public Contemplacao(Contrato contratoContemplacao) {
		this.contratoContemplacao = contratoContemplacao;
		this.dataContemplacao = java.time.LocalDate.now();
		this.idContemplacao = new Random().nextInt(10000000) + 1;
	}

	public Contrato getContratoContemplacao() {
		return contratoContemplacao;
	}

	public void setContratoContemplacao(Contrato contratoContemplacao) {
		this.contratoContemplacao = contratoContemplacao;
	}

	public LocalDate getDataContemplacao() {
		return dataContemplacao;
	}

	public void setDataContemplacao(LocalDate dataContemplacao) {
		this.dataContemplacao = dataContemplacao;
	}

	public int getIdContemplacao() {
		return idContemplacao;
	}

	public void sorteioContemplacao() {
		Random rand = new Random();
		for (Contrato Vcontrato : contratoContemplacao.getGrupoAssociado().getListaContratos()) {
			if(Vcontrato.getStatusContrato() == StatusContratoEnum.ATIVO) {
				int IndiceSorteado = rand.nextInt(Vcontrato.getGrupoAssociado().getListaContratos().size());
				contratoContemplacao = Vcontrato.getGrupoAssociado().getListaContratos().get(IndiceSorteado);
				contratoContemplacao.setStatusContrato(StatusContratoEnum.CONTEMPLADO);
				addOrdenarListaContemp(contratoContemplacao);
			} else {
			System.out.println("Este cliente não pode ser contemplado!");
          }
          //e essa alteração? oi? esse main apago?
		}
	}
	private void addOrdenarListaContemp (Contrato contrato) {
        if(contrato != null){
		GrupoConsorcio grupo = contrato.getGrupoAssociado();
		grupo.getListaContratosContemplados().add(contrato);
        } else {
            System.out.println("Contrato inexistente! Contemplação não adicionada!");
        }
	}
}
