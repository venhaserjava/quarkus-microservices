package com.tecnopar.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BudgetDTO {


    private Long customerId;

    private String customerName;
    
    private Long produtId;

    private BigDecimal budgetValue;


}
