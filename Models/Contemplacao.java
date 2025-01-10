package Models;

import java.time.LocalDate;
import Enums.StatusContratoEnum;
import java.util.Random;

public class Contemplacao {
    private Contrato contratoContemplacao;
	private LocalDate dataContemplacao;
	
	public Contemplacao(Contrato contratoContemplacao, LocalDate dataContemplacao) {
		super();
		this.contratoContemplacao = contratoContemplacao;
		this.dataContemplacao = java.time.LocalDate.now();
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

	public void sorteioContemplacao() {
		Random rand = new Random();
		for (Contrato Vcontrato : contratoContemplacao.getGrupoAssociado().getListaContratos()) {
			if(Vcontrato.getStatusContrato() == StatusContratoEnum.ATIVO) {
				int IndiceSorteado = rand.nextInt(Vcontrato.getGrupoAssociado().getListaContratos().size());
				contratoContemplacao = Vcontrato.getGrupoAssociado().getListaContratos().get(IndiceSorteado);
				contratoContemplacao.setStatusContrato(StatusContratoEnum.CONTEMPLADO);
				addOrdenarListaContemp(contratoContemplacao);
			}
			System.out.println("Este cliente não pode ser contemplado!");
		}
	}
	private void addOrdenarListaContemp (Contrato contrato) {
		GrupoConsorcio grupo = contrato.getGrupoAssociado();
		grupo.getListaContratosContemplados().add(contrato);
	}
}
