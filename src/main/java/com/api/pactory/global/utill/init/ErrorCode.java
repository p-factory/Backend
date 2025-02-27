package com.api.pactory.global.utill.init;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND(404, "M001", "해당 회원을 찾을 수 없습니다."),
    INVALID_TOKEN(401, "A001", "유효하지 않은 토큰입니다."),
    ACCESS_DENIED(403, "A002", "접근이 거부되었습니다.");

    private final int status;
    private final String code;
    private final String message;
}