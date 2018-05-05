package surveyapp.thesmader.com.surveyapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
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
    public TextView nestedTextView;
    public TextView serialNo;
    public TextView marksNo;
    public TextView wasteNo;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> user = new HashMap<>();
    private CollectionReference notebookRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_entry);
        nestedTextView=(TextView)findViewById(R.id.text_view_data);
       notebookRef=db.collection(scode);
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
                            loadNotes();
                        }//dataLabel.setText(userName);
                    } else {
                        Log.d("FirestoreDemo", "No such document");
                    }
                } else {
                        Log.d("FirestoreDemo", "get failed with ", task.getException());
                    }
                }
            }
    });
        //loadNotes();
    }

public void onClick(View view)
{
                 EditText marks=(EditText)findViewById(R.id.marks_entry);
                 EditText paper1=(EditText)findViewById(R.id.main_paper_wastage);

                  EditText paper2=(EditText)findViewById(R.id.extra_paper_wastage);
                marksValue=Integer.parseInt(marks.getText().toString());
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
                Note note=new Note(marksValue,mainValue,0,index-1);
                notebookRef.add(note);
               // loadNotes();


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
    public void loadNotes() {
        notebookRef.whereGreaterThanOrEqualTo("Index", 0)
                .orderBy("Index", Query.Direction.DESCENDING)
                .limit(5)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = " ";

                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Note note = documentSnapshot.toObject(Note.class);
                            note.setDocumentId(documentSnapshot.getId());

                            String documentId = note.getDocumentId();
                            int index=note.getIndex();
                            int marks = note.getMarks();
                            int mainSheet= note.getMainSheet();
                            int suppSheet = note.getSuppSheet();

                            data += "ID: " + documentId
                                    + "\nIndex: " + index + "\n Marks" +marks
                                    + "\nPaper wasted" +mainSheet+suppSheet + "\n\n";
                        }

                        nestedTextView.setText("Trial");
                    }
                });
       // marks.setText("");
       // paper1.setText("");
       // paper2.setText("");
    }

public void goBack(View view)
{
    startActivity(new Intent(this, MainActivity.class));
    finish();
}

}
