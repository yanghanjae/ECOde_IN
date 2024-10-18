package com.project.ecodein.dto;

import java.time.LocalDateTime;
import com.project.ecodein.entity.Admin;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "approval_status_lable")
public class ApprovalStatusLable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer status_no;

    @Column(name = "update_lable_date", nullable = false)
    private LocalDateTime updateLableDate;

    @Column(nullable = false)
    private byte status;

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "admin_id", nullable = false)
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "approval_no", referencedColumnName = "approval_no", nullable = false)
    private Approval approval;
}
