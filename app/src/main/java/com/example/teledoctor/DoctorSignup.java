package com.example.teledoctor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.teledoctor.API.API;
import com.example.teledoctor.Model.Doctor;
import com.example.teledoctor.ServerResponse.ImageResponse;
import com.example.teledoctor.ServerResponse.SignupResponse;
import com.example.teledoctor.StrictMode.StrictModeClass;
import com.example.teledoctor.URL.Url;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static android.app.Activity.RESULT_OK;


public class DoctorSignup extends AppCompatActivity {
    private EditText etlname, etaddress, etphone, etclinic, etspeciality, etexperience, etemail, etpassword;
    private EditText etfname;
    private ImageView profile;
    Spinner gender;
    private String imagePath;
    private String imageName = "";
    private String Mygender;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_doctor_signup );

        etfname = findViewById( R.id.dfname );
        etlname = findViewById( R.id.dlname );
        etaddress = findViewById( R.id.daddress );
        etphone = findViewById( R.id.dphone );
        etclinic = findViewById( R.id.dclinic );
        etspeciality = findViewById( R.id.dsepeciality );
        etexperience = findViewById( R.id.dexperience );
        etemail = findViewById( R.id.demail );
        etpassword = findViewById( R.id.dpassword );
        gender = findViewById( R.id.spinnergender );
        Mygender= gender.getSelectedItem().toString();
        signup = findViewById( R.id.btnsignup );
        profile = findViewById( R.id.imgprofile );


        profile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        } );

        signup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImageOnly();
                signup();
            }
        } );
    }


    private void BrowseImage(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult( requestCode, resultCode, data );
        if(resultCode==RESULT_OK){
            if(data==null){
                Toast.makeText( this, "please select image", Toast.LENGTH_SHORT ).show();
            }
        }
        Uri uri = data.getData();
        profile.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }


    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this,
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create( MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("myFile",
                file.getName(),requestBody);
        API usersAPI = Url.getInstance().create(API.class);
        Call<ImageResponse> responseCall=usersAPI.uploadImage( body );
        StrictModeClass.StrictMode();
        try {
            Response<ImageResponse> imageResponseResponse = responseCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, "Image inserted" + imageName, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }



    private void signup() {
        String fname = etfname.getText().toString();
        String lname = etlname.getText().toString();
        String address = etaddress.getText().toString();
        String phone = etphone.getText().toString();
        String clinic = etclinic.getText().toString();
        String speciality = etspeciality.getText().toString();
        String experience = etexperience.getText().toString();
        String email = etemail.getText().toString();
        String password = etpassword.getText().toString();



        Doctor doctor = new Doctor( imageName,fname, lname, address, phone, Mygender, clinic, speciality, experience, email, password );
        API doctorAPI = Url.getInstance().create( API.class );
        Call<SignupResponse> responseCall = doctorAPI.registerdoctor( doctor );

        responseCall.enqueue( new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(DoctorSignup.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(DoctorSignup.this, "Registered", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(DoctorSignup.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        } );


}

}
