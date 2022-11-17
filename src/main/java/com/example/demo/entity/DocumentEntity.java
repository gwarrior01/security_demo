package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "documents")
@Entity
public class DocumentEntity {

    @Id
    private long id;
    private String name;
    private String ownerName;

}
