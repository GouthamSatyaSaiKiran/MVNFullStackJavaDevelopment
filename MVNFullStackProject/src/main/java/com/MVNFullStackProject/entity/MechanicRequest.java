package com.MVNFullStackProject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mechanic_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MechanicRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String issueType;

    private String vehicleNumber;

    private String status;
}

