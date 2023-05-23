package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;

@Table( name="\"user\"", indexes = {@Index(columnList = "username")})
@Getter
@SQLDelete(sql = "update \"user\" set deleted_at =  now() where id = ?")
@Where(clause="deleted_at is null")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private Timestamp created_at;
    private Timestamp modified_at;
    private Timestamp deleted_at;
}
