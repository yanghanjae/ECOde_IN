package com.project.ecodein.dto;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderPoolDTO {

    private int orderNo;
    private int buyer_code;
    private String user_id;
    private Date due_date;
    private List<Integer> order_nos;
    private List<Integer> quantities;
}
