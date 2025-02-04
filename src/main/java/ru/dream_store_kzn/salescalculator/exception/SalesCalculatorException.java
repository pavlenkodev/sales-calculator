package ru.dream_store_kzn.salescalculator.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SalesCalculatorException extends RuntimeException {

    public SalesCalculatorException(String message, Throwable cause) {
        super(message);
    }
}
