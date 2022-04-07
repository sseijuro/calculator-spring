package com.github.seijuroseta.calculatorspring;

public class CSModel {
    public static class Result {
        public final String value;
        public final String type;

        Result(String value, String type) {
            this.value = value;
            this.type = type;
        }
    }

    public static class Response {
        public Result result;
        public Boolean hasError;
        public String message;

        Response(Result result) {
            this.result = result;
            hasError = false;
            message = null;
        }

        Response(String message) {
            this.message = message;
            hasError = true;
            result = null;
        }
    }

    public static class Pair {
        public Float lhs;
        public Float rhs;

        Pair(Float lhs, Float rhs) {
            this.lhs = lhs;
            this.rhs = rhs;
        }
    }
}
