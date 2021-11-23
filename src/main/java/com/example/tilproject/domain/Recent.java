package com.example.tilproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Recent extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String title;

    private String content;

    @Column(nullable = false)
    private String turn;

    private String blogUrl;

    @JsonIgnoreProperties({"recents"})
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    @ManyToOne
    private User user;
}
