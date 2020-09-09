package com.example.teledoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class PhoneAuth extends AppCompatActivity {

    private EditText phoneText, codeText;
    private Button btncontinue;
    private Button btnnext;

    private RelativeLayout relativelayout;
    private FirebaseAuth mAuth;
    private String codeSent;
    private PhoneAuthProvider.ForceResendingToken mAuthToken;
    private ProgressDialog loadingbar;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_phone_auth );

        mAuth = FirebaseAuth.getInstance();
        loadingbar = new ProgressDialog( this );
        phoneText = findViewById( R.id.phoneText );
        codeText = findViewById( R.id.codeText );
        btncontinue = findViewById( R.id.continueNextButton );
        btnnext=findViewById( R.id.btnnext );
        relativelayout=findViewById( R.id.phoneAuth );

        btncontinue.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode();
            }
        } );

        btnnext.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerifySentCode();
            }
        } );

    }

private void VerifySentCode(){

        String code= codeText.getText().toString();

      PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
      signInWithPhoneAuthCredential( credential );
}

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent=new Intent( PhoneAuth.this, DoctorSignup.class );
                            startActivity( intent );
                            finish();

                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText( PhoneAuth.this, "Incorrect verification code", Toast.LENGTH_SHORT ).show();
                            }
                        }
                    }
                });
    }


    private void sendVerificationCode(){
        String phone= phoneText.getText().toString();
        if(phone.equals( "" )){
            loadingbar.setTitle( "Phone number verification" );
       loadingbar.setMessage( "Please wait, phone number is being verified" );
       loadingbar.show();

        }
        if(phone.length()< 10){
            loadingbar.setTitle( "Phone number verification" );
            loadingbar.setMessage( "Please enter valid phone number" );
            loadingbar.show();
        }


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks




    }


   PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
       @Override
       public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {


           signInWithPhoneAuthCredential( phoneAuthCredential );
       }

       @Override
       public void onVerificationFailed(FirebaseException e) {


           Toast.makeText( PhoneAuth.this, "write valid phone number", Toast.LENGTH_SHORT ).show();

       }

       @Override
       public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
           super.onCodeSent( s, forceResendingToken );
           codeSent=s;
           mAuthToken=forceResendingToken;


       }
   };

}
