package com.kakaopay.hf_finance.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "institute")
@Data
@EqualsAndHashCode(exclude = "financeInfos")

public class Institute implements Serializable {

    private static final long serialVersionUID = 5860335269250167305L;
    @Id
    @Column(name = "code", nullable = false, length = 7)
    private String code;
    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @OneToMany(mappedBy = "institute", cascade = CascadeType.ALL)
    private Set<FinanceInfo> financeInfos;

    public Institute(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Set<FinanceInfo> getFinanceInfos() {
        return financeInfos;
    }

    @Override
    public String toString() {
        return "Institute{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", financeInfos=" + financeInfos +
                '}';
    }
}
