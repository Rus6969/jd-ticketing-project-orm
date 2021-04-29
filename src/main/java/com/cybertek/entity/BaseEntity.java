package com.cybertek.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
//we need provide listener class here
@EntityListeners(BaseEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //nullable false updatable false we do not want update the value it should be the same

    @Column(nullable = false,updatable = false)
    public LocalDateTime insertDateTime;
    @Column(nullable = false,updatable = false)
    public Long insertUserId;
    @Column(nullable = false)
    public LocalDateTime lastUpdateDateTime;
    @Column(nullable = false)
    public Long lastUpdateUserId;

    private Boolean isDeleted=false;
   // we remove those 2 methods  bc we are can not do an injection in entity class thats why we created class BaseEntityListener
//
//    // before saving object in db
//    @PrePersist
//    private void onPrePersist() {
//        this.insertDateTime = LocalDateTime.now();
//        this.lastUpdateDateTime = LocalDateTime.now();
//        this.lastUpdateUserId = 1l;
//        this.insertUserId = 1L;
//    }
//
//    @PreUpdate
//    public void onPreUpdaate() {
//        this.lastUpdateDateTime = LocalDateTime.now();
//        this.lastUpdateUserId = 1L;
//    }


}
