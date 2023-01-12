package com.student.bookshop.service;

import com.student.bookshop.model.Language;
import com.student.bookshop.model.Region;

import java.util.List;
import java.util.Optional;

public interface LanguageService {


    public void saveLanguage(Language language);

    List<Language> findAllLanguage();

    public void deleteLanguage(Long id);

    Optional<Language> findByIdUpdateLanguage(Long id);
}
