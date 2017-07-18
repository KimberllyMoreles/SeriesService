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
import model.SerieAtor;
import persistence.AtorDAO;
import persistence.SerieAtorDAO;
import persistence.SerieDAO;

@Path("/apiserieator")
public class SerieAtorAPI extends UsuarioAPI {
    
    /*AtorDAO atorDAO;
    SerieDAO serieDAO;*/
    SerieAtorDAO serieAtorDAO;
    
    
    
    public SerieAtorAPI() throws SQLException {
        /*atorDAO = new AtorDAO();
        serieDAO = new SerieDAO();*/
        serieAtorDAO = new SerieAtorDAO();
    }  

    @GET
    @Path("/serieator")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarAtores(@Context HttpHeaders headers) {
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        List<SerieAtor> serieAtores = serieAtorDAO.listar();
        return Response.ok(serieAtores).build();
    } 
    
    @GET
    @Path("/serieator/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarSerieAtor(@DefaultValue("0") @PathParam("id") int id, @Context HttpHeaders headers) {
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        SerieAtor serieAtor = serieAtorDAO.buscarPorChavePrimaria(id);
        return Response.ok(serieAtor).build();
    }
    
    @POST
    @Path("/serieator")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirSerieAtor(SerieAtor serieAtor, @Context HttpHeaders headers) {
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        serieAtor = serieAtorDAO.incluir(serieAtor);
        return Response.ok(serieAtor).build();
    }
    
    @PUT
    @Path("/serieator/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterarAtor(SerieAtor serieAtor, @Context HttpHeaders headers) {
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        serieAtor = serieAtorDAO.alterar(serieAtor);
        return Response.ok(serieAtor).build();
    }
    
    @DELETE
    @Path("/serieator/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirAtor(@DefaultValue("0") @PathParam("id") int id, @Context HttpHeaders headers) {
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(serieAtorDAO.excluir(id)).build();
    }

}