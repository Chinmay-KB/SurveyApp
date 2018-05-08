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
    public static int sup1;
    public static int sup2;
    public static int sup3;
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
                        if(task.getResult().getData().get("data0")!=null)
                        {
                            String d0=task.getResult().getData().get("data0").toString();
                            String k0=task.getResult().getData().get("key0").toString();
                            data[0]=d0;
                            keyOfData[0]=k0;
                        }
                        if(task.getResult().getData().get("data1")!=null)
                        {
                            String d1=task.getResult().getData().get("data1").toString();
                            String k1=task.getResult().getData().get("key1").toString();
                            data[1]=d1;
                            keyOfData[1]=k1;
                        }
                        if(task.getResult().getData().get("data2")!=null)
                        {
                            String d2=task.getResult().getData().get("data2").toString();
                            String k2=task.getResult().getData().get("key2").toString();
                            data[2]=d2;
                            keyOfData[2]=k2;
                        }
                        if(task.getResult().getData().get("data3")!=null)
                        {
                            String d3=task.getResult().getData().get("data3").toString();
                            String k3=task.getResult().getData().get("key3").toString();
                            data[3]=d3;
                            keyOfData[3]=k3;
                        }
                        if(task.getResult().getData().get("data4")!=null)
                        {
                            String d4=task.getResult().getData().get("data4").toString();
                            String k4=task.getResult().getData().get("key4").toString();
                            data[4]=d4;
                            keyOfData[4]=k4;
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
                 EditText paper1=(EditText)findViewById(R.id.main_paper_wastage);
                 EditText paper2=(EditText)findViewById(R.id.extra_paper_wastage);
                 EditText paper3=(EditText)findViewById(R.id.extra_paper_wastage3);
                 EditText paper4=(EditText)findViewById(R.id.extra_paper_wastage4);

                if(!marks.getText().toString() .isEmpty() && !paper1.getText().toString().isEmpty() && !paper2.getText().toString().isEmpty() && !paper3.getText().toString().isEmpty() && !paper4.getText().toString().isEmpty()) {
                    marksValue=Integer.parseInt(marks.getText().toString());
                    mainValue=Integer.parseInt(paper1.getText().toString());
                    sup1=Integer.parseInt(paper2.getText().toString());
                    sup2=Integer.parseInt(paper3.getText().toString());
                    sup3=Integer.parseInt(paper4.getText().toString());
                    user.put("Year", yearValue);
                    user.put("Semester", semesterValue);
                    user.put("marks", marksValue);
                    user.put("Main sheet wasted", mainValue);
                    user.put("Supplementary 1", sup1);
                    user.put("Supplementary 2",sup2);
                    user.put("Supplementary 3",sup3);
                    user.put("Index", index);
                    db.collection(scode)
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("FirestoreDemo", "DocumentSnapshot added with ID " + documentReference.getId());
                                    updateUI(documentReference.getId().toString()); // Pass on values over here
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("FirestoreDemo", "Error adding document", e);
                                }
                            });
                    Toast.makeText(getApplicationContext(), Integer.toString(mainValue), Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Don't leave the fields blank",Toast.LENGTH_SHORT).show();
               // setContentView(R.layout.page_entry);


}

public void savingData(View view)
{
    user.put("Last Index", index);
    user.put("Last Subject Code",scode);
    user.put("Last Semester",semesterValue);
    user.put("Last Year value",yearValue);
    user.put("Index",-1);
    if(data[0]!=null && keyOfData[0]!=null){
    user.put("data0",data[0]);
    user.put("key0",keyOfData[0]);
}
    if(data[1]!=null && keyOfData[1]!=null){
        user.put("data1",data[1]);
        user.put("key1",keyOfData[1]);
    }
    if(data[2]!=null && keyOfData[2]!=null){
        user.put("data2",data[2]);
        user.put("key2",keyOfData[2]);
    }
    if(data[3]!=null && keyOfData[3]!=null){
        user.put("data3",data[3]);
        user.put("key3",keyOfData[3]);
    }
    if(data[4]!=null && keyOfData[4]!=null){
        user.put("data4",data[4]);
        user.put("key4",keyOfData[4]);
    }
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
        data[index]="#"+ Integer.toString(index+1)+" Marks:"+ Integer.toString(marksValue)+" Main "+ Integer.toString(mainValue)+" S1 "+ Integer.toString(sup1)+ " S2 "+ Integer.toString(sup2)+ " S3 "+Integer.toString(sup3);
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
        data[4]="#"+ Integer.toString(index+1)+" Marks:"+ Integer.toString(marksValue)+" Main "+ Integer.toString(mainValue)+" S1 "+ Integer.toString(sup1)+ " S2 "+ Integer.toString(sup2)+ " S3 "+Integer.toString(sup3);
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
public void onBackPressed(View view)
{
    savingData(view);
}
public void goBack(View view)
{
    startActivity(new Intent(this, MainActivity.class));
    finish();
}

}
