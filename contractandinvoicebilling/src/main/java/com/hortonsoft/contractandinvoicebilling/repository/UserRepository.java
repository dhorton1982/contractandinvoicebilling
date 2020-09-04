package com.hortonsoft.contractandinvoicebilling.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hortonsoft.contractandinvoicebilling.model.UserEntity;
 
@Repository
public interface UserRepository
        extends PagingAndSortingRepository<UserEntity, Long> {
 
}