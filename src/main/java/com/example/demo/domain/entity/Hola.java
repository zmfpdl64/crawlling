package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nullable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "\"hola\"")
public class Hola {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String num;
    private String postname;
    private String content;
    private String username;
    private String date;
    private String link;
    @Column(nullable = true)
    private String strings;
    private Integer views;
    private String talk;
    public Hola(String num, String postName, String content, String userName, String date, String link, String string, int views, String talk) {
        this.num = num;
        this.postname=postName;
        this.content=content;
        this.username=userName;
        this.date=date;
        this.link=link;
        this.strings=string;
        this.views=views;
        this.talk=talk;
    }
}
