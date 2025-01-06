package com.mcq.swipescriptbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginationMetadata {
    private int currentPage;
    private int itemsPerPage;
    private long totalItems;
    private int totalPages;
}
