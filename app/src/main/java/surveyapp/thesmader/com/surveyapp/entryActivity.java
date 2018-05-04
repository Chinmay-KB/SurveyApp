package surveyapp.thesmader.com.surveyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chinmay on 02-05-2018.
 */

public class entryActivity extends AppCompatActivity implements View.OnClickListener{
   public static String scode;
    public static String semesterValue;
    public static String yearValue;
    public static int marksValue;
    public static int mainValue;
    public int index;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> user = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_entry);
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
                        if(interimID!=null)
                            index=Integer.parseInt(interimID);
                        //dataLabel.setText(userName);
                    } else {
                        Log.d("FirestoreDemo", "No such document");
                    }
                } else {
                        Log.d("FirestoreDemo", "get failed with ", task.getException());
                    }
                }
            }
    });
    }

public void onClick(View view)
{
                EditText marks=(EditText)findViewById(R.id.marks_entry);
                marksValue=Integer.parseInt(marks.getText().toString());
                EditText paper1=(EditText)findViewById(R.id.main_paper_wastage);
                EditText paper2=(EditText)findViewById(R.id.extra_paper_wastage);
                mainValue=Integer.parseInt(paper1.getText().toString()) + Integer.parseInt(paper2.getText().toString());
                user.put("Year",yearValue);
                user.put("Semester",semesterValue);
                user.put("marks",marksValue);
                user.put("Wasted paper",mainValue);
                user.put("Index",index++);
                db.collection(scode)
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
                Toast.makeText(getApplicationContext(),Integer.toString(mainValue), Toast.LENGTH_SHORT).show();
                setContentView(R.layout.page_entry);


}
public void updateUI() {
        if(index<5)
    for (int i = 0; i <=index; i++) {
        CollectionReference peopleRef=db.collection(scode);
                peopleRef.whereEqualTo("Year", yearValue)
                .whereEqualTo("Semester", semesterValue)
                .whereEqualTo("Index", i)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Log.d("Yes", document.getId() + " => " + document.getData());// After this document.getString("Field name"), then link to respective TextView, done :D
                            }
                        } else {
                            Log.d("No", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
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
}
public void goBack(View view)
{
    startActivity(new Intent(this, MainActivity.class));
    finish();
}

}
