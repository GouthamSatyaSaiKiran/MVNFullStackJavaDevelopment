package com.MVNFullStackProject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mechanic_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MechanicRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String mobile;

    private String issue;
    
    private String location;

    private LocalDateTime bookingTime; 

    private String status;
}
