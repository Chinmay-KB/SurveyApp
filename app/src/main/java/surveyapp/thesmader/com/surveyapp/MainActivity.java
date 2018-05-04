package surveyapp.thesmader.com.surveyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    public String subjectCode;
    public String yearValue;
    public String semesterValue;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
    }


    public void dataEntry(View view)
    {
       entryActivity x=new entryActivity();

        EditText editText=(EditText)findViewById(R.id.subject_code);
        String subjectCode=editText.getText().toString();
        Spinner year_select=(Spinner)findViewById(R.id.year_select);
        yearValue=year_select.getSelectedItem().toString();
        Spinner semester_select=(Spinner)findViewById(R.id.semester_select);
        semesterValue=semester_select.getSelectedItem().toString();
        x.scode=subjectCode;
        x.semesterValue=semesterValue;
        x.yearValue=yearValue;
        if(yearValue.equals("Choose Year") || semesterValue.equals("Choose Semester"))
            Toast.makeText(getApplicationContext(),"Please provide appropriate input", Toast.LENGTH_SHORT).show();
        else
       startActivity(new Intent(this, entryActivity.class));
    }
}
