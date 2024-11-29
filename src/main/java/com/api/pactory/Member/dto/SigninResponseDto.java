package com.api.pactory.Member.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class SigninResponseDto {
    @Id
    private Long id;
    private String memberId;
    private String password;
}
