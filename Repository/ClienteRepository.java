package Repository;

import Models.Cliente;
import Models.Contrato;

import java.util.ArrayList;

public class ClienteRepository {
    private ArrayList<Cliente> clientes;

    public ClienteRepository() {
        this.clientes = new ArrayList<>();
    }

    public void getAllClientes() {
        try {
            if (clientes.isEmpty()) {
                System.out.println("\nNenhum cliente foi cadastrado!\n");
            } else {
                System.out.println("\nLista de clientes:\n");
                for (Cliente cliente : clientes) {
                    System.out.printf("Cliente: %s\n", cliente.getNome());

                    for (Contrato contrato : cliente.getContratos()) {
                        System.out.printf("%s;\nSaldo Devedor: %.2f;\nStatus do Contrato: %s.\n\n", contrato.getGrupoAssociado(), contrato.getSaldoDevedor(), contrato.getStatusContrato());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter a lista de clientes: " + e.getMessage());
        }
    }

    public Cliente getClienteByCpf(String cpf) {
        try {
            for (Cliente cliente : clientes) {
                if (cliente.getCpf().equals(cpf)) {
                    System.out.println(cliente);
                    return cliente;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar cliente pelo CPF: " + e.getMessage());
        }
        return null;
    }

    public Cliente createCliente(String nome, String cpf, String telefone, String email) {
        try {
            if (getClienteByCpf(cpf) == null) {
                Cliente cliente = new Cliente(nome, cpf, telefone, email);

                clientes.add(cliente);

                System.out.println(cliente);

                System.out.printf("Cliente '%s' adicionado com sucesso!\n\n", cliente.getNome());
                return cliente;
            } else {
                System.out.println("Cpf já cadastrado para um cliente!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar cliente: " + e.getMessage());
        }
        return null;
    }

    public Cliente updateCliente(String cpf, String novoNome, String novoTelefone, String novoEmail) {
        try {
            Cliente clienteTemp = getClienteByCpf(cpf);

            if (clienteTemp != null) {
                if (!novoNome.isEmpty()) {
                    clienteTemp.setNome(novoNome);
                }

                if (!novoTelefone.isEmpty()) {
                    clienteTemp.setTelefone(novoTelefone);
                }

                if (!novoEmail.isEmpty()) {
                    clienteTemp.setEmail(novoEmail);
                }

                System.out.println(clienteTemp);
                System.out.println("\nCliente atualizado com sucesso!\n");
                return clienteTemp;
            } else {
                System.out.println("\nNenhum cliente encontrado com o CPF informado!\n");
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }
        return null;
    }

    public void deleteCliente(String cpf) {
        try {
            Cliente clienteTemp = getClienteByCpf(cpf);

            if (clienteTemp != null) {
                clientes.remove(clienteTemp);
                System.out.printf("\nCliente '%s' deletado com sucesso!\n", clienteTemp.getNome());
            } else {
                System.out.println("\nNenhum cliente encontrado com o CPF informado!\n");
            }
        } catch (Exception e) {
            System.out.println("Erro ao deletar cliente: " + e.getMessage());
        }
    }
}