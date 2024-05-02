package org.example.blogapiwithsecurity.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException {

    private HttpStatus status;
    private String message;


    public BlogApiException(HttpStatus status, String message)
    {
        this.message=message;
        this.status=status;
    }

    public BlogApiException(String message, HttpStatus status, String message1)
    {
        super(message);
        this.status = status;
        this.message= message;

    }
    public HttpStatus getStatus()
    {
        return status;
    }
}
