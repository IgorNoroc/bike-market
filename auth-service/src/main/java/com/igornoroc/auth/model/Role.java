package com.igornoroc.auth.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@Entity(name = "roles")
public class Role extends BasicEntity {
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<User> users;
}
