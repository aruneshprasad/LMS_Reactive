package com.lms;

import com.lms.handler.StudentHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class LmsApplication {

    /*@Bean
    public FilterRegistrationBean jwtFilterStudent() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilterStudent());
        registrationBean.addUrlPatterns("/lms/authorizedStudent/*");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean jwtFilterAdmin() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilterAdmin());
        registrationBean.addUrlPatterns("/lms/authorizedAdmin/*");

        return registrationBean;
    }*/

    @Bean
    public RouterFunction<ServerResponse> routesStudentDetailsClass(
            StudentHandler studentHandler) {
        return route(
                GET("/lms/findAllStudents"), studentHandler::findAll)
                .andRoute(
                        GET("/lms/findById/{id}"), studentHandler::findById)
                .andRoute(
                        PUT("/lms/update/{id}"), studentHandler::update)
                .andRoute(
                        POST("/lms/create"), studentHandler::create)
                .andRoute(
                        DELETE("/lms/deleteById/{id}"), studentHandler::deleteById);
    }

    public static void main(String[] args) {
        SpringApplication.run(LmsApplication.class, args);
    }

}
