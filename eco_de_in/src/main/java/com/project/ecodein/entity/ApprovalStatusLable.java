package com.project.ecodein.entity;

import com.project.ecodein.dto.Admin;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "approval_status_lable")
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApprovalStatusLable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_no", insertable = false, updatable = false)
    private Integer orderNo;

    @Column(name = "update_label_date")
    private LocalDateTime updateLableDate;

    @Column
    private byte status;

    @ManyToOne
    @JoinColumn(name = "admin_in", referencedColumnName = "admin_id")
    private Admin admin;

    @ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "order_no", referencedColumnName = "order_no")
    private Approval approval;
}
