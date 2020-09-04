package com.hortonsoft.contractandinvoicebilling.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hortonsoft.contractandinvoicebilling.model.InvoiceEntity;
 
@Repository
public interface InvoiceRepository
        extends PagingAndSortingRepository<InvoiceEntity, Long> {
 
}