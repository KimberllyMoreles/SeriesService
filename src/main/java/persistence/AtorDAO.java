package persistence;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Ator;

public class AtorDAO {

    private EntityManager em;
    private EntityManagerFactory emf;

    public AtorDAO() throws SQLException {
        this.emf = Persistence.createEntityManagerFactory("API_REST");
        this.em = this.emf.createEntityManager();
    }

    public Ator incluir(Ator ator) {
        try {
            em.getTransaction().begin();
            em.persist(ator);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            throw e;
        }
        return ator;
    }

    public Ator alterar(Ator ator) {
        try {
            em.getTransaction().begin();
            em.merge(ator);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            throw e;
        }
        return ator;
    }

    public Boolean excluir(Ator ator) {
        Boolean retorno;
        try {
            em.getTransaction().begin();
            em.remove(ator);
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
            Ator obj = this.buscarPorChavePrimaria(chaveprimaria);
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

    public List<Ator> listar() {
        return em.createNamedQuery("Ator.findAll").getResultList();
    }

    public Ator buscarPorChavePrimaria(int chaveprimaria) {
        return em.find(Ator.class, chaveprimaria);
    }
}
