package br.com.teste.impl.service;

import br.com.teste.impl.database.UsuariosMapper;
import br.com.teste.impl.model.UsuariosModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuariosService {
    @Autowired
    private UsuariosMapper usuariosMapper;

    public List<UsuariosModel> findAllUsuarios() {return usuariosMapper.findAllUsuarios();}

    public UsuariosModel findById(long id) {return usuariosMapper.findById(id);}

    public Boolean deleteById(long id) {return usuariosMapper.deleteById(id);}

    public Boolean updateById(long id) {return usuariosMapper.updateById(id);}

    public int insertUsuarios(UsuariosModel usuariosModel){return usuariosMapper.insertUsuarios(usuariosModel);}
}
