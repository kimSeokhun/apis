package com.chequer.ax5.api.demo.entity.grid;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "GRID_L")
public class AX5Grid implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    Long id;

    @Column(name = "A")
    String a;

    @Column(name = "B")
    String b;

    @Column(name = "C")
    int c;

    @Column(name = "D")
    String d;

    @Column(name = "E")
    String e;

    @Column(name = "F")
    String f;

    @Column(name = "G")
    String g;
}
