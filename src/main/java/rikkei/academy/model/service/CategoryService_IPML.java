package rikkei.academy.model.service;

import org.springframework.stereotype.Controller;
import rikkei.academy.model.entity.Category;

import java.util.List;
@Controller
public class CategoryService_IPML implements CategoryService_ITF{
    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Category findById(Integer id) {
        return null;
    }

    @Override
    public Boolean create(Category category) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }

    @Override
    public Boolean update(Category category, Integer id) {
        return null;
    }

    @Override
    public Category logon(Category category) {
        return null;
    }
}
