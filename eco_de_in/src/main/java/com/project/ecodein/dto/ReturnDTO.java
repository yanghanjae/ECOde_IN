package com.project.ecodein.dto;

import com.project.ecodein.entity.Buyer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnDTO {

    private String returnId;
    private Buyer buyer;
    private User user;
    private String returnReason;
    private Date returnRegistDate;

}
