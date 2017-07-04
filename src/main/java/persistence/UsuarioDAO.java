package persistence;

import helpers.Hash;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Usuario;

public class UsuarioDAO {

    private EntityManager em;
    private EntityManagerFactory emf;

    public UsuarioDAO() throws SQLException {
        this.emf = Persistence.createEntityManagerFactory("API_REST");
        this.em = this.emf.createEntityManager();
    }

    public Usuario login(Usuario usuario) throws NoSuchAlgorithmException {
            
        usuario.setSenha(Hash.generateHash(usuario.getSenha()));
        
        if(this.checarLogin(usuario)) {
            Query query = em.createNamedQuery("Usuario.findByLogin");
            query.setParameter("login", usuario.getLogin());
            usuario = (Usuario) query.getSingleResult();
            String token = Hash.base64encode("login=" + usuario.getLogin() + "&" + "senha=" + usuario.getSenha());
            usuario.setLogin("");
            usuario.setSenha("");
            usuario.setToken(token);
        } else {
            usuario.setToken("");            
        }
        
        return usuario;
    }

    public boolean checarLogin(Usuario usuario) {

        Query query = em.createNamedQuery("Usuario.login");
        query.setParameter("login", usuario.getLogin());
        query.setParameter("senha", usuario.getSenha());
        return query.getResultList().size() > 0;
        
    }

    public Usuario incluir(Usuario usuario) {
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            throw e;
        }
        return usuario;
    }

    public Usuario alterar(Usuario usuario) {
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
            throw e;
        }
        return usuario;
    }

    public Boolean excluir(Usuario usuario) {
        Boolean retorno;
        try {
            em.getTransaction().begin();
            em.remove(usuario);
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
            Usuario obj = this.buscarPorChavePrimaria(chaveprimaria);
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

    public List<Usuario> listar() {
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }

    public Usuario buscarPorChavePrimaria(int chaveprimaria) {
        return em.find(Usuario.class, chaveprimaria);
    }
}
