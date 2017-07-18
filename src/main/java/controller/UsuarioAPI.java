package controller;

import helpers.Hash;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import model.Usuario;
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
import persistence.UsuarioDAO;

@Path("/apiusuario")
public class UsuarioAPI {

    UsuarioDAO usuarioDAO;

    public UsuarioAPI() throws SQLException {
        usuarioDAO = new UsuarioDAO();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Usuario usuario) throws NoSuchAlgorithmException {
        usuario = usuarioDAO.login(usuario);
        if (!usuario.getToken().equals("")) {
            return Response.ok(usuario).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @GET
    @Path("/usuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuarios(@Context HttpHeaders headers) {

        String token = headers.getRequestHeader("Authorization").get(0);

        if (!this.checarToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        List<Usuario> usuarios = usuarioDAO.listar();
        return Response.ok(usuarios).build();
    }

    @GET
    @Path("/usuario/{idusuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario buscarUsuario(@DefaultValue("0") @PathParam("idusuario") int idusuario) {
        Usuario usuario = usuarioDAO.buscarPorChavePrimaria(idusuario);
        return usuario;
    }

    @POST
    @Path("/usuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario inserirUsuario(Usuario usuario) throws NoSuchAlgorithmException {
        usuario.setSenha(Hash.generateHash(usuario.getSenha()));
        usuario = usuarioDAO.incluir(usuario);
        return usuario;
    }

    @PUT
    @Path("/usuario/{idusuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario alterarUsuario(Usuario usuario) throws NoSuchAlgorithmException {
        usuario.setSenha(Hash.generateHash(usuario.getSenha()));
        usuario = usuarioDAO.alterar(usuario);
        return usuario;
    }

    @DELETE
    @Path("/usuario/{idusuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean excluirUsuario(@DefaultValue("0") @PathParam("idusuario") int idusuario) {
        return usuarioDAO.excluir(idusuario);
    }

    protected boolean checarToken(String token) {

        String dadosLogin = "";
        try {
            dadosLogin = Hash.base64decode(token);
            String dadosSplit[] = dadosLogin.split("&");
            String login = dadosSplit[0].replace("login=", "");
            String senha = dadosSplit[1].replace("senha=", "");

            Usuario usuario = new Usuario();
            usuario.setLogin(login);
            usuario.setSenha(senha);

            return usuarioDAO.checarLogin(usuario);
        } catch (Exception ex) {
            return false;
        }
    }

}
