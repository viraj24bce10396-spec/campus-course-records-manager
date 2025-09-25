package edu.ccrm.service;

public class MaxCreditLimitExceededException extends Exception {
    public MaxCreditLimitExceededException(String msg) { super(msg); }
}