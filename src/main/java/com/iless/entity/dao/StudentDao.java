package com.iless.entity.dao;

import com.iless.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentDao extends JpaRepository<Student, Long> {

    List<Student> findByAgeAndNameLike(int age, String name);

    Student getFirstByAge(int age);

    @Query(value = "select * from student where age = ?1 order by createTime desc limit 3", nativeQuery = true)
    List<Student> readTop3ByAgeOrderByCreateTime(int age);

    List<Student> findByAgeOrderByCreateTimeDesc(int age);


    @Transactional
    @Modifying
    @Query(value = "update student set name = :name where id = :id", nativeQuery = true)
    int updateNameById(@Param("name")String name, @Param("id") long id);
}
