package com.project.ecodein.entity;

import com.project.ecodein.dto.Admin;
import com.project.ecodein.dto.Buyer;
import com.project.ecodein.dto.Ordering;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "approval")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Approval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_no")
    private Integer orderNo;

    @ManyToOne
    @JoinColumn(name = "buyer_code", referencedColumnName = "buyer_code", nullable = false)
    private Buyer buyer;

    @Column(name = "approval_update_date")
    private LocalDateTime approvalUpdateDate;

    @Column(name = "approval_regist_date", nullable = false)
    private LocalDateTime approvalRegistDate;

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "admin_id", nullable = true)
    private Admin admin;

    @Column
    private String subject;

    @OneToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "order_no", referencedColumnName = "order_no")
    private Ordering ordering;

}
