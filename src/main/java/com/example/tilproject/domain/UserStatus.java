package com.example.tilproject.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserStatus implements Serializable {

    @Id
    @JoinColumn(name = "userId")
    @OneToOne
    private User user;

    @Column(nullable = false)
    private String unregister;

    @Column(nullable = false)
    private String registerDate;

    private String unregisterDate;
}
