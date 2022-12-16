package com.gerasimchuk.openinviewexample.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sample")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SampleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "sample")
    private String sample;
}
