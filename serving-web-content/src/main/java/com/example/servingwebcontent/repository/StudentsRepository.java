package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.models.Students;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends CrudRepository<Students,Integer> {
    Students findByName(String name);

    List<Students> findAllByName(String studentName);

    @Query("SELECT DISTINCT s.id, s.name, s.age FROM Students s JOIN s.grades gs GROUP BY s HAVING AVG(gs.grade) >= 4")
    List<Object[]> findUniqueStudentInfoWithAverageGradeGreaterThanOrEqual4();

}
