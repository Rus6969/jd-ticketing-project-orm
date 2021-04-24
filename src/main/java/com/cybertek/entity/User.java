package com.cybertek.entity;

import com.cybertek.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
//works with repository belongs to this entity, all queries in repository  will be with "where" is_deleted false
@Where(clause = "is_deleted=false")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;
    private boolean enabled;
    private String phone;


    @Enumerated(EnumType.STRING)
    private Gender gender;
// we deleted fetch type bc its created infinite loop after added security portion
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


}