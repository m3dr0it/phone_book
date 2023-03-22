package com.phonebok.phonebook.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PhoneBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    String name;

    @Column(name = "nickname")
    String nickname;

    @Column(name = "address")
    String address;

    @Column(name = "phone_number")
    String phoneNumber;

}
