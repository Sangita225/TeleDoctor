package com.example.teledoctor.API;

import com.example.teledoctor.Model.Doctor;
import com.example.teledoctor.ServerResponse.SignupResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {

    @POST("doctor/register")
    Call<SignupResponse> registerowner(@Body Doctor doctor);


}
