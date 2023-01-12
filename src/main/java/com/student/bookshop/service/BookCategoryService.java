package com.student.bookshop.service;

import com.student.bookshop.model.Category;

import java.util.List;
import java.util.Optional;

public interface BookCategoryService {

    public void saveBookCategory(Category bookCategory);

    List<Category> findAllBookCategories();

    public void deleteBookCategoryId(Long id);

    Optional<Category> findByIdUpdate(Long id);

}
