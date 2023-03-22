package com.phonebok.phonebook.repository;

import com.phonebok.phonebook.model.PhoneBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneBookRepository extends JpaRepository<PhoneBook,Long> {
    List<PhoneBook> findByNameLikeIgnoreCaseAndAddressLikeIgnoreCase(String name, String address);

}
