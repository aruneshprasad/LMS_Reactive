package com.lms.handler;

import com.lms.model.Response;
import com.lms.model.StudentDetails;
import com.lms.repo.StudentRepository;
import com.lms.service.StudentService;
import com.lms.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class StudentHandler {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ResponseBuilder responseBuilder;

    public Mono<ServerResponse> findAll(ServerRequest serverRequest){
        return studentService.findAll()
                /*.filter(
                        student -> !student.getsId().equals("724467")
                )*/
                .collectList()
                .flatMap(
                        students -> {
                            Response<List<StudentDetails>> response = responseBuilder.getResponse(students);
                            return ServerResponse.ok().body(Mono.just(response), Response.class);
                        }

                );
    }

    public Mono<ServerResponse> findById(ServerRequest serverRequest){
        return studentService.findById(serverRequest.pathVariable("id"))
                .flatMap(
                        student -> {
                            Response<StudentDetails> response = responseBuilder.getResponse(student);
                            return ServerResponse.ok().body(Mono.just(response), Response.class);
                        }
                );

    }

    /*public Mono<ServerResponse> findById(ServerRequest serverRequest){
        return studentService.findById(serverRequest.pathVariable("id"))
                .map(
                        student -> {
                            Response<StudentDetails> response = responseBuilder.getResponse(student);
                            Mono<ServerResponse> sr = ServerResponse.ok().body(Mono.just(response), Response.class);
                            return sr;
                        })
                .flatMap(
                        sr -> sr
                );

    }*/

    public Mono<ServerResponse> create(ServerRequest serverRequest){
        return serverRequest.bodyToMono(StudentDetails.class)
                .flatMap(newStudent -> this.studentService.addStudent(newStudent))
                .flatMap(
                        createdStudent -> {
                            Response<StudentDetails> response = responseBuilder.getResponse(createdStudent);
                            return ServerResponse.ok().body(Mono.just(response), Response.class);
                        });
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest){
        return serverRequest.bodyToMono(StudentDetails.class)
                .flatMap(
                        updateStudent -> {
                            return this.studentService.findById(serverRequest.pathVariable("id"))
                                    .filter(
                                            existingStudent -> {
                                                return (existingStudent.getsId().equals(updateStudent.getsId()));
                                            })

                                    .zipWith(
                                            Mono.just(updateStudent),
                                            (existingStudent, reqBodyStudent) -> {
                                                existingStudent.setsId(reqBodyStudent.getsId());
                                                existingStudent.setStudentId(reqBodyStudent.getStudentId());
                                                existingStudent.setName(reqBodyStudent.getName());
                                                existingStudent.setEmail(reqBodyStudent.getEmail());
                                                existingStudent.setPassword(reqBodyStudent.getPassword());
                                                existingStudent.setBooksBorrowed(reqBodyStudent.getBooksBorrowed());
                                                return existingStudent;
                                            });
                        })
                .flatMap(toBeSavedStudent -> this.studentService.addStudent(toBeSavedStudent))
                .flatMap(
                        savedStudent -> {
                            Response<StudentDetails> response = responseBuilder.getResponse(savedStudent);
                            return ServerResponse.ok().body(Mono.just(response), Response.class);
                        });
    }

    public Mono<ServerResponse> deleteById(ServerRequest serverRequest){
        return studentService.deleteById(serverRequest.pathVariable("id"))
                .thenReturn(serverRequest.pathVariable("id"))
                .flatMap(
                        deleteId -> {
                            Response<?> response = responseBuilder.getResponseString("Deleted");
                            return ServerResponse.ok().body(Mono.just(response), Response.class);
                        });
    }
}
