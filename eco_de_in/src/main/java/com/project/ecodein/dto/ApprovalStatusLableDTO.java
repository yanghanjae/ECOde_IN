package com.project.ecodein.dto;

import com.project.ecodein.entity.Approval;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ApprovalStatusLableDTO {

    private Integer statusNo;
    private LocalDateTime updateLableDate;
    private byte status;
    private Admin admin;
    private Approval approval;

}
