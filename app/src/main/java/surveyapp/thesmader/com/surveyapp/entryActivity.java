package surveyapp.thesmader.com.surveyapp;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chinmay on 02-05-2018.
 */

public class entryActivity extends AppCompatActivity implements View.OnClickListener{
   // EditText max = (EditText) findViewById(R.id.extra_paper_wastage);
    public static String scode;
    public String slno;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> user = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_entry);
        slno="0";
    }

public void onClick(View view)
{

   /* max.setOnEditorActionListener(new TextView.OnEditorActionListener() {
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
               // Log.i(TAG,"Enter pressed"); */
                EditText marks=(EditText)findViewById(R.id.marks_entry);
                String markNote=marks.getText().toString();
                EditText paper1=(EditText)findViewById(R.id.main_paper_wastage);
                EditText paper2=(EditText)findViewById(R.id.extra_paper_wastage);
                String wastage=paper1.getText().toString() + "+";
                String s = wastage+ paper2.getText().toString();
                user.put("marks",markNote);
                db.collection(scode)
                       // .document(slno)
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("FirestoreDemo", "DocumentSnapshot added with ID "+ documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("FirestoreDemo", "Error adding document", e);
                            }
                        });
                slno=Integer.toString(Integer.parseInt(slno)+1);
                Toast.makeText(getApplicationContext(),markNote+ " " + s, Toast.LENGTH_SHORT).show();
                setContentView(R.layout.page_entry);
                recreate();
          /*  }
            return false;
        }
    }); */

}
public void goBack(View view)
{
    startActivity(new Intent(this, MainActivity.class));
    finish();
}

}
