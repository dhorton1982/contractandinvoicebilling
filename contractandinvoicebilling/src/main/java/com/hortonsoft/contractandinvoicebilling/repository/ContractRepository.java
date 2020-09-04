package com.hortonsoft.contractandinvoicebilling.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hortonsoft.contractandinvoicebilling.model.ContractEntity;
 
@Repository
public interface ContractRepository
        extends PagingAndSortingRepository<ContractEntity, Long> {
 
}