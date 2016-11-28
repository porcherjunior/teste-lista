package br.com.teste.impl.database;

import br.com.teste.impl.model.UsuariosModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface UsuariosMapper {

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "nome", property = "nome"),
            @Result(column = "cpf", property = "cpf"),
            @Result(column = "data", property = "data")
    })
    @Select("select * from teste_junior")
    List<UsuariosModel> findAllUsuarios();

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "nome", property = "nome"),
            @Result(column = "cpf", property = "cpf"),
            @Result(column = "data", property = "data")
    })
    @Select("SELECT * FROM teste_junior WHERE id = #{id}")
    UsuariosModel findById(@Param("id") long id);

    @Delete("DELETE FROM teste_junior WHERE id = #{id}")
    Boolean deleteById(@Param("id") long id);

    @Update("UPDATE teste_junior SET nome = 'GUSTAVO' WHERE id = #{id}")
    Boolean updateById(@Param("id") long id);

    @Insert("INSERT INTO teste_junior (id, nome, cpf, data) VALUES (#{id}, #{nome}, #{cpf}, #{data})")
    public int insertUsuarios(UsuariosModel usuariosModel);
}
