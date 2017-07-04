package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll",
            query = "SELECT c FROM Usuario c")
    ,
    @NamedQuery(name = "Usuario.findByLogin",
            query = "SELECT c FROM Usuario c WHERE c.login = :login")
    ,
    @NamedQuery(name = "Usuario.login",
            query = "SELECT c FROM Usuario c WHERE c.login = :login AND c.senha = :senha")})

public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private String nome;

    @Column
    private String login;

    @Column
    private String senha;
    
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    

}
