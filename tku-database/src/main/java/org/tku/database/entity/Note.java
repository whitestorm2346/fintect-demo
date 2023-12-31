package org.tku.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @Column(name = "name")
    private String noteName;

    @Column(name = "link")
    private String noteLink;

    @Column(name = "type")
    private String noteType;

    @Column(name = "access")
    private Integer access;
}
