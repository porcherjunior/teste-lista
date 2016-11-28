package br.com.teste.contract.v1;

import br.com.teste.impl.model.UsuariosModel;
import br.com.teste.impl.service.UsuariosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1")
@Api(tags = "/v1")
public class RestEndpoint {
    @Autowired
    UsuariosService usuariosService;

    @GET
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna todos os usuários")
    public List<UsuariosModel> getUsuarios() {
        return usuariosService.findAllUsuarios();
    }

    @GET
    @Path("/usuarios/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retorna usuário por id")
    public UsuariosModel findById(@PathParam("id") @ApiParam(value = "Id do usuário",required = true) Long id) {
        return usuariosService.findById(id);
    }

    @DELETE
    @Path("/usuarios/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Deleta usuário por id")
    public Boolean deleteById(@PathParam("id") @ApiParam(value = "Id do usuário",required = true) Long id){
        return usuariosService.deleteById(id);
    }

    @POST
    @Path("/usuarios/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Atualiza usuário por id")
    public Boolean updateById(@PathParam("id") @ApiParam(value = "Id do usuário",required = true) Long id){
        return usuariosService.updateById(id);
    }

    @POST
    @Path("/usuarios/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Insere usuário")
    public int insertUsuarios(UsuariosModel usuariosModel){
        return usuariosService.insertUsuarios(usuariosModel);
    }
}
