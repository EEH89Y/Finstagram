package hu.mobalk.finstagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private static final String LOG_TAG = SignUpActivity.class.getName();
    private static final String PREF_KEY = MainActivity.class.getPackage().toString();


    EditText userNameEditText;
    EditText userEmailEditText;
    EditText passwordEditText;
    EditText passwordAgainEditText;

    private SharedPreferences preferences;
    private FirebaseAuth mAuth;
    private NotificationHelper mNotificationHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //No title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        userNameEditText = findViewById(R.id.userNameET);
        userEmailEditText = findViewById(R.id.userEmailET);
        passwordEditText = findViewById(R.id.passwordET);
        passwordAgainEditText = findViewById(R.id.passwordAgainET);

        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        String userName = preferences.getString("userName", "");
        String password = preferences.getString("password", "");

        userNameEditText.setText(userName);
        passwordEditText.setText(password);



        Log.i(LOG_TAG,"OnCreate");

    }

    public void signUp(View view) {

        String userNameStr = userNameEditText.getText().toString();
        String emailStr = userEmailEditText.getText().toString();
        String passwordStr= passwordEditText.getText().toString();
        String passwordAgainStr = passwordAgainEditText.getText().toString();

        if (!passwordStr.equals(passwordAgainStr)){
            return;
        }

        mAuth.createUserWithEmailAndPassword(emailStr,passwordStr).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(LOG_TAG, "User created succesfully!");
                    mNotificationHelper = new NotificationHelper(SignUpActivity.this);
                    mNotificationHelper.send("Woho! Now log in to take pictures!");
                    startMain();
                }else {
                    Log.d(LOG_TAG, "User was't created successfully:", task.getException());
                    Toast.makeText(SignUpActivity.this, "User was't created successfully:", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

//    public void startFeed(){
//        Intent intent = new Intent(this,FeedActivity.class);
//        startActivity(intent);
//    }
    public void startMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }



    public void cancel(View view) {
        finish();
    }
}