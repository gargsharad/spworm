package com.spworm.dao.jpa;

import org.springframework.stereotype.Repository;

import com.spworm.dao.ExhibitionDao;
import com.spworm.model.domain.Exhibition;

@Repository("exhibitionDao")
public class ExhibitionDaoJpa extends GenericDaoJpa<Exhibition> implements ExhibitionDao {

    public ExhibitionDaoJpa() {
        super(Exhibition.class);
    }
}
