package com.phonebok.phonebook.service;

import com.phonebok.phonebook.exception.BadRequestException;
import com.phonebok.phonebook.exception.NotFoundException;
import com.phonebok.phonebook.model.BaseResponse;
import com.phonebok.phonebook.model.PhoneBook;
import com.phonebok.phonebook.repository.PhoneBookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PhoneBookService implements PhoneBookInterface{
    @Autowired
    PhoneBookRepository repository;
    @Autowired
    EntityManager em;
    @Override
    public BaseResponse getPhoneBook(Map<String,String> params) throws NotFoundException {
        BaseResponse response = new BaseResponse();

        if(params.containsKey("id")){
            response.setStatus("success");
            response.setData(getPhoneBookById(Long.valueOf(params.get("id"))));
            return response;
        }

        response.setStatus("success");
        response.setData(findPhoneBook(params));

        return response;
    }

    @Override
    public BaseResponse addPhoneBook(Map<String, Object> body) throws BadRequestException {
        List<String> required = new ArrayList<>();
        required.add("name");
        required.add("phoneNumber");

        for(String require : required){
            if(!body.containsKey(require)){
                String message = String.format("missing required field %s ",require);
                throw new BadRequestException(message);
            }
        }

        String nickName = "";
        String address = "";

        if(body.containsKey("nickname")){
            nickName = body.get("nickname").toString();
        }

        if(body.containsKey("address")){
            address = body.get("address").toString();
        }

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.setName(body.get("name").toString());
        phoneBook.setPhoneNumber(body.get("phoneNumber").toString());
        phoneBook.setNickname(nickName);
        phoneBook.setAddress(address);
        repository.save(phoneBook);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus("created");
        baseResponse.setData(new ArrayList<>());
        return baseResponse;
    }

    @Override
    public BaseResponse updatePhoneBook(Long id, Map<String, Object> body) throws BadRequestException, NotFoundException {
        PhoneBook phoneBook = getPhoneBookById(id);
        if(body.containsKey("nickname")){
            phoneBook.setNickname(body.get("nickname").toString());
        }

        if(body.containsKey("address")){
            phoneBook.setAddress(body.get("address").toString());
        }

        if(body.containsKey("name")){
            phoneBook.setName(body.get("name").toString());
        }

        if(body.containsKey("phoneNumber")){
            phoneBook.setPhoneNumber(body.get("phoneNumber").toString());
        }

        repository.save(phoneBook);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus("updated");
        baseResponse.setData(new ArrayList<>());
        return baseResponse;
    }

    @Override
    public BaseResponse deletePhoneBook(Long id) throws BadRequestException, NotFoundException {
        PhoneBook phoneBook = getPhoneBookById(id);
        repository.delete(phoneBook);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus("deleted");
        baseResponse.setData(new ArrayList<>());
        return baseResponse;
    }

    private List<PhoneBook> findPhoneBook(Map<String,String> params) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PhoneBook> cq = cb.createQuery(PhoneBook.class);
        Root<PhoneBook> book = cq.from(PhoneBook.class);

        if(params.containsKey("name")){
            Predicate predicate = cb.like(book.get("name"), params.get("name"));
            cq.where(predicate);
        }

        if(params.containsKey("address")){
            Predicate predicate = cb.like(book.get("address"),params.get("address"));
            cq.where(predicate);
        }

        if(params.containsKey("phoneNumber")){
            Predicate predicate = cb.equal(book.get("phoneNumber"),params.get("phoneNumber"));
            cq.where(predicate);
        }

        if(params.containsKey("nickname")){
            Predicate predicate = cb.like(book.get("nickname"),params.get("nickname"));
            cq.where(predicate);
        }

        TypedQuery<PhoneBook> query = em.createQuery(cq);
        return query.getResultList();
    }

    private PhoneBook getPhoneBookById(Long id) throws NotFoundException {
        Optional<PhoneBook> phoneBookOpt = repository.findById(id);
        PhoneBook phoneBook;

        if(phoneBookOpt.isEmpty()){
            throw new NotFoundException("Phonebook");
        }
        phoneBook = phoneBookOpt.get();

        return phoneBook;
    }

}
