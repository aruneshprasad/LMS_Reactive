package com.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.model.StudentDetails;
import com.lms.repo.StudentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepo;

    @Override
    public Flux<StudentDetails> findAll() {
        return studentRepo.findAll();
    }

    @Override
    public Mono<StudentDetails> findById(String id) {
        return studentRepo.findById(id);
    }

    @Override
    public Mono<StudentDetails> addStudent(StudentDetails student) {
       //System.out.println(student.getBooksBorrowed());
       return studentRepo.save(student);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return studentRepo.deleteById(id);
    }


}
