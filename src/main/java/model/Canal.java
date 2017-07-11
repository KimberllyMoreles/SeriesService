package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Canal.findAll", query = "SELECT c FROM Canal c")})

public class Canal implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;

    @Column
    private String nome;
    
    @OneToMany(mappedBy = "canal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Serie> serie;

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

    /**
     * @return the serie
     */
    public List<Serie> getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(List<Serie> serie) {
        this.serie = serie;
    }

}
