package surveyapp.thesmader.com.surveyapp;

<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
=======
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
>>>>>>> 9e22a725a7e21f16bb6c7da34b2f48e364ec9228
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.android.gms.tasks.OnSuccessListener;
=======
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
>>>>>>> 9e22a725a7e21f16bb6c7da34b2f48e364ec9228
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chinmay on 02-05-2018.
 */

public class entryActivity extends AppCompatActivity implements View.OnClickListener{
<<<<<<< HEAD
   public static String scode;
    public static String semesterValue;
    public static String yearValue;
    public static int marksValue;
    public static int mainValue;
    public int index;
    public TextView serialNo;
    public TextView marksNo;
    public TextView wasteNo;
    public String[] data;
    public String[] keyOfData;
    public TextView tv;
    public TextView tv1;
    public TextView tv2;
    public TextView tv3;
    public TextView tv4;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> user = new HashMap<>();
    private CollectionReference notebookRef;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_entry);
        tv=(TextView)findViewById(R.id.textView);
        tv1=(TextView)findViewById(R.id.textView2);
        tv2=(TextView)findViewById(R.id.textView3);
        tv3=(TextView)findViewById(R.id.textView4);
        tv4=(TextView)findViewById(R.id.textView5);
        data=new String[5];
        keyOfData=new String[5];
        notebookRef=db.collection(scode);
        index=0;
        DocumentReference docRef = db.collection(scode).document("Last Accessed");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                    if (document != null) {
                        Log.d("", "DocumentSnapshot data: " + task.getResult().getData());
                        String interimID =task.getResult().getData().get("Last Index").toString();
                        if(interimID!=null) {
                            index = Integer.parseInt(interimID);
                        }
                    } else {
                        Log.d("FirestoreDemo", "No such document");
                    }
                } else {
                        Log.d("FirestoreDemo", "get failed with ", task.getException());
                    }
                }
            }
    });
=======
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
>>>>>>> 9e22a725a7e21f16bb6c7da34b2f48e364ec9228
    }

public void onClick(View view)
{
<<<<<<< HEAD
                 EditText marks=(EditText)findViewById(R.id.marks_entry);
                 EditText paper1=(EditText)findViewById(R.id.main_paper_wastage);

                  EditText paper2=(EditText)findViewById(R.id.extra_paper_wastage);
                marksValue=Integer.parseInt(marks.getText().toString());
                mainValue=Integer.parseInt(paper1.getText().toString()) + Integer.parseInt(paper2.getText().toString());
                user.put("Year",yearValue);
                user.put("Semester",semesterValue);
                user.put("marks",marksValue);
                user.put("Wasted paper",mainValue);
                user.put("Index",index);
                db.collection(scode)
=======

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
>>>>>>> 9e22a725a7e21f16bb6c7da34b2f48e364ec9228
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("FirestoreDemo", "DocumentSnapshot added with ID "+ documentReference.getId());
<<<<<<< HEAD
                                updateUI(documentReference.getId().toString()); // Pass on values over here
=======
>>>>>>> 9e22a725a7e21f16bb6c7da34b2f48e364ec9228
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("FirestoreDemo", "Error adding document", e);
                            }
                        });
<<<<<<< HEAD
                Toast.makeText(getApplicationContext(),Integer.toString(mainValue), Toast.LENGTH_SHORT).show();
               // setContentView(R.layout.page_entry);


}

public void savingData(View view)
{
    user.put("Last Index", index);
    user.put("Last Subject Code",scode);
    user.put("Last Semester",semesterValue);
    user.put("Last Year value",yearValue);
    user.put("Index",-1);

    db.collection(scode)
            .document("Last Accessed")//Stores the credentials of the last worked upon tab
            .set(user)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d("FirestoreDemo","Document snapshot added!");

                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("FirestoreDemo","Error adding document",e);
                }
            });

    goBack(view);
}
public void updateUI(String key)
{
    if(index<5)
    {
        data[index]="Marks:"+ Integer.toString(marksValue)+"Waste paper"+ Integer.toString(mainValue);
        keyOfData[index]=key;
    }
    else{
        data[0]=data[1];
        data[1]=data[2];
        data[2]=data[3];
        data[3]=data[4];
        keyOfData[0]=keyOfData[1];
        keyOfData[1]=keyOfData[2];
        keyOfData[2]=keyOfData[3];
        keyOfData[3]=keyOfData[4];
        keyOfData[4]=key;
        data[4]="Marks:"+ Integer.toString(marksValue)+"Waste paper"+ Integer.toString(mainValue);
    }
    if(data[0]!=null)
        tv.setText(data[0]);
    if(data[1]!=null)
        tv1.setText(data[1]);
    if(data[2]!=null)
        tv2.setText(data[2]);
    if(data[3]!=null)
        tv3.setText(data[3]);
    if(data[4]!=null)
        tv4.setText(data[4]);
    index++;
}

=======
                slno=Integer.toString(Integer.parseInt(slno)+1);
                Toast.makeText(getApplicationContext(),markNote+ " " + s, Toast.LENGTH_SHORT).show();
                setContentView(R.layout.page_entry);
                recreate();
          /*  }
            return false;
        }
    }); */

}
>>>>>>> 9e22a725a7e21f16bb6c7da34b2f48e364ec9228
public void goBack(View view)
{
    startActivity(new Intent(this, MainActivity.class));
    finish();
}

}
