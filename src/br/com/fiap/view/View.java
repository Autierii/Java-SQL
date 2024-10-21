package br.com.fiap.view;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.model.Usuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

package br.com.fiap.view;

import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.model.Usuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class View {

    public static void main(String[] args) {
        while (true) {
            String[] options = {"Adicionar Usuario", "Buscar Usuario", "Atualizar Usuario", "Deletar Usuario", "Sair"};
            int choice = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Administração de Usuários",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    adicionarUsuario();
                    break;
                case 1:
                    buscarUsuario();
                    break;
                case 2:
                    atualizarUsuario();
                    break;
                case 3:
                    deletarUsuario();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }

    private static void buscarUsuario() {
        try {
            String email = JOptionPane.showInputDialog("Informe o email do usuário a ser buscado:");
            try (Connection connection = ConnectionFactory.abrirConexao()) {
                if (connection == null) {
                    JOptionPane.showMessageDialog(null, "Erro ao abrir conexão com o banco de dados.");
                    return;
                }
                UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
                Usuario usuario = usuarioDAO.buscarUsuarioPorEmail(email);
                if (usuario != null) {
                    String userInfo = "ID: " + usuario.getUserId() + "\nNome: " + usuario.getNome() + "\nEmail: " + usuario.getEmail() +
                            "\nTipo: " + usuario.getTipoUsuario() + "\nMédia: " + usuario.getMedia();
                    JOptionPane.showMessageDialog(null, userInfo);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL ao buscar usuário: " + e.getMessage(), "Erro de SQL", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar usuário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void atualizarUsuario() {
        try {
            int userId = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do usuário para atualização:"));
            String novoNome = JOptionPane.showInputDialog("Informe o novo nome:");
            String novoEmail = JOptionPane.showInputDialog("Informe o novo email:");
            String[] options = {"Aluno", "Professor", "Jogador Comum"};
            int tipoUsuarioIndex = JOptionPane.showOptionDialog(null, "Selecione o novo tipo de usuário:", "Tipo de Usuário",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            String novoTipoUsuario = options[tipoUsuarioIndex];
            double novaMedia = Double.parseDouble(JOptionPane.showInputDialog("Informe a nova média:"));

            try (Connection connection = ConnectionFactory.abrirConexao()) {
                if (connection == null) {
                    JOptionPane.showMessageDialog(null, "Erro ao abrir conexão com o banco de dados.");
                    return;
                }
                UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
                Usuario usuario = new Usuario(userId, novoNome, novoEmail, novoTipoUsuario, novaMedia);
                usuarioDAO.atualizarUsuario(usuario);
                JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL ao atualizar usuário: " + e.getMessage(), "Erro de SQL", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro de formatação de número: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void deletarUsuario() {
        try {
            String email = JOptionPane.showInputDialog("Informe o email do usuário a ser deletado:");
            try (Connection connection = ConnectionFactory.abrirConexao()) {
                if (connection == null) {
                    JOptionPane.showMessageDialog(null, "Erro ao abrir conexão com o banco de dados.");
                    return;
                }
                UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
                usuarioDAO.deletarUsuarioPorEmail(email);
                JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL ao deletar usuário: " + e.getMessage(), "Erro de SQL", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar usuário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void calcularMedia() {
        try {
            int userId = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do usuário para calcular a média:"));
            int numNotas = Integer.parseInt(JOptionPane.showInputDialog("Quantas notas serão inseridas?"));
            double soma = 0;
            for (int i = 0; i < numNotas; i++) {
                double nota = Double.parseDouble(JOptionPane.showInputDialog("Insira a nota " + (i + 1) + ":"));
                soma += nota;
            }
            double media = soma / numNotas;

            try (Connection connection = ConnectionFactory.abrirConexao()) {
                if (connection == null) {
                    JOptionPane.showMessageDialog(null, "Erro ao abrir conexão com o banco de dados.");
                    return;
                }
                UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
                usuarioDAO.inserirMedia(userId, media);
                JOptionPane.showMessageDialog(null, "Média do usuário calculada e inserida: " + media);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL ao calcular/inserir média: " + e.getMessage(), "Erro de SQL", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro de formatação de número: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao calcular/inserir média: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        while (true) {
            String[] options = {"Adicionar", "Buscar", "Atualizar", "Deletar", "Calcular Média", "Sair"};
            int choice = JOptionPane.showOptionDialog(null, "Selecione uma operação:", "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (choice == -1 || choice == 6) {
                break; // Sair do loop

}
