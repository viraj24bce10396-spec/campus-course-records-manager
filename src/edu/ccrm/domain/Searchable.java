package edu.ccrm.service;

@FunctionalInterface
public interface Searchable<T> {
    boolean matches(T t);
}