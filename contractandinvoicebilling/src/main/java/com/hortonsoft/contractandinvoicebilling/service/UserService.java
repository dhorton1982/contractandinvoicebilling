package com.hortonsoft.contractandinvoicebilling.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hortonsoft.contractandinvoicebilling.exception.RecordNotFoundException;
import com.hortonsoft.contractandinvoicebilling.model.UserEntity;
import com.hortonsoft.contractandinvoicebilling.repository.UserRepository;
 
@Service
public class UserService {
     
    @Autowired
    UserRepository repository;
     
    public List<UserEntity> getAllUsers(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
 
        Page<UserEntity> pagedResult = repository.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<UserEntity>();
        }
    }
     
    public UserEntity getUserById(Long id) throws RecordNotFoundException
    {
        Optional<UserEntity> contract = repository.findById(id);
         
        if(contract.isPresent()) {
            return contract.get();
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }
     
    public UserEntity createUser(UserEntity entity) throws RecordNotFoundException
    {
            entity = repository.save(entity);            
            return entity;
    }
     
    public void deleteUserById(Long id) throws RecordNotFoundException
    {
        Optional<UserEntity> contract = repository.findById(id);
         
        if(contract.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No user  record exist for given id");
        }
    }
}