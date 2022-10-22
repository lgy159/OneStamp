package com.example.onestamp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AccumulateActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;     // 파이어 베이스 인증
    private TextView mPhoneView;
    private EditText mPhoneEdit;
    private DatabaseReference mDatabaseRef;
    private Button mBtn_ok, mBtn_cancel, mBtn_first;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accumulate);

        mPhoneView = findViewById(R.id.phoneView);
        mPhoneEdit = findViewById(R.id.phoneEdit);
        mBtn_ok = findViewById(R.id.btn_ok);
        mBtn_cancel = findViewById(R.id.btn_cancel);
        mBtn_first = findViewById(R.id.btn_first);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("oneStamp").child("UserAccount");
        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();


        // 현재 계정의 매장 정보 address 저장
        mDatabaseRef.child(firebaseUser.getUid()).child("StoreAccount").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StoreAccount account = snapshot.getValue(StoreAccount.class);
                address = account.getStore_address();
                Toast.makeText(AccumulateActivity.this, address, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        mBtn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 입력 받은 핸드폰 번호 search 후 1번만 event
                mDatabaseRef.orderByChild("phone").equalTo(mPhoneEdit.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            UserAccount account = snapshot.getValue(UserAccount.class);

                            // 검색 결과의 address (매장)에 stamp 적립
                            mDatabaseRef.child(account.getIdToken()).child(address).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Toast.makeText(AccumulateActivity.this, "inner", Toast.LENGTH_SHORT).show();
                                    int value = dataSnapshot.getValue(int.class);
                                    mDatabaseRef.child(account.getIdToken()).child(address).setValue(value + 1);
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Toast.makeText(AccumulateActivity.this, "error", Toast.LENGTH_SHORT).show();
                                }
                            });
                            Toast.makeText(AccumulateActivity.this, "적립이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });

        mBtn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseRef.orderByChild("phone").equalTo(mPhoneEdit.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            UserAccount account = snapshot.getValue(UserAccount.class);
                            mDatabaseRef.child(account.getIdToken()).child(address).setValue(0);
                            Toast.makeText(AccumulateActivity.this, "적립이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

            }
        });
    }
}