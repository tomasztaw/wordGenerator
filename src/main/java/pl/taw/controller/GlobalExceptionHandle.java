/**
 * Created by tomasz_taw
 * Date: 02.11.2023
 * Time: 20:12
 * Project Name: wordGenerator
 * Description:
 */
package pl.taw.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.taw.exception.BadRequestException;

import java.util.concurrent.TimeoutException;

@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<String> handleTimeoutException(TimeoutException e) {
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Przekroczono czas oczekiwania na odpowiedź.");
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
