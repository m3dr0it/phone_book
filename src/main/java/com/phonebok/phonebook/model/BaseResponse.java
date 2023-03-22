package com.phonebok.phonebook.model;

import lombok.Data;

@Data
public class BaseResponse {
    String status;
    Object data;
}
