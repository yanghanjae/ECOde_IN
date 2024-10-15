package com.project.ecodein.entity;

import com.project.ecodein.dto.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "return")
public class Return {

    @Id
    @Column(name = "return_id")
    private String returnId;

    @ManyToOne
    @JoinColumn(name = "return_buyer_code", referencedColumnName = "buyer_code")
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "return_buyer_manager", referencedColumnName = "user_id")
    private User user;

    @Column(name = "return_reason")
    private String returnReason;

    @Column(name = "return_regist_date")
    private Date returnRegistDate;

}
