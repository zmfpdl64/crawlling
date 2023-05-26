package com.example.demo.respository;

import com.example.demo.domain.entity.Hola;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HolaRepsitory extends JpaRepository<Hola, Integer> {
    @Query(value="select h from Hola h where h.content like %:content%")
    Page<Hola> findByKeyWord(@Param("content") String content, Pageable pageable);
}
