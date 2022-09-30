package com.sweepy.exception;

public class NullRequest extends Throwable {
    public NullRequest(String errorMessage) {
        super(errorMessage);
    }
}
