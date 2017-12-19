package com.example.balvinder.newsapplication.exceptions;

/**
 * Created by balvinder on 18/12/17.
 */


public class NewsException extends Exception {

    private ErrorMessage errorMessage;

    public NewsException(ErrorMessage message) {
        this.errorMessage = message;
    }

    public NewsException() {

    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
