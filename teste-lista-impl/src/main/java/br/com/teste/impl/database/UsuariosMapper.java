package br.com.teste.impl.database;

import br.com.teste.impl.model.UsuariosModel;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable("defaultCache")
    List<UsuariosModel> findAllUsuarios();

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "nome", property = "nome"),
            @Result(column = "cpf", property = "cpf"),
            @Result(column = "data", property = "data")
    })
    @Select("SELECT * FROM teste_junior WHERE id = #{id}")
    @Cacheable("defaultCache")
    UsuariosModel findById(@Param("id") long id);

    @Delete("DELETE FROM teste_junior WHERE id = #{id}")
    @Cacheable("defaultCache")
    Boolean deleteById(@Param("id") long id);

    @Update("UPDATE teste_junior SET nome = 'GUSTAVO' WHERE id = #{id}")
    @Cacheable("defaultCache")
    Boolean updateById(@Param("id") long id);

    @Insert("INSERT INTO teste_junior (id, nome, cpf, data) VALUES (#{id}, #{nome}, #{cpf}, #{data})")
    @Cacheable("defaultCache")
    public int insertUsuarios(UsuariosModel usuariosModel);
}
