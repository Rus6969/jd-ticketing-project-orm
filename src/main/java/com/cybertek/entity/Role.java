package com.cybertek.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    // no arg constructor is used to carry object
    private String description;
    //// we can remove because we are using manyToOne relationship
//
//    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
//    private List<User> users = new ArrayList<>();

}
