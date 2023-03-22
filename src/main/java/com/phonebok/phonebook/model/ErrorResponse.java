package com.phonebok.phonebook.model;

import lombok.Data;

@Data
public class ErrorResponse {
    String status;
    String message;
}
