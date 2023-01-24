package com.student.bookshop.repository;

import com.student.bookshop.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepo extends JpaRepository<Book,Long> {
    List<Book> findAllByCategories_Id(Long id);
    List<Book> findAllByPublisher_Id(Long id);
    List<Book> findAllByLanguage_Id(Long id);

}
