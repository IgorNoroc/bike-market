package com.igornoroc.auth.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.OffsetDateTime;

@MappedSuperclass
public class BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private OffsetDateTime created;

    @LastModifiedBy
    private OffsetDateTime updated;

    @Enumerated(EnumType.STRING)
    private Status status;
}
