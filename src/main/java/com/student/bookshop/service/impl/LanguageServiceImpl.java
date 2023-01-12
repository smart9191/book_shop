package com.student.bookshop.service.impl;

import com.student.bookshop.model.Language;
import com.student.bookshop.repository.LanguageRepository;
import com.student.bookshop.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository languageRepository;
    @Override
    public void saveLanguage(Language language) {
        languageRepository.save(language);
    }

    @Override
    public List<Language> findAllLanguage() {
        return languageRepository.findAll();
    }

    @Override
    public void deleteLanguage(Long id) {
        languageRepository.deleteById(id);
    }

    @Override
    public Optional<Language> findByIdUpdateLanguage(Long id) {
        return languageRepository.findById(id);
    }
}
