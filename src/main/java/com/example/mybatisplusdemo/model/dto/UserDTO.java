package com.example.mybatisplusdemo.model.dto;

import com.example.mybatisplusdemo.model.domain.User;
import lombok.Data;

@Data
public class UserDTO extends User {
    private String name;
}
