package com.example.demo.respository;

import com.example.demo.domain.entity.CatchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatchRepository extends JpaRepository<CatchEntity, Integer> {
    @Override
    Page<CatchEntity> findAll(Pageable pageable);
}
