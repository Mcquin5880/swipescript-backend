package com.mcq.swipescriptbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

// todo temp code for testing errors - remove

@RestController
@RequestMapping("/api/buggy")
public class TempBuggyController {

    @GetMapping("/not-found")
    public void simulateNotFound() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
    }

    @GetMapping("/bad-request")
    public void simulateBadRequest() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request error");
    }

    @GetMapping("/internal-server-error")
    public void simulateServerError() {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
    }

    @GetMapping("/unauthorized")
    public void simulateUnauthorized() {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access");
    }

    @GetMapping("/forbidden")
    public void simulateForbidden() {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden access");
    }
}
