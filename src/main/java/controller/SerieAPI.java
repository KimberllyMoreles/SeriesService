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
import javax.ws.rs.core.MediaType;
import model.Canal;
import persistence.SerieDAO;
import persistence.CanalDAO;

@Path("/apiserie")
public class SerieAPI {

    SerieDAO serieDAO;
    CanalDAO canalDAO;

    public SerieAPI() throws SQLException {
        serieDAO = new SerieDAO();
        canalDAO = new CanalDAO();
    
    }

    @GET
    @Path("/serie")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Serie> listarSeriees() {
        List<Serie> seriees = serieDAO.listar();
        return seriees;
    }

    @GET
    @Path("/serie/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Serie buscarSerie(@DefaultValue("0") @PathParam("id") int id) {
        Serie serie = serieDAO.buscarPorChavePrimaria(id);
        return serie;
    }

    @GET
    @Path("/serie/{id}/canal")
    @Produces(MediaType.APPLICATION_JSON)
    public Canal buscarSerieCanals(@DefaultValue("0") @PathParam("id") int id) {
        return serieDAO.buscarPorChavePrimaria(id).getCanal();
    }
    
    @GET
    @Path("/serie/{id}/canal/{idcanal}")
    @Produces(MediaType.APPLICATION_JSON)
    public Canal buscarCanal(@DefaultValue("0") @PathParam("id") int id,
            @DefaultValue("0") @PathParam("idcanal") int idcanal) {
        return canalDAO.buscarPorChavePrimaria(idcanal);
    }

    @POST
    @Path("/serie")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Serie inserirSerie(Serie serie) {
        API api = new API();
        serie.setPoster(api.storeImage(serie.getPoster()));
        serie = serieDAO.incluir(serie);
        return serie;
    }

    @POST
    @Path("/serie/{id}/canal")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Canal inserirSerieCanal(@DefaultValue("0") @PathParam("id") int id, Canal canal) {
        Serie serie = serieDAO.buscarPorChavePrimaria(id);
        //canal.setSerie(serie);
        return canalDAO.incluir(canal);
    }

    @PUT
    @Path("/serie/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Serie alterarSerie(Serie serie) {
        serie = serieDAO.alterar(serie);
        return serie;
    }

    @PUT
    @Path("/serie/{id}/canal/{idcanal}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Canal alterarCanal(@DefaultValue("0") @PathParam("id") int id,
            @DefaultValue("0") @PathParam("idcanal") int idcanal,
            Canal canal
    ) {
        Serie serie = serieDAO.buscarPorChavePrimaria(id);
        canal.setId(idcanal);
        //canal.setSerie(serie);
        return canalDAO.alterar(canal);
    }

    @DELETE
    @Path("/serie/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean excluirSerie(@DefaultValue("0") @PathParam("id") int id) {
        return serieDAO.excluir(id);
    }

    @DELETE
    @Path("/serie/{id}/canal/{idcanal}")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean excluirSerieCanal(@DefaultValue("0") @PathParam("id") int id,
            @DefaultValue("0") @PathParam("idcanal") int idcanal) {
        return canalDAO.excluir(idcanal);
    }

    
    
    
}

