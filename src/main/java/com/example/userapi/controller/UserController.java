package com.example.userapi.controller;

import com.example.userapi.dto.XUserDTO;
import com.example.userapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<XUserDTO>  getUser(@PathVariable String username) {
        XUserDTO user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<XUserDTO> registerUser(@RequestBody XUserDTO userDTO) {
        XUserDTO user = userService.registerUser(userDTO);
        return ResponseEntity.ok(user);
    }
}
