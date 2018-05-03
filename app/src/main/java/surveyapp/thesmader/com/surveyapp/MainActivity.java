package surveyapp.thesmader.com.surveyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void dataEntry(View view)
    {
       /* Intent enterData=new Intent(this,entryActivity.class);
        EditText editText=(EditText)findViewById(R.id.subject_code);
        String message=editText.getText().toString();
        Spinner year_select=(Spinner)findViewById(R.id.year_select);
        message+=year_select.getSelectedItem().toString();
        Spinner semester_select=(Spinner)findViewById(R.id.semester_select);
        message+=semester_select.getSelectedItem().toString();
        getIntent().putExtra(EXTRA_MESSAGE ,message);
        startActivity(enterData); */
       startActivity(new Intent(this, entryActivity.class));
    }
}
