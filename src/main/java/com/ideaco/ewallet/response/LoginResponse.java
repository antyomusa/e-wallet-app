package com.ideaco.ewallet.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginResponse extends BaseResponse {
    private UserSession data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserSession{
        private Integer id;
        private String username;
        private String email;
        private String phoneNumber;
    }
}
