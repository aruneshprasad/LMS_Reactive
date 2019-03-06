package com.lms.service;

import com.lms.model.StudentDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {

    Flux<StudentDetails> findAll();
    Mono<StudentDetails> findById(String id);
    Mono<StudentDetails> addStudent(StudentDetails student);
    Mono<Void> deleteById(String id);

}
