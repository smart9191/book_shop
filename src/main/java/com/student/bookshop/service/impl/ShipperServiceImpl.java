package com.student.bookshop.service.impl;

import com.student.bookshop.model.Shipper;
import com.student.bookshop.repository.ShipperRepository;
import com.student.bookshop.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipperServiceImpl implements ShipperService {

    @Autowired
    private ShipperRepository shipperRepository;

    @Override
    public void saveShipper(Shipper shipper) {
        shipperRepository.save(shipper);
    }

    @Override
    public List<Shipper> findAllShippers() {
        return shipperRepository.findAll();
    }

    @Override
    public void deleteShipperId(Long id) {
        shipperRepository.deleteById(id);
    }

    @Override
    public Optional<Shipper> findByIdUpdate(Long id) {
        return shipperRepository.findById(id);
    }
}
