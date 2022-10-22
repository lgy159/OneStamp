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

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;     // 파이어 베이스 인증
    private TextView mProfile_email, mEmailView, mPassView, mPhoneView;
    private EditText mProfile_pass, mProfile_phone;
    private DatabaseReference mDatabaseRef;
    private Button mBtn_change, mBtn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mProfile_email = (TextView) findViewById(R.id.profile_email);
        mProfile_pass = findViewById(R.id.profile_pass);
        mEmailView = findViewById(R.id.phoneView);
        mPassView = findViewById(R.id.passView);
        mBtn_change = findViewById(R.id.btn_ok);
        mBtn_cancel = findViewById(R.id.btn_cancel);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("oneStamp");
        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserAccount account = snapshot.getValue(UserAccount.class);
                mProfile_email.setText(account.getEmailId());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mBtn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "클릭", Toast.LENGTH_SHORT).show();
                mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).child("password").setValue(mProfile_pass.getText().toString());
            }
        });
    }
}