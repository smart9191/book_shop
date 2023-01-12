package com.student.bookshop.service.impl;

import com.student.bookshop.model.Category;
import com.student.bookshop.repository.CategoryRepository;
import com.student.bookshop.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void saveBookCategory(Category bookCategory) {
        categoryRepository.save(bookCategory);
    }

    @Override
    public List<Category> findAllBookCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteBookCategoryId(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> findByIdUpdate(Long id) {
        return categoryRepository.findById(id);
    }

}
