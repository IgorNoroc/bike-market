package com.igornoroc.auth.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
