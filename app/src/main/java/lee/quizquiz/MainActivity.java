package lee.quizquiz;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("user");
    DatabaseReference userListRef = FirebaseDatabase.getInstance().getReference().child("userList");

    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uid = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        try{
            getUserCheck();
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        }catch (Exception e){
            Log.d("DB ERROR",e.getMessage());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    void getUserCheck(){
        userListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!uid.equalsIgnoreCase(dataSnapshot.getValue()+"")){
                    userRef.child(uid).setValue(new User(uid,0));
                    userListRef.child(uid).setValue(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
