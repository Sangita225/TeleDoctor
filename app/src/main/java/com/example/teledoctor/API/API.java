package com.example.teledoctor.API;

import com.example.teledoctor.Model.Doctor;
import com.example.teledoctor.Model.Patients;
import com.example.teledoctor.Patient;
import com.example.teledoctor.ServerResponse.ImageResponse;
import com.example.teledoctor.ServerResponse.SignupResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface API {

 @Multipart
 @POST("upload")
 Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @POST("doctors/register")
    Call<SignupResponse> registerdoctor(@Body Doctor doctor);

    @POST("patients/register")
    Call<SignupResponse> registerpatient(@Body Patients patient);

}
