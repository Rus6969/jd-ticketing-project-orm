package com.cybertek.entity;


import com.cybertek.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Where;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "projects")
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Project extends BaseEntity {
    //validation on data base side , avoid duplicates of a project name, if schema was created before drop table first
    @Column(unique = true)
    private String projectCode;

    private String projectName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private User assignedManager;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Status projectStatus;

    private String projectDetail;


}
