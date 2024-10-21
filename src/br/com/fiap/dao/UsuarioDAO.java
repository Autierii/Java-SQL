package br.com.fiap.dao;

import br.com.fiap.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuarios (user_id, Nome, email, tipo_usuario) VALUES (seq_usuarios.NEXTVAL, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getTipoUsuario());
            statement.executeUpdate();
        }
    }

    public Usuario buscarUsuarioPorEmail(String email) throws SQLException {
        String sql = "SELECT user_id, Nome, email, tipo_usuario FROM Usuarios WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Usuario(
                    resultSet.getInt("user_id"),
                    resultSet.getString("Nome"),
                    resultSet.getString("email"),
                    resultSet.getString("tipo_usuario")
                );
            }
            return null;
        }
    }

    public void atualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuarios SET Nome = ?, email = ?, tipo_usuario = ? WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getTipoUsuario());
            statement.setInt(4, usuario.getUserId());
            statement.executeUpdate();
        }
    }

    public void deletarUsuarioPorEmail(String email) throws SQLException {
        String sql = "DELETE FROM Usuarios WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.executeUpdate();
        }
    }
}
