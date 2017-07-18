package controller;

import java.sql.SQLException;
import model.Serie;
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
import model.Canal;
import persistence.SerieDAO;
import persistence.CanalDAO;

@Path("/apiserie")
public class SerieAPI extends UsuarioAPI{

    SerieDAO serieDAO;
    CanalDAO canalDAO;

    public SerieAPI() throws SQLException {
        serieDAO = new SerieDAO();
        canalDAO = new CanalDAO();
    
    }

    @GET
    @Path("/serie")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarSeriees(@Context HttpHeaders headers) {
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        List<Serie> seriees = serieDAO.listar();
       return Response.ok(seriees).build();
    }

    @GET
    @Path("/serie/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarSerie(@DefaultValue("0") @PathParam("id") int id, @Context HttpHeaders headers) {
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        Serie serie = serieDAO.buscarPorChavePrimaria(id);
        return Response.ok(serie).build();
    }

    @GET
    @Path("/serie/{id}/canal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarSerieCanals(@DefaultValue("0") @PathParam("id") int id, @Context HttpHeaders headers) {
        
         String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(serieDAO.buscarPorChavePrimaria(id).getCanal()).build();
    }
    
    @GET
    @Path("/serie/{id}/canal/{idcanal}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCanal(@DefaultValue("0") @PathParam("id") int id,
            @DefaultValue("0") @PathParam("idcanal") int idcanal, @Context HttpHeaders headers) {
        
         String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        return Response.ok(canalDAO.buscarPorChavePrimaria(idcanal)).build();
    }

    @POST
    @Path("/serie")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirSerie(Serie serie, @Context HttpHeaders headers) {
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        API api = new API();
        serie.setPoster(api.storeImage(serie.getPoster()));
        serie = serieDAO.incluir(serie);
        return Response.ok(serie).build();
    }

    @POST
    @Path("/serie/{id}/canal")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirSerieCanal(@DefaultValue("0") @PathParam("id") int id, Canal canal, @Context HttpHeaders headers ) {
        
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        
        Serie serie = serieDAO.buscarPorChavePrimaria(id);
        //canal.setSerie(serie);
        return Response.ok(canalDAO.incluir(canal)).build();
    }

    @PUT
    @Path("/serie/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterarSerie(Serie serie, @Context HttpHeaders headers) {
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        serie = serieDAO.alterar(serie);
        return Response.ok(serie).build();
    }

    @PUT
    @Path("/serie/{id}/canal/{idcanal}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterarCanal(@DefaultValue("0") @PathParam("id") int id,
            @DefaultValue("0") @PathParam("idcanal") int idcanal,
            Canal canal,
            @Context HttpHeaders headers
    ) {
        
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        Serie serie = serieDAO.buscarPorChavePrimaria(id);
        canal.setId(idcanal);
        //canal.setSerie(serie);
        return Response.ok(canalDAO.alterar(canal)).build();
    }

    @DELETE
    @Path("/serie/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirSerie(@DefaultValue("0") @PathParam("id") int id, @Context HttpHeaders headers) {
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        return Response.ok(serieDAO.excluir(id)).build();
    }

    @DELETE
    @Path("/serie/{id}/canal/{idcanal}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirSerieCanal(@DefaultValue("0") @PathParam("id") int id,
            @DefaultValue("0") @PathParam("idcanal") int idcanal, @Context HttpHeaders headers) {
        
        
        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        return Response.ok(canalDAO.excluir(idcanal)).build();
    }

    
    
    
}

