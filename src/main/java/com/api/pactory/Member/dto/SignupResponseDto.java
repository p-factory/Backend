package com.api.pactory.Member.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
public class SignupResponseDto {
    @Id
    private Long id;
    private String memberId;
    private String password;
    private LocalDateTime createdAt;
}
