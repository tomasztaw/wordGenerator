package pl.taw.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.taw.exception.BadRequestException;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandleTest {


    private final GlobalExceptionHandle exceptionHandler = new GlobalExceptionHandle();

    @Test
    public void testHandleTimeoutException() {
        TimeoutException timeoutException = new TimeoutException("Przekroczono czas oczekiwania.");
        ResponseEntity<String> response = exceptionHandler.handleTimeoutException(timeoutException);

        assertEquals(HttpStatus.REQUEST_TIMEOUT, response.getStatusCode());
        assertEquals("Przekroczono czas oczekiwania.", response.getBody());
    }

    @Test
    public void testHandleSocketTimeoutException() {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("Limit czasu przekroczony.");
        ResponseEntity<String> response = exceptionHandler.handleSocketTimeoutException(socketTimeoutException);

        assertEquals(HttpStatus.REQUEST_TIMEOUT, response.getStatusCode());
        assertEquals("Żądanie przekroczyło limit czasu: Limit czasu przekroczony.", response.getBody());
    }

    @Test
    public void testHandleBadRequestException() {
        BadRequestException badRequestException = new BadRequestException("Niepoprawne żądanie.");
        ResponseEntity<String> response = exceptionHandler.handleBadRequestException(badRequestException);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Niepoprawne żądanie.", response.getBody());
    }

}