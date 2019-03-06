package com.lms.util;

import com.lms.model.Response;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseBuilder {

    public <T> Response<List<T>> getResponse(List<T> data) {
        Response<List<T>> response = new Response<List<T>>(data);
        return response;
    }

    public <T> Response<T> getResponse(T data) {
        Response<T> response = new Response<T>(data);
        return response;
    }
    public <String> Response<String> getResponseString(String data) {
        Response<String> response = new Response<String>(data);
        return response;
    }
}
