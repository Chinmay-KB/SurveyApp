package surveyapp.thesmader.com.surveyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class interimActivity extends AppCompatActivity {

    public String name, email;
    TextView displayName;
    TextView displayMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        displayName = (TextView) findViewById(R.id.textView6);
        displayMail = (TextView) findViewById(R.id.textView7);
        displayName.setText(name);
        displayMail.setText(email);

    }
    public void redirect(View view)
    {
        startActivity(new Intent(this, MainActivity.class));
    }

}
