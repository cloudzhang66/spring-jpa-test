package com.iless;

import com.iless.config.JpaConfig;
import com.iless.entity.Student;
import com.iless.entity.dao.StudentDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);

        StudentDao dao = context.getBean(StudentDao.class);
//        IntStream.range(0,100).forEach(x->{
//
//            Student student = new Student();
//            student.setAge((int)(Math.random()*1000)/10).setSex(true);
//            dao.save(student);
//            System.out.println(student);
//        });

        long id = 10;
        System.out.println("updateNameById:"+dao.updateNameById("name-"+10, id));
        Optional<Student> opt = dao.findById(id);
        System.out.println("findById:"+opt.get());

        System.out.println("findByAgeAndNameLike");
        System.out.println(dao.findByAgeAndNameLike(77, "name%"));


        System.out.println("getFirstByAge:"+dao.getFirstByAge(77));


        System.out.println("readTop3ByAgeOrderByCreateTime");
        System.out.println(dao.readTop3ByAgeOrderByCreateTime(77));
        System.out.println(dao.findByAgeOrderByCreateTimeDesc(77));
    }
}
