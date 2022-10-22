package com.example.onestamp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StoreRegisterActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;     // 파이어 베이스 인증
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스
    private EditText mStore_name, mStore_address, mStore_number;      // 회원가입 입력필드
    private Button mBtn_store_Register;            // 회원가입 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("oneStamp");

        mStore_name = findViewById(R.id.store_name);
        mStore_address = findViewById(R.id.store_address);
        mStore_number = findViewById(R.id.store_number);
        mBtn_store_Register = findViewById(R.id.btn_store_register);

        mBtn_store_Register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                StoreAccount account = new StoreAccount();
                account.setStore_address(mStore_address.getText().toString());
                account.setStore_name(mStore_name.getText().toString());
                account.setStore_number(mStore_number.getText().toString());

                mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).child("StoreAccount").setValue(account);
                finish();
            }
        });
    }
}