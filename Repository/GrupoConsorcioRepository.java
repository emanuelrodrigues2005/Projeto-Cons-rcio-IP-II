package Repository;

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
            System.out.println("Nenhum grupo foi criado!");
        } else {
            System.out.println("Lista de grupos:");
            for (GrupoConsorcio grupo : grupos) {
                System.out.printf("- %s (participantes: %d)\n", grupo.getNomeGrupo(), grupo.getNumeroParticipantes());
            }
        }
    }

    public String getGrupoByName(String nomeGrupo) {
        for (GrupoConsorcio grupo : grupos) {
            if (grupo.getNomeGrupo().equalsIgnoreCase(nomeGrupo)) {
                return grupo.toString();
            }
        }
        return "Grupo não encontrado!";
    }

    public void createGrupoConsorcio(String nomeGrupo, int numeroParticipantes, double valorTotal, double taxaAdmin) {
        GrupoConsorcio grupoConsorcio = new GrupoConsorcio(nomeGrupo, numeroParticipantes, valorTotal, taxaAdmin);

        grupos.add(grupoConsorcio);

        System.out.printf("Grupo %s criado e adicionado com sucesso!\n", grupoConsorcio.getNomeGrupo());
    }

    public boolean updateGrupoConsorcio(
            String nomeGrupo,
            int novoNumeroParticipantes,
            double novoValorTotal,
            double novoTaxaAdmin,
            StatusGrupoConsorcioEnum statusGrupoConsorcio
    ) {
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
                return true;
            }
        }
        return false;
    }

    public boolean deleteGrupoConsorcio(String nomeGrupo) {
        GrupoConsorcio grupoTemp = null;
        for (int i = 0; i < grupos.size(); i++) {
            if (grupos.get(i).getNomeGrupo().equalsIgnoreCase(nomeGrupo)) {
                grupoTemp = grupos.get(i);
            }
        }

        if (grupoTemp != null) {
            grupos.remove(grupoTemp);
            return true;
        }

        return false;
    }
}
