package com.phonebok.phonebook.controller;

import com.phonebok.phonebook.exception.BadRequestException;
import com.phonebok.phonebook.exception.NotFoundException;
import com.phonebok.phonebook.model.BaseResponse;
import com.phonebok.phonebook.service.PhoneBookInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/phone-book")
public class PhoneBookController {

    @Autowired
    PhoneBookInterface phoneBookInterface;

    @GetMapping()
    public ResponseEntity<BaseResponse> getPhoneBook(
            @RequestParam Map<String,String> params
            ) throws NotFoundException {
        BaseResponse response =  phoneBookInterface.getPhoneBook(params);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> udatePhoneBook(
            @PathVariable Long id,
            @RequestBody Map<String,Object> reqBody
    ) throws NotFoundException, BadRequestException {
        BaseResponse response =  phoneBookInterface.updatePhoneBook(id,reqBody);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<BaseResponse> addPhoneBook(
            @RequestBody Map<String,Object> reqBody
    ) throws NotFoundException, BadRequestException {
        BaseResponse response =  phoneBookInterface.addPhoneBook(reqBody);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> deletePhoneBook(
            @PathVariable Long id
    ) throws NotFoundException, BadRequestException {
        BaseResponse response =  phoneBookInterface.deletePhoneBook(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
