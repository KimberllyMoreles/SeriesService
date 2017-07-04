package persistence;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Serie;

public class SerieDAO {

    private EntityManager em;
    private EntityManagerFactory emf;

    public SerieDAO() throws SQLException {
        this.emf = Persistence.createEntityManagerFactory("API_REST");
        this.em = this.emf.createEntityManager();
    }

    public Serie incluir(Serie serie) {
        try {
            //serie.getProdutor().setId(idprodutor);
            em.getTransaction().begin();
            em.persist(serie);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            throw e;
        }
        return serie;
    }

    public Serie alterar(Serie serie) {
        try {
            em.getTransaction().begin();
            em.merge(serie);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            throw e;
        }
        return serie;
    }

    public Boolean excluir(Serie serie) {
        Boolean retorno;
        try {
            em.getTransaction().begin();
            em.remove(serie);
            em.getTransaction().commit();
            retorno = true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            retorno = false;
            throw e;
        }
        return retorno;
    }

    public Boolean excluir(int chaveprimaria) {
        Boolean retorno;
        try {
            Serie obj = this.buscarPorChavePrimaria(chaveprimaria);
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            retorno = true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            retorno = false;
            throw e;
        }
        return retorno;
    }

    public List<Serie> listar() {
        return em.createNamedQuery("Serie.findAll").getResultList();
    }

    public Serie buscarPorChavePrimaria(int chaveprimaria) {
        return em.find(Serie.class, chaveprimaria);
    }
}
