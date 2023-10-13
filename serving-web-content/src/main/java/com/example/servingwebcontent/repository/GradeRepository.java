package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.models.GradesStudents;
import com.example.servingwebcontent.models.Students;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends CrudRepository<GradesStudents,Integer> {
    List<GradesStudents> findByStudent(Students studentToDelete);
}
