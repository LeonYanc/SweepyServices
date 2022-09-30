package com.sweepy.exception;

public class EmptyEntry extends Throwable{
    public EmptyEntry(String errorMessage) {
        super(errorMessage);
    }
}
