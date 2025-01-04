package com.example.its.doamin.issue;

import org.apache.ibatis.annotations.*;

import java.util.List;

// @Repositoryは必要なくなる（@MapperでBeen登録が不要となる）
@Mapper
public interface IssueRepository {

    @Select("SELECT * FROM issues")
    List<IssueEntity> findAll();

    @Insert("INSERT INTO issues (summary, description) VALUES (#{summary}, #{description})")
    void insert(@Param("summary") String summary, @Param("description") String description);

    @Select("SELECT * FROM issues WHERE id = #{issueId}")
    IssueEntity findById(long issueId);

    @Delete("DELETE FROM issues where id=#{issueId};")
    void deleteById(long issueId);

    @Delete("DELETE FROM issues")
    void deleteAll();

    @Update("ALTER TABLE issues AUTO_INCREMENT = 1")
    void resetAutoIncrement();

}
