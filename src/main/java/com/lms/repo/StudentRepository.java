package com.lms.repo;

import com.lms.model.StudentDetails;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;


public interface StudentRepository extends ReactiveCouchbaseRepository<StudentDetails, String> {

    /*@Query("#{#n1ql.selectEntity} where #{#n1ql.filter} and studentId = $1 and $2 within #{#n1ql.bucket} ")
    List<StudentLogin> checkLogin(String studentId, String password);*/

}
