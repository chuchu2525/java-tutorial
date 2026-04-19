package com.example.demo.library.web;

import com.example.demo.library.controller.LibraryController;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = LibraryController.class)
public class LibraryApiExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(LibraryApiExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<Map<String, String>> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> {
                    Map<String, String> row = new LinkedHashMap<>();
                    row.put("field", fe.getField());
                    row.put("message", fe.getDefaultMessage());
                    return row;
                })
                .toList();
        return badRequest("入力内容に誤りがあります", fieldErrors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        log.debug("Failed to read HTTP message", ex);
        return badRequest("JSONの形式が正しくありません。キーと値の構文を確認してください。", List.of());
    }

    private static ResponseEntity<Map<String, Object>> badRequest(String message, List<Map<String, String>> errors) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", message);
        body.put("errors", errors);
        return ResponseEntity.badRequest().body(body);
    }
}
