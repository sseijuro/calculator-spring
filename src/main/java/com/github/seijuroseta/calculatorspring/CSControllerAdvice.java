package com.github.seijuroseta.calculatorspring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
@RequestMapping(value = "/calculator")
public class CSControllerAdvice {
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<CSModel.Response> handleMissingParam(MissingServletRequestParameterException ex) {
        String errorMessage = AdviceError.PARAM_MISSING.getMessage(ex.getParameterName());
        return new ResponseEntity<>(new CSModel.Response(errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CSModel.Response> handleMissmathParam(MethodArgumentTypeMismatchException ex) {
        String errorMessage = AdviceError.PARAM_INCORRECT.getMessage(ex.getParameter().getParameterName());
        return new ResponseEntity<>(new CSModel.Response(errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<CSModel.Response> handleIllegalState(IllegalStateException ex) {
        return new ResponseEntity<>(new CSModel.Response(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private enum AdviceError {
        PARAM_MISSING("is missing, but required"),
        PARAM_INCORRECT("is incorrect");

        private final String verdict;

        AdviceError(String verdict) {
            this.verdict = verdict;
        }

        public String getMessage(String parameter) {
            return "Parameter `" + parameter + "` " + verdict;
        }
    }
}
