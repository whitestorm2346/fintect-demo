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
@Table(name = "Notes")
public class Note {
    @Id
    @Column(name = "Note_Name")
    private String noteName;

    @Column(name = "Link")
    private String noteLink;

    @Column(name = "Type")
    private String noteType;

    @Column(name = "Access")
    private Integer access;
}
