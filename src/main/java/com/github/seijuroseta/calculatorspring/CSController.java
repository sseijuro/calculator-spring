package com.github.seijuroseta.calculatorspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/calculator")
public class CSController {
    @Autowired
    private CSService csService;

    @RequestMapping(value = "/math", method = RequestMethod.GET)
    public ResponseEntity<CSModel.Response> getMath(@RequestParam CSOperator.MathOperator operator,
                                                    @RequestParam Float lhs,
                                                    @RequestParam Float rhs) throws IllegalStateException {
        CSModel.Result result = csService.solve(operator, lhs, rhs);
        return new ResponseEntity<>(new CSModel.Response(result), HttpStatus.OK);
    }

    @RequestMapping(value = "/notation", method = RequestMethod.GET)
    public ResponseEntity<CSModel.Response> getNotation(@RequestParam CSOperator.NotationOperator operator,
                                                        @RequestParam String input) throws IllegalStateException {
        CSModel.Result result = csService.solve(operator, input);
        return new ResponseEntity<>(new CSModel.Response(result), HttpStatus.OK);
    }
}
