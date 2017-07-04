package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Serie.findAll", query = "SELECT c FROM Serie c ")})

public class Serie implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;

    @Column
    private String nome;

    @Column
    private String poster;
    
    @JoinColumn(name="canal_id")
    @ManyToOne
    private Canal canal;
    

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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }    

    public Canal getCanal() {
        return canal;
    }

    public void setCanal(Canal canal) {
        this.canal = canal;
    }   

}