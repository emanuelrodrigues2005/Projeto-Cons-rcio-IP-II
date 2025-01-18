

import Enums.StatusGrupoConsorcioEnum;
import Models.GrupoConsorcio;
import java.util.ArrayList;

public class GrupoConsorcioRepository {
    private ArrayList<GrupoConsorcio> grupos;

    public GrupoConsorcioRepository() {
        this.grupos = new ArrayList<>();
    }

    public void getAllGrupos() {
        if (grupos.isEmpty()) {
            System.out.println("\nNenhum grupo foi criado!\n");
        } else {
            System.out.println("\nLista de grupos:");
            for (GrupoConsorcio grupo : grupos) {
                System.out.printf("- %s (participantes: %d)\n", grupo.getNomeGrupo(), grupo.getNumeroParticipantes());
            }
        }
    }

    public void getGrupoByName(String nomeGrupo) {
        GrupoConsorcio grupoTemp = null;

        for (GrupoConsorcio grupo : grupos) {
            if (grupo.getNomeGrupo().equalsIgnoreCase(nomeGrupo)) {
                grupoTemp = grupo;
            }
        }

        if (grupoTemp != null) {
            System.out.println(grupoTemp);
        } else {
            System.out.println("\nO grupo procurado não foi encontrado!\n");
        }
    }

    public void createGrupoConsorcio(String nomeGrupo, int numeroParticipantes, double valorTotal, double taxaAdmin) {
        GrupoConsorcio grupoConsorcio = new GrupoConsorcio(nomeGrupo, numeroParticipantes, valorTotal, taxaAdmin);

        grupos.add(grupoConsorcio);

        System.out.println(grupoConsorcio);

        System.out.printf("Grupo '%s' criado e adicionado com sucesso!\n\n", grupoConsorcio.getNomeGrupo());
    }

    public void updateGrupoConsorcio(
            String nomeGrupo,
            int novoNumeroParticipantes,
            double novoValorTotal,
            double novoTaxaAdmin,
            StatusGrupoConsorcioEnum statusGrupoConsorcio
    ) {
        GrupoConsorcio grupoTemp = null;

        for (int i = 0; i < grupos.size(); i++) {
            if (grupos.get(i).getNomeGrupo().equalsIgnoreCase(nomeGrupo)) {
                if(novoNumeroParticipantes >= 0) {
                    grupos.get(i).setNumeroParticipantes(novoNumeroParticipantes);
                }
                if(novoValorTotal >= 0) {
                    grupos.get(i).setValorTotal(novoValorTotal);
                }
                if(novoTaxaAdmin >= 0) {
                    grupos.get(i).setTaxaAdmin(novoTaxaAdmin);
                }
                if(statusGrupoConsorcio != null) {
                    grupos.get(i).setStatusGrupoConsorcio(statusGrupoConsorcio);
                }

                double novoValorParcela = ((novoValorTotal + novoValorTotal * novoTaxaAdmin) / novoNumeroParticipantes);

                if(novoValorParcela >= 0) {
                    grupos.get(i).setValorParcela(novoValorParcela);
                }

                grupoTemp = grupos.get(i);
            }
        }

        if (grupoTemp != null) {
            System.out.println(grupoTemp);

            System.out.printf("\nGrupo '%s' criado e adicionado com sucesso!\n", grupoTemp.getNomeGrupo());
        } else {
            System.out.println("Grupo não foi encontrado, logo não foi posspivel atualizá-lo!\n");
        }
    }

    public void deleteGrupoConsorcio(String nomeGrupo) {
        GrupoConsorcio grupoTemp = null;
        for (int i = 0; i < grupos.size(); i++) {
            if (grupos.get(i).getNomeGrupo().equalsIgnoreCase(nomeGrupo)) {
                grupoTemp = grupos.get(i);
            }
        }

        if (grupoTemp != null) {
            grupos.remove(grupoTemp);

            System.out.printf("Grupo: %s deletado com sucesso!\n", grupoTemp.getNomeGrupo());
        } else {
            System.out.println("Não foi possível deletar o grupo, pois esse não foi encontrado!");
        }
    }
}
