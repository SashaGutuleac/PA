package org.example.homework11;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface StudentHwRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.school.name = :schoolName")
    List<Student> findStudentsBySchoolName(@Param("schoolName") String schoolName);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.name = :newName WHERE s.id = :studentId")
    void updateStudentName(@Param("studentId") Long studentId, @Param("newName") String newName);
}