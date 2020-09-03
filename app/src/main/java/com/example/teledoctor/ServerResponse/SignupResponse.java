package com.example.teledoctor.ServerResponse;

public class SignupResponse {
    private String status;
    private String token;

    public SignupResponse(String token, String status) {
        this.token = token;
        this.status = status;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
