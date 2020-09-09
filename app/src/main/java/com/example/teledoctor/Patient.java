package com.example.teledoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.teledoctor.API.API;
import com.example.teledoctor.Model.Patients;
import com.example.teledoctor.ServerResponse.SignupResponse;
import com.example.teledoctor.URL.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Patient extends AppCompatActivity {

    private EditText etfname, etlname, etaddress, etphone, etage, etemail, etpassword;
    private CheckBox checkterms;
    private Button btnsignup;
    Spinner gender;
    private String mygender;
    private Boolean checked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_patient );

        etfname=findViewById( R.id.etpfname );
        etlname=findViewById( R.id.etpplname );
        etaddress=findViewById( R.id.etpaddress );
//        etphone=findViewById( R.id.etphone );
        etage=findViewById( R.id.etpage );
        etemail=findViewById( R.id.etpemail );
        etpassword=findViewById( R.id.etpatientpassword );
        gender=findViewById( R.id.spinnergender );
        mygender=gender.getSelectedItem().toString();
        checkterms=findViewById( R.id.checkBox );
        checked=checkterms.isChecked();
        btnsignup=findViewById( R.id.btnpsignup );

        btnsignup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        } );



    }
    private  void signup(){

        String fname=etfname.getText().toString();
        String lname= etlname.getText().toString();
        String address= etaddress.getText().toString();
        String age=etage.getText().toString();
//        String phone= etphone.getText().toString();
        String email= etemail.getText().toString();
        String password=etpassword.getText().toString();
        Patients patients=new Patients( fname,lname,address,age,mygender,email,password );

        API patientAPI = Url.getInstance().create( API.class );
        Call<SignupResponse> responseCall = patientAPI.registerpatient( patients );

        responseCall.enqueue( new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(Patient.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(Patient.this, "Registered", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(Patient.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        } );

    }

}
