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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import persistence.CanalDAO;

@Path("/apicanal")
public class CanalAPI extends UsuarioAPI{
    
    CanalDAO canalDAO;
    
    public CanalAPI() throws SQLException {
        canalDAO = new CanalDAO();
    }  

    @GET
    @Path("/canal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCanals(@Context HttpHeaders headers) {
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        List<Canal> canals = canalDAO.listar();
        return Response.ok(canals).build();
    } 
    
    @GET
    @Path("/canal/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCanal(@DefaultValue("0") @PathParam("id") int id, @Context HttpHeaders headers) {
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        Canal canal = canalDAO.buscarPorChavePrimaria(id);
        return Response.ok(canal).build();
    }
    
    @POST
    @Path("/canal")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirCanal(Canal canal, @Context HttpHeaders headers) {
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        canal = canalDAO.incluir(canal);
        return Response.ok(canal).build();
    }
    
    @PUT
    @Path("/canal/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterarCanal(Canal canal, @Context HttpHeaders headers) {
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        canal = canalDAO.alterar(canal);
        return Response.ok(canal).build();
    }
    
    @DELETE
    @Path("/canal/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirCanal(@DefaultValue("0") @PathParam("id") int id, @Context HttpHeaders headers) {
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        return Response.ok(canalDAO.excluir(id)).build();
    }

}
