package com.book.store.constant;

import org.springframework.stereotype.Component;

@Component
public class ExceptionConstant {
    public static final String BOOK_NOT_FOUND = "There is not a book by the given id";
    public static final String WRONG_ID = "Id is missing";
}
