package com.MVNFullStackProject.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "branches")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String branchName;

    private String address;

    private String contactNumber;
}

