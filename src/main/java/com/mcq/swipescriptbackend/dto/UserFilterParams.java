package com.mcq.swipescriptbackend.dto;

import lombok.Data;

@Data
public class UserFilterParams {
    private String gender;
    private String currentUsername;
    private int pageNumber = 1; // Default to the first page
    private int pageSize = 12;  // Default page size
    private String sortBy = "username"; // Default sort field
}
