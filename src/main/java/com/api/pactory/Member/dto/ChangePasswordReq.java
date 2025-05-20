package com.api.pactory.Member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangePasswordReq {
    private String currentPassword;
    private String newPassword;
    private String newPasswordConfirm;
}
