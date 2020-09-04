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
import com.hortonsoft.contractandinvoicebilling.model.InvoiceEntity;
import com.hortonsoft.contractandinvoicebilling.service.InvoiceService;
 
@RestController
@RequestMapping("/invoices")
public class InvoiceController
{
    @Autowired
    InvoiceService service;
 
    @GetMapping
    public ResponseEntity<List<InvoiceEntity>> getAllEmployees(
                        @RequestParam(defaultValue = "0") Integer pageNo,
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy)
    {
        List<InvoiceEntity> list = service.getAllInvoices(pageNo, pageSize, sortBy);
 
        return new ResponseEntity<List<InvoiceEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceEntity> getEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
    	InvoiceEntity entity = service.getInvoicesById(id);
 
        return new ResponseEntity<InvoiceEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InvoiceEntity> createOrUpdateEmployee(@RequestBody InvoiceEntity invoice)
                                                    throws RecordNotFoundException {
    	InvoiceEntity updated = service.createInvoice(invoice);
        return new ResponseEntity<InvoiceEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deleteInvoiceById(id);
        return HttpStatus.FORBIDDEN;
    }
 
}