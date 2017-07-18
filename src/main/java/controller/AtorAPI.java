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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import persistence.AtorDAO;

@Path("/apiator")
public class AtorAPI extends UsuarioAPI{
    
    AtorDAO atorDAO;
    
    public AtorAPI() throws SQLException {
        atorDAO = new AtorDAO();
    }  

    @GET
    @Path("/ator")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarAtors(@Context HttpHeaders headers) {
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        List<Ator> ators = atorDAO.listar();
        return Response.ok(ators).build();
    } 
    
    @GET
    @Path("/ator/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarAtor(@DefaultValue("0") @PathParam("id") int id, @Context HttpHeaders headers) {
        
         String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        Ator ator = atorDAO.buscarPorChavePrimaria(id);
        return Response.ok(ator).build();
    }
    
    @POST
    @Path("/ator")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirAtor(Ator ator, @Context HttpHeaders headers) {
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        ator = atorDAO.incluir(ator);
        return Response.ok(ator).build();
    }
    
    @PUT
    @Path("/ator/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterarAtor(Ator ator, @Context HttpHeaders headers) {
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        ator = atorDAO.alterar(ator);
        return Response.ok(ator).build();
    }
    
    @DELETE
    @Path("/ator/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirAtor(@DefaultValue("0") @PathParam("id") int id, @Context HttpHeaders headers) {
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(atorDAO.excluir(id)).build();
    }

}
