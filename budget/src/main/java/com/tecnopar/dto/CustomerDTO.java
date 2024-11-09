package com.tecnopar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private String name;

    private  String phone;

    private String email;

    private String address;

    private  Long age;

}
