package hila.peri.hoursreportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import hila.peri.hoursreportapp.ui.reports.ReportsFragment;
import hila.peri.hoursreportapp.ui.summary.MonthPickerFragment;

public class RegisterActivity extends AppCompatActivity {


    private TextView Register_TXT_registerHead;
    private EditText Register_TXT_name;
    private EditText Register_TXT_email;
    private EditText Register_TXT_pass;
    private Button Register_BTN_register,Register_BTN_alreadyHaveAcc;
    private FirebaseAuth mAuth;
    MonthPickerFragment m = new MonthPickerFragment();;
    private ProgressDialog mLoadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Register_TXT_registerHead=findViewById(R.id.Register_TXT_registerHead);
        Register_TXT_name=findViewById(R.id.Register_TXT_name);
        Register_TXT_email=findViewById(R.id.Register_TXT_email);
        Register_TXT_pass=findViewById(R.id.Register_TXT_pass);
        Register_BTN_register=findViewById(R.id.Register_BTN_register);
        Register_BTN_alreadyHaveAcc=findViewById(R.id.Register_BTN_alreadyHaveAcc);
        mAuth = FirebaseAuth.getInstance();
        Register_BTN_register.setOnClickListener((v)->{checkCardetials();});
        mLoadingBar=new ProgressDialog(RegisterActivity.this);

        Register_BTN_alreadyHaveAcc.setOnClickListener((v) -> {startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        });
    }

    private void checkCardetials() {
        String username = Register_TXT_name.getText().toString();
        String email = Register_TXT_email.getText().toString();
        String pass = Register_TXT_pass.getText().toString();

        if (username.isEmpty()||username.length()<1){
            showError(Register_TXT_name,"User name is invalid");
        }
        else if(email.isEmpty()||!email.contains("@")){
            showError(Register_TXT_email,"Email is invalid");

        }
        else if(pass.isEmpty()||pass.length()<4){
            showError(Register_TXT_pass,"Your password must have min 5 numbers");

        }
        else {
            mLoadingBar.setTitle("Registrar");
            mLoadingBar.setMessage("Please wait, In the registration process");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();


            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this,task.getException().toString(),Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}