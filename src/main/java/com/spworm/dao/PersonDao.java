package com.spworm.dao;

import com.spworm.exception.AuthenticationException;
import com.spworm.exception.EntityNotFoundException;
import com.spworm.model.domain.Person;

public interface PersonDao extends GenericDao<Person> {

    public Person getPersonByUsername(String username) throws EntityNotFoundException;

    public Person authenticatePerson(String username, String password) throws AuthenticationException;

}
