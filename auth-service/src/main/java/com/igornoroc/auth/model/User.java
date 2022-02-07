package com.igornoroc.auth.model;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private OffsetDateTime created;
    private OffsetDateTime update;

    @PrePersist
    private void created() {
        created = OffsetDateTime.now();
    }

    @PreUpdate
    private void updated() {
        update = OffsetDateTime.now();
    }
}
