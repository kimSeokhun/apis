package com.chequer.ax5.api.demo.entity.grid.order;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "GRID_ORDER_L")
public class AX5GridOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    Long id;

    @Column(name = "A")
    String a;

    @Column(name = "B")
    String b;

    @Column(name = "PRICE")
    int price;

    @Column(name = "AMOUNT")
    int amount;

    @Column(name = "COST")
    int cost;

    @Column(name = "SALE_DT")
    Date saleDt;

    @Column(name = "CUSTOMER")
    String customer;

    @Column(name = "USER_TYPE")
    String userType;
}
