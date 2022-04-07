package com.github.seijuroseta.calculatorspring;

public class CSOperator {
    public interface IOperator<InputType> {
        CSModel.Result execute(InputType input);
    }

    public enum MathOperator implements IOperator<CSModel.Pair> {
        ADD, SUBTRACT, DIVIDE, MULTIPLY;

        @Override
        public CSModel.Result execute(CSModel.Pair input) {
            Float value;
            switch (this) {
                case ADD:
                    value = input.lhs + input.rhs;
                    break;
                case SUBTRACT:
                    value = input.lhs - input.rhs;
                    break;
                case DIVIDE:
                    if (input.rhs == 0) {
                        throw new IllegalStateException(MathOperatorError.DIVISION_BY_ZERO.message);
                    }
                    value = input.lhs / input.rhs;
                    break;
                case MULTIPLY:
                    value = input.lhs * input.rhs;
                    break;
                default:
                    throw new IllegalStateException(MathOperatorError.UNEXPECTED_OPERATOR.message);
            }
            if (value >= Float.MAX_VALUE) {
                throw new IllegalStateException(MathOperatorError.OUT_OF_BOUNDS.message);
            }
            return new CSModel.Result(String.valueOf(value), Float.class.getTypeName());
        }

        private enum MathOperatorError {
            UNEXPECTED_OPERATOR("Unexpected operator type"),
            DIVISION_BY_ZERO("Unexpected division by zero"),
            OUT_OF_BOUNDS("Max value out of bounds");

            private final String message;

            MathOperatorError(String message) {
                this.message = message;
            }
        }
    }

    public enum NotationOperator implements IOperator<String> {
        DEC_TO_HEX, DEC_TO_BIN, BIN_TO_DEC;

        @Override
        public CSModel.Result execute(String input) {
            String value;
            NotationOperatorType type;
            try {
                switch (this) {
                    case DEC_TO_HEX:
                        value = Integer.toHexString(Integer.parseInt(input));
                        type = NotationOperatorType.HEX;
                        break;
                    case DEC_TO_BIN:
                        value = Integer.toBinaryString(Integer.parseInt(input));
                        type = NotationOperatorType.BIN;
                        break;
                    case BIN_TO_DEC:
                        value = String.valueOf(Integer.parseInt(input, 2));
                        type = NotationOperatorType.DEC;
                        break;
                    default:
                        throw new IllegalStateException(NotationOperatorError.UNEXPECTED_OPERATOR.message);
                }
            } catch (NumberFormatException ignored) {
                throw new IllegalStateException(NotationOperatorError.BAD_INPUT.message);
            }

            return new CSModel.Result(value, type.name());
        }

        private enum NotationOperatorError {
            UNEXPECTED_OPERATOR("Unexpected operator type"),
            BAD_INPUT("Failed to encode: bad input");

            private final String message;

            NotationOperatorError(String message) {
                this.message = message;
            }
        }

        private enum NotationOperatorType {
            BIN, DEC, HEX;
        }
    }
}
