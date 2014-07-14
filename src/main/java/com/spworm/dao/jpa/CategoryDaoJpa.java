package com.spworm.dao.jpa;

import org.springframework.stereotype.Repository;

import com.spworm.dao.CategoryDao;
import com.spworm.model.domain.Category;

@Repository("categoryDao")
public class CategoryDaoJpa extends GenericDaoJpa<Category> implements CategoryDao {

    public CategoryDaoJpa() {
        super(Category.class);
    }
}
