package pl.com.store.webstore.services;

import pl.com.store.webstore.controllers.dtos.CategoryDto;
import pl.com.store.webstore.entities.Category;

import java.util.List;

public interface CategoryService {

    Long addCategory(CategoryDto customerDto);

    List<Category> findAll();

    Category findById(Long id) throws Exception;

    Category updateCategory(Long id, CategoryDto customerDto) throws Exception;

    void deleteById(Long id) throws Exception;
}
