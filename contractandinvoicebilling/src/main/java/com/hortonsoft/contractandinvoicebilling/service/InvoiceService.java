package com.hortonsoft.contractandinvoicebilling.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
import com.hortonsoft.contractandinvoicebilling.model.InvoiceEntity;
import com.hortonsoft.contractandinvoicebilling.repository.InvoiceRepository;
 
@Service
public class InvoiceService {
     
    @Autowired
    InvoiceRepository repository;    
    // NOTE: The below service only exists within this class
    // as a means of calling the getRemainingBillableValueById()
    // method from the ContractService to be used within the 
    // createInvoice() method within this class. If time were
    // permitting I would make a call at the Controller level
    // as that would decouple the application much more cleanly.
    @Autowired
    ContractService service;
     
    public List<InvoiceEntity> getAllInvoices(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
 
        Page<InvoiceEntity> pagedResult = repository.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<InvoiceEntity>();
        }
    }
     
    public InvoiceEntity getInvoicesById(Long id) throws RecordNotFoundException
    {
        Optional<InvoiceEntity> invoice = repository.findById(id);
         
        if(invoice.isPresent()) {
            return invoice.get();
        } else {
            throw new RecordNotFoundException("No invoice record exist for given id");
        }
    }
     
    public InvoiceEntity createInvoice(InvoiceEntity entity) throws RecordNotFoundException
    {   
    	// Check whether allowing the specified invoice 
    	// value to be subtracted from the remaining value  
    	// will exceed the overall contract value.
    	if(entity.getContractId() >= 1) {
    		BigDecimal remainingValue = service.getRemainingBillableValueById(entity.getContractId()).getRemainingValue();
    		BigDecimal invoiceValue = entity.getInvoiceValue();    		
    		if(remainingValue.compareTo(invoiceValue) != 1) {
    			throw new RecordNotFoundException("The sum of all non-void invoice values on a contract cannot be greater than the contract value");
    		}
    	}
    	long currentTimeMillis = System.currentTimeMillis();
    	entity.setCreated(new Timestamp(currentTimeMillis));
    	entity = repository.save(entity);
    	return entity;
    }
     
    public void deleteInvoiceById(Long id) throws RecordNotFoundException
    {
        Optional<InvoiceEntity> invoice = repository.findById(id);
         
        if(invoice.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No invoice record exist for given id");
        }
    }
}