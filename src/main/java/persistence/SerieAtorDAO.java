package persistence;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.SerieAtor;

public class SerieAtorDAO {

    private EntityManager em;
    private EntityManagerFactory emf;

    public SerieAtorDAO() throws SQLException {
        this.emf = Persistence.createEntityManagerFactory("API_REST");
        this.em = this.emf.createEntityManager();
    }

    public SerieAtor incluir(SerieAtor serieAtor) {
        try {
            em.getTransaction().begin();
            em.persist(serieAtor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            throw e;
        }
        return serieAtor;
    }

    public SerieAtor alterar(SerieAtor serieAtor) {
        try {
            em.getTransaction().begin();
            em.merge(serieAtor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            throw e;
        }
        return serieAtor;
    }

    public Boolean excluir(SerieAtor serieAtor) {
        Boolean retorno;
        try {
            em.getTransaction().begin();
            em.remove(serieAtor);
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
            SerieAtor obj = this.buscarPorChavePrimaria(chaveprimaria);
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

    public List<SerieAtor> listar() {
        return em.createNamedQuery("SerieAtor.findAll").getResultList();
    }

    public SerieAtor buscarPorChavePrimaria(int chaveprimaria) {
        return em.find(SerieAtor.class, chaveprimaria);
    }
}
