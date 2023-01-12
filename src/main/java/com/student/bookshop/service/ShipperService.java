package com.student.bookshop.service;

import com.student.bookshop.model.Author;
import com.student.bookshop.model.Shipper;

import java.util.List;
import java.util.Optional;

public interface ShipperService {


    public void saveShipper(Shipper shipper);

    List<Shipper> findAllShippers();

    public void deleteShipperId(Long id);

    Optional<Shipper> findByIdUpdate(Long id);

}
