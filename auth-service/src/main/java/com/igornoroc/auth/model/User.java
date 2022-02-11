package com.igornoroc.auth.model;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(unique = true)
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @Column(updatable = false)
    private OffsetDateTime created;
    private OffsetDateTime updated;

    @PrePersist
    private void setCreated() {
        created = OffsetDateTime.now();
    }

    @PreUpdate
    private void setUpdated() {
        updated = OffsetDateTime.now();
    }
}
