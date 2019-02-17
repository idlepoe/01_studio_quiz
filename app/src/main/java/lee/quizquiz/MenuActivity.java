package lee.quizquiz;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.squareup.otto.Bus;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

public class MenuActivity extends AppCompatActivity {

    DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference().child("user");
    DatabaseReference MyInfoRef;

    User myUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        MyInfoRef= UserRef.child(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));


        getMyInfo();
    }

    void getMyInfo(){
        MyInfoRef.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                //bus.post(user);
                setUser(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    void setUser(User user){
        myUser = user;
        Toast.makeText(this,myUser.getName()+" WELCOME!!",Toast.LENGTH_LONG).show();
    }

}
