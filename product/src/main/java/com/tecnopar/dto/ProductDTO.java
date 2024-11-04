package com.tecnopar.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@JsonSerialize
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
//    private Long id;

    private String name;

    private String description;

    private  String category;

    private String model;

    private BigDecimal price;

}
