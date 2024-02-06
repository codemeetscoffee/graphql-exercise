package com.cmc.graphql.datasource.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Problemz {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @CreationTimestamp
    private LocalDateTime creationTimestamp;
    private String title;
    private String content;
    private String tags;

//    @OrderBy("creationTimestamp desc")
    @OneToMany(mappedBy = "problems")
    private List<Solutionz> solutions;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Userz createdBy;
}
