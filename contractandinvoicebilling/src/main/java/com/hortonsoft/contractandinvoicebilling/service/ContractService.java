package com.hortonsoft.contractandinvoicebilling.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hortonsoft.contractandinvoicebilling.exception.RecordNotFoundException;
import com.hortonsoft.contractandinvoicebilling.model.ContractEntity;
import com.hortonsoft.contractandinvoicebilling.model.InvoiceEntity;
import com.hortonsoft.contractandinvoicebilling.repository.ContractRepository;
 
@Service
public class ContractService {
     
    @Autowired
    ContractRepository repository;
     
    public List<ContractEntity> getAllContracts(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
 
        Page<ContractEntity> pagedResult = repository.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<ContractEntity>();
        }
    }
     
    public ContractEntity getContractById(Long id) throws RecordNotFoundException
    {
        Optional<ContractEntity> contract = repository.findById(id);
         
        if(contract.isPresent()) {
            return contract.get();
        } else {
            throw new RecordNotFoundException("No contract record exist for given id");
        }
    }
    
    public ContractEntity getRemainingBillableValueById(Long id) throws RecordNotFoundException
    {
        Optional<ContractEntity> contract = repository.findById(id);
         
        if(contract.isPresent()) {
        	BigDecimal contractValue = contract.get().getContractValue();
        	// Filter out the void invoices (i.e. where VoidValue == true).
        	List<InvoiceEntity> invoices =  contract.get().getInvoices().stream()
        			                                .filter(invoiceEntity -> invoiceEntity.getVoidValue() == false)
        			                                .collect(Collectors.toList());       	
        	// Add up all of the non void invoice
        	// values for the specified contract.
        	BigDecimal totalInvoiceValue = invoices.stream()
        	        .map(invoice -> invoice.getInvoiceValue())
        	        .reduce(new BigDecimal(0.0), (invoiceValue1, invoiceValue2) -> invoiceValue1.add(invoiceValue2));
        	// Subtract the contract value from the total
        	// invoice value to get the remaining value.
        	BigDecimal remainingValue = contractValue.subtract(totalInvoiceValue);
        	contract.get().setRemainingValue(remainingValue);
        	
            return contract.get();
        } else {
            throw new RecordNotFoundException("No contract record exist for given id");
        }
    }
    
    public ContractEntity getContractByIdContainingNonVoidInvoicesByCreated(Long id) throws RecordNotFoundException
    {
        Optional<ContractEntity> contract = repository.findById(id);
         
        if(contract.isPresent() && contract.get().getInvoices() != null) {
        	// Filter out the void invoices (i.e. where VoidValue == true).
        	List<InvoiceEntity> invoiceEntities =  contract.get().getInvoices().stream()
        			                                                            .filter(invoiceEntity -> invoiceEntity.getVoidValue() == false)
        			                                                            .collect(Collectors.toList());
        	// Sort the invoices by created/submitted.
        	invoiceEntities.sort((invoiceEntity1, invoiceEntity2) -> invoiceEntity1.getCreated().compareTo(invoiceEntity2.getCreated()));
        	contract.get().setInvoices(invoiceEntities);
            return contract.get();
        } else {
            throw new RecordNotFoundException("No contract record exist for given id");
        }
    }
     
    public ContractEntity createContract(ContractEntity entity) throws RecordNotFoundException
    {
            entity = repository.save(entity);            
            return entity;
    }
     
    public void deleteContractById(Long id) throws RecordNotFoundException
    {
        Optional<ContractEntity> contract = repository.findById(id);
         
        if(contract.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No contract record exist for given id");
        }
    }
}