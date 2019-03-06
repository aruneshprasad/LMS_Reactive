package com.lms.model;

import java.util.Objects;

public class Response<T> {

    private T response;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public Response(T response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response<?> response1 = (Response<?>) o;
        return Objects.equals(response, response1.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(response);
    }

    @Override
    public String toString() {
        return "Response{" +
                "response=" + response +
                '}';
    }


}
