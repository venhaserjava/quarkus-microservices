package com.tecnopar.service;

import java.util.List;
import java.util.ArrayList;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.tecnopar.dto.BudgetDTO;
import com.tecnopar.entity.BudgetEntity;
import com.tecnopar.repository.BudgetRepository;

@ApplicationScoped
public class BudgetService {

    @Inject
    BudgetRepository budgetRepository;
    
   
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
        budgetRepository.persist(mapToEntity(dto));        
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
        dto.setProdutId(ent.getProdutId());
        dto.setBudgetValue(ent.getBudgetValue());
        return dto;
    }
    private BudgetEntity mapToEntity(BudgetDTO dto) {
        BudgetEntity ent = new BudgetEntity();
        ent.setBudgetValue(dto.getBudgetValue());
        ent.setCustomerId(dto.getCustomerId());
        ent.setCustomerName(dto.getCustomerName());
        ent.setProdutId(dto.getProdutId());
        return ent;
    }
    
}
