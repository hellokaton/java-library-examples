package io.github.biezhi.mybatis.mapper;

import io.github.biezhi.mybatis.model.TransactionToken;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

/**
 * An article on these annotations can be found at:
 * http://loianegroner.com/2011/02/getting-started-with-ibatis-mybatis-annotations/
 * <p/>
 * Notice how the SQL statements are broken up into more readable multiple strings.
 * myBatis automatically adds spaces, but otherwise the statements are the same.
 */
public interface TransactionTokenMapper {

    /**
     * In this example, the DDL is invoked during the test setup.  It would be easy to envision using this kind
     * of functionality to both intialize the database for test purposes as well as validating the bindings. For
     * example, a standard schema() method and a validate() method, called as part of the factory setup.
     */
    @Update("create table trans_token (id bigint auto_increment, trans_id varchar, token_id varchar)")
    void schema();

    @Select({
            "select * from trans_token where id = #{id}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "trans_id", property = "transaction", jdbcType = JdbcType.VARCHAR),
            @Result(column = "token_id", property = "token", jdbcType = JdbcType.VARCHAR)
    })
    TransactionToken getById(long id);


    /**
     * Note the use of the options annotation to tell myBatis to put the returned id
     * into the object when the object is successfully inserted.
     */
    @Insert({
            "insert into trans_token (trans_id, token_id)",
            "values (#{transaction}, #{token})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(TransactionToken record);


    @Select("select count(*) from trans_token")
    long count();

    @Delete({
            "delete from trans_token where id = #{id}"
    })
    void deleteById(TransactionToken token);

    @Delete({
            "delete from trans_token where trans_id = #{transaction}"
    })
    void deleteByTransaction(TransactionToken token);

    @Update({
            "update trans_token",
            "set trans_id = #{transaction},",
            "token_id = #{token}",
            "where id = #{id}"
    })
    int update(TransactionToken record);

    @Select({
            "select id, trans_id, token_id",
            "from trans_token where trans_id = #{transaction}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "trans_id", property = "transaction", jdbcType = JdbcType.VARCHAR),
            @Result(column = "token_id", property = "token", jdbcType = JdbcType.VARCHAR)
    })
    TransactionToken selectByTransaction(String transaction);
}