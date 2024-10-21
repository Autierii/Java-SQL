package br.com.fiap.model;

public class Usuario {
    private int userId;
    private String nome;
    private String email;
    private String tipoUsuario;
    private double media;  // Adicionado para suportar a média de notas

    // Constructor
    public Usuario(int userId, String nome, String email, String tipoUsuario, double media) {
        this.userId = userId;
        this.nome = nome;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
        this.media = media;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }


}
