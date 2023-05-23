package com.example.demo.respository;

import com.example.demo.domain.entity.Hola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolaRepsitory extends JpaRepository<Hola, Integer> {
}
