package io.github.biezhi.mybatis.mapper;

import org.apache.ibatis.annotations.Update;

/**
 * @author biezhi
 * @date 2018/1/16
 */
public interface VillageMapper {
    @Update("CREATE TABLE `village` (\n" +
            "\t`id` INT(10) NOT NULL AUTO_INCREMENT,\n" +
            "\t`name` VARCHAR(50) NULL DEFAULT NULL,\n" +
            "\t`district` VARCHAR(50) NULL DEFAULT NULL,\n" +
            "\tPRIMARY KEY (`id`)\n" +
            ")")
    void schema();
}
