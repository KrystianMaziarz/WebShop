package pl.com.store.webstore.services.implementations;

import org.springframework.stereotype.Service;
import pl.com.store.webstore.controllers.dtos.CategoryDto;
import pl.com.store.webstore.entities.Category;
import pl.com.store.webstore.repositories.CategoryRepository;
import pl.com.store.webstore.services.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    private CategoryRepository repository;

    public CategoryServiceImp(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long addCategory(CategoryDto customerDto) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Category findById(Long id) throws Exception {
        return null;
    }

    @Override
    public Category updateCategory(Long id, CategoryDto customerDto) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) throws Exception {

    }
}
