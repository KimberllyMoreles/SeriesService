package persistence;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Canal;

public class CanalDAO {

    private EntityManager em;
    private EntityManagerFactory emf;

    public CanalDAO() throws SQLException {
        this.emf = Persistence.createEntityManagerFactory("API_REST");
        this.em = this.emf.createEntityManager();
    }

    public Canal incluir(Canal canal) {
        try {
            em.getTransaction().begin();
            em.persist(canal);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            throw e;
        }
        return canal;
    }

    public Canal alterar(Canal canal) {
        try {
            em.getTransaction().begin();
            em.merge(canal);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            throw e;
        }
        return canal;
    }

    public Boolean excluir(Canal canal) {
        Boolean retorno;
        try {
            em.getTransaction().begin();
            em.remove(canal);
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
            Canal obj = this.buscarPorChavePrimaria(chaveprimaria);
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

    public List<Canal> listar() {
        return em.createNamedQuery("Canal.findAll").getResultList();
    }

    public Canal buscarPorChavePrimaria(int chaveprimaria) {
        return em.find(Canal.class, chaveprimaria);
    }
}
