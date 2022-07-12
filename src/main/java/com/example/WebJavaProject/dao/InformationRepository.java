package com.example.WebJavaProject.dao;

import com.example.WebJavaProject.entity.Information;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformationRepository extends JpaRepository<Information,Integer> {

    public List<Information> findAllByUser_Id(int id);

    public List<Information> findAllByUser_Id(int id, Sort sort);


}
