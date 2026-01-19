package org.example.feemanagementsystem.repository;

import org.example.feemanagementsystem.domain.entity.FeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface FeeTypeRepository extends JpaRepository<FeeType, Long> {
}
