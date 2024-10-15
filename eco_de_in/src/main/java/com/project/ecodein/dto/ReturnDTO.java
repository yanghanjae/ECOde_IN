package com.project.ecodein.dto;

import java.sql.Date;
import com.project.ecodein.entity.Buyer;
import com.project.ecodein.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
