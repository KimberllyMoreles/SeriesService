package persistence;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Cliente;

public class ClienteDAO {

    private EntityManager em;
    private EntityManagerFactory emf;

    public ClienteDAO() throws SQLException {
        this.emf = Persistence.createEntityManagerFactory("API_REST");
        this.em = this.emf.createEntityManager();
    }

    public Cliente incluir(Cliente cliente) {
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            throw e;
        }
        return cliente;
    }

    public Cliente alterar(Cliente cliente) {
        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            throw e;
        }
        return cliente;
    }

    public Boolean excluir(Cliente cliente) {
        Boolean retorno;
        try {
            em.getTransaction().begin();
            em.remove(cliente);
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
            Cliente obj = this.buscarPorChavePrimaria(chaveprimaria);
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

    public List<Cliente> listar() {
        return em.createNamedQuery("Cliente.findAll").getResultList();
    }

    public Cliente buscarPorChavePrimaria(int chaveprimaria) {
        return em.find(Cliente.class, chaveprimaria);
    }
}
