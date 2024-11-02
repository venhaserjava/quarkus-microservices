package com.tecnopar.service;

import com.tecnopar.dto.CustomerDTO;
import com.tecnopar.entity.CustomerEntity;
import com.tecnopar.exception.CustomerNotFoundException;
import com.tecnopar.repository.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.NewCookie;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CustomerService  {

    @Inject
    private CustomerRepository customerRepository;

    public List<CustomerDTO> findAll(){
        List<CustomerDTO> customers = new ArrayList<>();
        customerRepository.findAll()
                .stream()
                .forEach(item ->{
                    customers.add(mapToDTO(item));
                });
        return customers;
    }
    private CustomerDTO mapToDTO(CustomerEntity ent){
        CustomerDTO dto = new CustomerDTO();
        dto.setAddress(ent.getAddress());
        dto.setAge(ent.getAge());
        dto.setName(ent.getName());
        dto.setEmail(ent.getEmail());
        dto.setPhone(ent.getPhone());
        return dto;
    }
    private CustomerEntity mapToEntity(CustomerDTO dto){
        CustomerEntity ents = new CustomerEntity();
        ents.setAddress(dto.getAddress());
        ents.setAge(dto.getAge());
        ents.setName(dto.getName());
        ents.setEmail(dto.getEmail());
        ents.setPhone(dto.getPhone());
        return  ents;
    }

}
