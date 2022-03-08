package com.example.fitnessapp.repository;

import com.example.fitnessapp.model.BMI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BMIRepository extends JpaRepository<BMI, Long> {
}
