package pojo;

import java.io.Serializable;

/**
 * Created by alexandra on 28/11/17.
 */

public class Usuario implements Serializable {
    private String nome;
    private String login;
    private String senha;

    private long id;

    public Usuario() {
    }

    public Usuario(long id, String nome, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Nome: '" + nome + '\'' +
                ", Login: '" + login + '\'' +
                '.';
    }

}
