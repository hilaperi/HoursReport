package hila.peri.hoursreportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import hila.peri.hoursreportapp.ui.home.HomeApproveActivity;

public class LoginActivity extends AppCompatActivity {
    private TextView Login_TXT_LoginHead;
    private EditText Login_TXT_email;
    private EditText Login_TXT_pass;
    private Button Login_BTN_login,Login_BTN_register;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;
    public static String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login_TXT_LoginHead=findViewById(R.id.Login_TXT_LoginHead);
        Login_TXT_email=findViewById(R.id.Login_TXT_email);
        Login_TXT_pass=findViewById(R.id.Login_TXT_pass);
        Login_BTN_login=findViewById(R.id.Login_BTN_login);
        Login_BTN_register=findViewById(R.id.Login_BTN_register);
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(LoginActivity.this);

        Login_BTN_login.setOnClickListener((v)->{checkCardetials();});

        Login_BTN_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);            }
        });

    }

    private void checkCardetials() {
        email = Login_TXT_email.getText().toString();
        String pass = Login_TXT_pass.getText().toString();


        if(email.isEmpty()||!email.contains("@")){
            showError(Login_TXT_email,"email is invalid");

        }
        else if(pass.isEmpty()||pass.length()<4){
            showError(Login_TXT_pass,"your pass must to have min 5 caracter");

        }
        else {
            mLoadingBar.setTitle("Login");
            mLoadingBar.setMessage("wait");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    else {
                        mLoadingBar.dismiss();
                        Toast.makeText(LoginActivity.this,"Try again",Toast.LENGTH_SHORT).show();
                    }


                }
            });

        }
    }
    public static String getMyString(){
        return email; }
//        SharedPreferences login = getSharedPreferences("LOGIN", 0);
//        SharedPreferences.Editor editor = login.edit();
//         editor.putString("user", Login_TXT_email.getText().toString());
//         editor.commit();

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }

}