package com.mcq.swipescriptbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcq.swipescriptbackend.dto.AppUserDto;
import com.mcq.swipescriptbackend.dto.MemberUpdateDto;
import com.mcq.swipescriptbackend.dto.PaginationMetadata;
import com.mcq.swipescriptbackend.entity.AppUser;
import com.mcq.swipescriptbackend.repository.AppUserRepository;
import com.mcq.swipescriptbackend.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;

    @GetMapping
    public ResponseEntity<List<AppUserDto>> getAllUsers(
            @RequestParam(name = "pageNumber", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "12") int size,
            @RequestParam(defaultValue = "username") String sortBy) throws JsonProcessingException {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<AppUser> userPage = appUserRepository.findAll(pageable);

        List<AppUserDto> userDtos = userPage.getContent().stream()
                .map(appUserService::convertToDto)
                .collect(Collectors.toList());

        // Pagination header
        ObjectMapper objectMapper = new ObjectMapper();
        String paginationJson = objectMapper.writeValueAsString(new PaginationMetadata(
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages()
        ));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Pagination", paginationJson);

        return ResponseEntity.ok().headers(headers).body(userDtos);
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUserDto> getUserByUsername(@PathVariable String username) {
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return ResponseEntity.ok(appUserService.convertToDto(user));
    }

    @PutMapping("/{username}")
    public ResponseEntity<Void> updateUserProfile(@PathVariable String username, @RequestBody MemberUpdateDto memberUpdateDto) {
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        appUserService.updateUserProfile(user, memberUpdateDto);
        return ResponseEntity.noContent().build();
    }
}
