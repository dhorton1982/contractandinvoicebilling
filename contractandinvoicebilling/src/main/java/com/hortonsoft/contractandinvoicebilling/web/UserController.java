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
import com.hortonsoft.contractandinvoicebilling.model.UserEntity;
import com.hortonsoft.contractandinvoicebilling.service.UserService;
 
@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    UserService service;
 
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers(
                        @RequestParam(defaultValue = "0") Integer pageNo,
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy)
    {
    	List<UserEntity>  list = service.getAllUsers(pageNo, pageSize, sortBy);
 
        return new ResponseEntity<List<UserEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUsersById(@PathVariable("id") Long id)
    		                                       throws RecordNotFoundException {
    	UserEntity entity = service.getUserById(id);

    	return new ResponseEntity<UserEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> createContract(@RequestBody UserEntity user)
                                                    throws RecordNotFoundException {
    	UserEntity updated = service.createUser(user);
        return new ResponseEntity<UserEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteUserById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deleteUserById(id);
        return HttpStatus.FORBIDDEN;
    }
 
}