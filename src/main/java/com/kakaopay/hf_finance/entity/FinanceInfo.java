package com.kakaopay.hf_finance.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "financeinfo")

public class FinanceInfo implements Serializable {

    private static final long serialVersionUID = -2878579251659145883L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year")
    private int year;

    @Column(name = "month")
    private int month;

    @Column(name = "amount")
    private Long amount;

    @ManyToOne
    @JoinColumn
    private Institute institute;

    public Long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public Long getAmount() {
        return amount;
    }

    public Institute getInstitute() {
        return institute;
    }

    public FinanceInfo(int year, int month, Long amount, Institute institute) {
        this.year = year;
        this.month = month;
        this.amount = amount;
        this.institute = institute;
    }

    @Override
    public String toString() {
        return "FinanceInfo{" +
                "id=" + id +
                ", year=" + year +
                ", month=" + month +
                ", amount=" + amount +
                ", institute=" + institute +
                '}';
    }
}
