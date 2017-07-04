package controller;

import java.sql.SQLException;
import model.Ator;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import persistence.AtorDAO;

@Path("/api")
public class AtorAPI {
    
    AtorDAO atorDAO;
    
    public AtorAPI() throws SQLException {
        atorDAO = new AtorDAO();
    }  

    @GET
    @Path("/ator")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ator> listarAtors() {
        List<Ator> ators = atorDAO.listar();
        return ators;
    } 
    
    @GET
    @Path("/ator/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ator buscarAtor(@DefaultValue("0") @PathParam("id") int id) {
        Ator ator = atorDAO.buscarPorChavePrimaria(id);
        return ator;
    }
    
    @POST
    @Path("/ator")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Ator inserirAtor(Ator ator) {
        ator = atorDAO.incluir(ator);
        return ator;
    }
    
    @PUT
    @Path("/ator/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Ator alterarAtor(Ator ator) {
        ator = atorDAO.alterar(ator);
        return ator;
    }
    
    @DELETE
    @Path("/ator/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean excluirAtor(@DefaultValue("0") @PathParam("id") int id) {
        return atorDAO.excluir(id);
    }

}
