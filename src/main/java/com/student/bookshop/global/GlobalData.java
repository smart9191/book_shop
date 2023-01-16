package com.student.bookshop.global;

import com.student.bookshop.model.Book;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {

    public static List<Book> cart;

    static {
        cart = new ArrayList<>();
    }

}
