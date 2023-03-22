package com.phonebok.phonebook.service;

import com.phonebok.phonebook.exception.BadRequestException;
import com.phonebok.phonebook.exception.NotFoundException;
import com.phonebok.phonebook.model.BaseResponse;
import com.phonebok.phonebook.model.PhoneBook;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PhoneBookInterface {
    public BaseResponse getPhoneBook(Map<String,String> params) throws NotFoundException;
    public BaseResponse addPhoneBook(Map<String,Object> body) throws BadRequestException;
    public BaseResponse updatePhoneBook(Long id, Map<String,Object> body) throws BadRequestException, NotFoundException;
    public BaseResponse deletePhoneBook(Long id) throws BadRequestException, NotFoundException;

}
