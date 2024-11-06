package com.tecnopar.service;

import com.tecnopar.dto.ProductDTO;
import com.tecnopar.entity.ProductEntity;
import com.tecnopar.exception.ProductNotFoundException;
import com.tecnopar.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.lang.Long;

@ApplicationScoped
public class ProductService {
    @Inject
    ProductRepository productRepository;

    public List<ProductDTO> FindAll(){
        List<ProductDTO> products = new ArrayList<>();
        productRepository.findAll()
                .stream()
                .forEach(
                        item -> {
                            products.add(mapToDTO(item));
                        });
        return products;
    }
    public  ProductDTO create(ProductDTO dto){
        productRepository.persist(mapToEntity(dto));
        return dto;
    }
    public ProductDTO update(Long id,ProductDTO dto){
        ProductEntity product = findById(id);

        if( product.getId().equals(id) ) {
            product.setModel(dto.getModel());
            product.setDescription(dto.getDescription());
            product.setName(dto.getName());
            product.setPrice(dto.getPrice());
            product.setCategory(dto.getCategory());
            productRepository.persist(product);
        }
        return mapToDTO(product);
    }
    public void delete(Long id) {
        if (findById(id).getId().equals(id)) {
            productRepository.deleteById(id);
        }
    }
    public ProductDTO getById(Long id){
        return mapToDTO(findById(id));
    }
////////////////////////////////////////////////////////////////////////////////////
// private methods
////////////////////////////////////////////////////////////////////////////////////
    private ProductEntity findById(Long id) {
        return (ProductEntity) productRepository.findByIdOptional(id)
                .orElseThrow(ProductNotFoundException::new);
    }
    private ProductDTO mapToDTO(ProductEntity entity) {
            ProductDTO dto = new ProductDTO();
            dto.setCategory(entity.getCategory());
            dto.setName(entity.getName());
            dto.setModel(entity.getModel());
            dto.setPrice(entity.getPrice());
            dto.setDescription(entity.getDescription());
            return dto;
    }
    private ProductEntity mapToEntity(ProductDTO dto){
            ProductEntity ent = new ProductEntity();

            ent.setCategory(dto.getCategory());
            ent.setName(dto.getName());
            ent.setPrice(dto.getPrice());
            ent.setModel(dto.getModel());
            ent.setDescription(dto.getDescription());
            return  ent;
    }
}
