package com.project.ecodein.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApprovalDTO {

    private Integer order_no;
    private Buyer buyer;
    private LocalDateTime approvalUpdateDate;
    private LocalDateTime approvalRegistDate;
    private Admin admin;
    private String subject;
    private Ordering ordering;
}
