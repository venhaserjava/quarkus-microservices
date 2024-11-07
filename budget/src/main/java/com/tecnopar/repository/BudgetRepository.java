package com.tecnopar.repository;

import com.tecnopar.entity.BudgetEntity;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class BudgetRepository implements PanacheRepository<BudgetEntity> {

}
