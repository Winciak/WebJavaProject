package com.example.WebJavaProject.service;

import com.example.WebJavaProject.dao.InformationRepository;
import com.example.WebJavaProject.entity.Information;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InformationServiceImpl implements InformationService {

    private InformationRepository informationRepository;

    public InformationServiceImpl(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    @Override
    public List<Information> findAllByUserId(int id) {
        return informationRepository.findAllByUser_Id(id);
    }

    @Override
    public List<Information> findAllByUserId(int theId, String sortField, String sortDirection) {

        Sort sort;
        Sort.Order order;
        if (sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())) order = new Sort.Order(Sort.Direction.ASC, sortField).ignoreCase();
        else order = new Sort.Order(Sort.Direction.DESC, sortField).ignoreCase();

        sort=Sort.by(order);

        return informationRepository.findAllByUser_Id(theId, sort);
    }

    @Override
    public Information findById(int theId) {

        Optional<Information> result = informationRepository.findById(theId);

        Information theInformation;

        if (result.isPresent()) {
            theInformation = result.get();
        }
        else {
            throw new RuntimeException("Did not find information id - " + theId);
        }

        return theInformation;
    }

    @Override
    public void save(Information theInformation) {
        informationRepository.save(theInformation);
    }

    @Override
    public void deleteById(int theId) {
        informationRepository.deleteById(theId);
    }


}
