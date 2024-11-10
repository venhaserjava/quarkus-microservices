package com.tecnopar.service;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import com.tecnopar.client.CustomerClient;
import com.tecnopar.client.ProductClient;
import com.tecnopar.dto.BudgetDTO;
import com.tecnopar.dto.CustomerDTO;
import com.tecnopar.entity.BudgetEntity;
import com.tecnopar.repository.BudgetRepository;

@ApplicationScoped
public class BudgetService {

    @Inject
    private BudgetRepository budgetRepository;

    @Inject
    @RestClient
    private CustomerClient customerClient;

    @Inject
    @RestClient
    private ProductClient productClient;
   
    public List<BudgetDTO> findAll(){   
        List<BudgetDTO> budgets = new ArrayList<>();
        budgetRepository.findAll()
            .stream()
            .forEach(item -> {
              budgets.add(mapToDTO(item));   
            });
        return budgets;   
    }
    
    public BudgetDTO getById(Long id) { 
        return mapToDTO(findById(id));
    }

    public void create(BudgetDTO dto){

        CustomerDTO customerDTO = customerClient.getCustomerById(dto.getCustomerId());
        if (customerDTO.getName().equals(dto.getCustomerName()) 
        && productClient.getProductById(dto.getProductId())!= null
        ){
            budgetRepository.persist(mapToEntity(dto));            
        } else {
            throw new NotFoundException();
        }                    
    }

    public BudgetDTO update(Long id,BudgetDTO dto) {
        BudgetEntity budget = findById(id);
        if (budget.getId().equals(id)) {
            budgetRepository.persist(budget);
        }
        return mapToDTO(budget);            
    }
    public void delete(Long id) {
        if (findById(id).getId().equals(id)) {
            budgetRepository.deleteById(id);            
        }
    }
////////////////////////////////////////////////////////////////////////////////////
// private methods
////////////////////////////////////////////////////////////////////////////////////

    private BudgetEntity findById(Long id) {
        return (BudgetEntity) budgetRepository.findById(id);
    }

    private BudgetDTO mapToDTO(BudgetEntity ent) {
        BudgetDTO dto = new BudgetDTO();

        dto.setCustomerId(ent.getCustomerId());
        dto.setCustomerName(ent.getCustomerName());
        dto.setProductId(ent.getProductId());
        dto.setBudgetValue(ent.getBudgetValue());
        return dto;
    }
    private BudgetEntity mapToEntity(BudgetDTO dto) {
        BudgetEntity ent = new BudgetEntity();
        ent.setBudgetValue(dto.getBudgetValue());
        ent.setCustomerId(dto.getCustomerId());
        ent.setCustomerName(dto.getCustomerName());
        ent.setProductId(dto.getProductId());
        return ent;
    }
    
}
