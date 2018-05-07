package surveyapp.thesmader.com.surveyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
<<<<<<< HEAD
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
=======
>>>>>>> 9e22a725a7e21f16bb6c7da34b2f48e364ec9228

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
<<<<<<< HEAD
    public String subjectCode;
    public String yearValue;
    public String semesterValue;
    private FirebaseAuth mAuth;
=======

>>>>>>> 9e22a725a7e21f16bb6c7da34b2f48e364ec9228
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        mAuth = FirebaseAuth.getInstance();
    }


=======
    }
>>>>>>> 9e22a725a7e21f16bb6c7da34b2f48e364ec9228
    public void dataEntry(View view)
    {
       entryActivity x=new entryActivity();

        EditText editText=(EditText)findViewById(R.id.subject_code);
<<<<<<< HEAD
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
=======
        String message=editText.getText().toString();
        Spinner year_select=(Spinner)findViewById(R.id.year_select);
        message+=year_select.getSelectedItem().toString();
        Spinner semester_select=(Spinner)findViewById(R.id.semester_select);
        message+=semester_select.getSelectedItem().toString();
        x.scode=message;
>>>>>>> 9e22a725a7e21f16bb6c7da34b2f48e364ec9228
       startActivity(new Intent(this, entryActivity.class));
    }
}
