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
public class CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    public List<CustomerDTO> findAll() {
        List<CustomerDTO> customers = new ArrayList<>();
        customerRepository.findAll()
                .stream()
                .forEach(item -> {
                    customers.add(mapToDTO(item));
                });
        return customers;
    }
    public CustomerDTO create(CustomerDTO customer){
        customerRepository.persist(mapToEntity(customer));
        return customer;
    }
    public CustomerDTO update(Long id, CustomerDTO dto) {
        var ent = findById(id);
        if (ent.getId().equals(id)) {
            ent.setAddress(dto.getAddress());
            ent.setAge(dto.getAge());
            ent.setName(dto.getName());
            ent.setPhone(dto.getPhone());
            ent.setEmail(dto.getEmail());
            customerRepository.persist(ent);
            return mapToDTO(ent);
        }
        return dto;
    }
    public void delete(Long id){
        if (findById(id).getId().equals(id)) {
            customerRepository.deleteById(id);
        }
    }
    public CustomerDTO getById(Long id) {
        return  mapToDTO(findById(id));
    }
    ////////////////////////////////////////////////////////////////////////////////////
    // private methods
    /////////////////////////////////////////////////////////////////////////////////////
    private CustomerEntity findById(Long id){
        return (CustomerEntity) customerRepository.findByIdOptional(id)
                .orElseThrow(CustomerNotFoundException::new);
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
