package com.hortonsoft.contractandinvoicebilling.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hortonsoft.contractandinvoicebilling.exception.RecordNotFoundException;
import com.hortonsoft.contractandinvoicebilling.model.ContractEntity;
import com.hortonsoft.contractandinvoicebilling.service.ContractService;
 
@RestController
@RequestMapping("/contracts")
public class ContractController
{
    @Autowired
    ContractService service;
 
    @GetMapping
    public ResponseEntity<List<ContractEntity>> getAllContractss(
                        @RequestParam(defaultValue = "0") Integer pageNo,
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy)
    {
    	List<ContractEntity>  list = service.getAllContracts(pageNo, pageSize, sortBy);
 
        return new ResponseEntity<List<ContractEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<ContractEntity> getContractsById(@PathVariable("id") Long id,
    		                                              @RequestParam(defaultValue = "false") Boolean nonVoidInvoices)
                                                    throws RecordNotFoundException {
    	ContractEntity entity;    	
    	if(nonVoidInvoices == false) {
    		entity = service.getContractById(id);
    	} else {
    		entity = service.getContractByIdContainingNonVoidInvoicesByCreated(id);
    	}
    	
        return new ResponseEntity<ContractEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping(value = "/{id}", params = "remainingBillableValue")
    public ResponseEntity<ContractEntity> getRemainingBillableValueById(@PathVariable("id") Long id,
    		                                                  @RequestParam(defaultValue = "true") Boolean remainingBillableValue)
                                                    throws RecordNotFoundException {
    	ContractEntity entity = service.getRemainingBillableValueById(id);
    	
        return new ResponseEntity<ContractEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContractEntity> createContract(@RequestBody ContractEntity contract)
                                                    throws RecordNotFoundException {
    	ContractEntity updated = service.createContract(contract);
        return new ResponseEntity<ContractEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteContractById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deleteContractById(id);
        return HttpStatus.FORBIDDEN;
    }
 
}