package controller;

import java.sql.SQLException;
import model.Canal;
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
import persistence.CanalDAO;

@Path("/apicanal")
public class CanalAPI {
    
    CanalDAO canalDAO;
    
    public CanalAPI() throws SQLException {
        canalDAO = new CanalDAO();
    }  

    @GET
    @Path("/canal")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Canal> listarCanals() {
        List<Canal> canals = canalDAO.listar();
        return canals;
    } 
    
    @GET
    @Path("/canal/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Canal buscarCanal(@DefaultValue("0") @PathParam("id") int id) {
        Canal canal = canalDAO.buscarPorChavePrimaria(id);
        return canal;
    }
    
    @POST
    @Path("/canal")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Canal inserirCanal(Canal canal) {
        canal = canalDAO.incluir(canal);
        return canal;
    }
    
    @PUT
    @Path("/canal/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Canal alterarCanal(Canal canal) {
        canal = canalDAO.alterar(canal);
        return canal;
    }
    
    @DELETE
    @Path("/canal/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean excluirCanal(@DefaultValue("0") @PathParam("id") int id) {
        return canalDAO.excluir(id);
    }

}
