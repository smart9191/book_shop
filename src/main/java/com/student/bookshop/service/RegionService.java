package com.student.bookshop.service;

import com.student.bookshop.model.Publisher;
import com.student.bookshop.model.Region;
import com.student.bookshop.repository.RegionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface RegionService {

    public void saveRegion(Region region);

    List<Region> findAllRegion();

    public void deleteRegion(Long id);

    Optional<Region> findByIdUpdateRegion(Long id);



}
