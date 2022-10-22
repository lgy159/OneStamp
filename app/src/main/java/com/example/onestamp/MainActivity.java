package com.example.onestamp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private ImageButton btn_menu;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_menu = (ImageButton) findViewById(R.id.btn_menu);
        mFirebaseAuth = FirebaseAuth.getInstance();

        btn_menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(),view);
                getMenuInflater().inflate(R.menu.popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.profile_change){ // 비밀번호 변경
                            Intent intent1 = new Intent(MainActivity.this, ProfileActivity.class);
                            startActivity(intent1);
                        }else if (menuItem.getItemId() == R.id.register_store){ // 매장 등록
                            Intent intent2 = new Intent(MainActivity.this, StoreRegisterActivity.class);
                            startActivity(intent2);
                        }else if (menuItem.getItemId() == R.id.accumulate){ // 적립하기
                            Intent intent3 = new Intent(MainActivity.this, AccumulateActivity.class);
                            startActivity(intent3);
                        }else { // 로그아웃
                            mFirebaseAuth.signOut();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            Toast.makeText(MainActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }
}