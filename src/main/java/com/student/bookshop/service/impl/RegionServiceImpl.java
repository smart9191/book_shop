package com.student.bookshop.service.impl;

import com.student.bookshop.model.Region;
import com.student.bookshop.repository.RegionRepo;
import com.student.bookshop.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepo regionRepo;

    @Override
    public void saveRegion(Region region) {
        regionRepo.save(region);
    }

    @Override
    public List<Region> findAllRegion() {
        return regionRepo.findAll();
    }

    @Override
    public void deleteRegion(Long id) {
        regionRepo.deleteById(id);
    }

    @Override
    public Optional<Region> findByIdUpdateRegion(Long id) {
        return regionRepo.findById(id);
    }
}
