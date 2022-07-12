package com.example.WebJavaProject.service;

import com.example.WebJavaProject.entity.Information;

import java.util.List;

public interface InformationService {


    List<Information> findAllByUserId(int theId);

    List<Information> findAllByUserId(int theId, String sortField, String sortDirection);

    Information findById(int theId);

    void save(Information theInformation);

    void deleteById(int theId);



}
