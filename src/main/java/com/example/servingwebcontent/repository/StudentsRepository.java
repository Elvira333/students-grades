package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.models.StudentInfoDTO;
import com.example.servingwebcontent.models.Students;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends CrudRepository<Students, Integer> {
    @Query("SELECT NEW com.example.servingwebcontent.models.StudentInfoDTO(s.id, s.name, s.age) " +
            "FROM Students s " +
            "JOIN s.grades g " +
            "GROUP BY s.id " +
            "HAVING AVG(g.grade) >= 4.0")
    List<StudentInfoDTO> findStudentsInfoWithAverageGradeGreaterThanOrEqual(@Param("minGrade") double minGrade);

    Students findByName(String name);

    List<Students> findAllByName(String studentName);

}
