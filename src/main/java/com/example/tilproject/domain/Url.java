package com.example.tilproject.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    private String urlTurn;

    @Column(nullable = false)
    private String urlName;

    private String url;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UrlSection role;

    public Url(String url, String urlName, String urlTurn, UrlSection role) {
        this.urlTurn = urlTurn;
        this.urlName = urlName;
        this.url = url;
        this.role = role;
    }

}
