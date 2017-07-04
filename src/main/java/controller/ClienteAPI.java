package controller;

import java.sql.SQLException;
import model.Cliente;
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
import persistence.ClienteDAO;

@Path("/api")
public class ClienteAPI {
    
    ClienteDAO clienteDAO;
    
    public ClienteAPI() throws SQLException {
        clienteDAO = new ClienteDAO();
    }  

    @GET
    @Path("/cliente")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = clienteDAO.listar();
        return clientes;
    } 
    
    @GET
    @Path("/cliente/{idcliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente buscarCliente(@DefaultValue("0") @PathParam("idcliente") int idcliente) {
        Cliente cliente = clienteDAO.buscarPorChavePrimaria(idcliente);
        return cliente;
    }
    
    @POST
    @Path("/cliente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente inserirCliente(Cliente cliente) {
        cliente = clienteDAO.incluir(cliente);
        return cliente;
    }
    
    @PUT
    @Path("/cliente/{idcliente}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente alterarCliente(Cliente cliente) {
        cliente = clienteDAO.alterar(cliente);
        return cliente;
    }
    
    @DELETE
    @Path("/cliente/{idcliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean excluirCliente(@DefaultValue("0") @PathParam("idcliente") int idcliente) {
        return clienteDAO.excluir(idcliente);
    }

}
