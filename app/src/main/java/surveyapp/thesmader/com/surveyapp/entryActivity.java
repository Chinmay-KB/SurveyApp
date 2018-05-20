package surveyapp.thesmader.com.surveyapp;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
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
    int xIndex[],xMarks[],xMain[],xs1[],xs2[],xs3[];
    public String[] data;
    public String[] keyOfData;
    private TableLayout table;
    public RadioGroup stream1,stream2;

    String stream,midend;
    FloatingActionButton bs3,bs2;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> user = new HashMap<>();
    private CollectionReference notebookRef;
    EditText s2,s3;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
         requestWindowFeature(Window.FEATURE_NO_TITLE);
         this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        table=(TableLayout)findViewById(R.id.table_layout);
        xMarks=new int[5];
        xMain=new int[5];
        xIndex=new int[5];
        xs1=new int[5];
        xs2=new int[5];
        xs3=new int[5];
        for(int i=0;i<4;i++)
        {
            xs1[i]=0;
            xs2[i]=0;
            xs3[1]=0;
        }
        setContentView(R.layout.page_entry);
         stream1=(RadioGroup)findViewById(R.id.stream1);
         stream2=(RadioGroup)findViewById(R.id.stream2);
         bs3=findViewById(R.id.floatingActionButton6);
         bs2=findViewById(R.id.floatingActionButton5);
        Intent i=getIntent();
        scode=i.getStringExtra("subject");
        yearValue=i.getStringExtra("year");
        semesterValue=i.getStringExtra("semester");
        stream=i.getStringExtra("stream");
        midend=i.getStringExtra("midend");
        TextView subjectDisplay=(TextView) findViewById(R.id.textView10);
        subjectDisplay.setText(scode);

        s2=(EditText)findViewById(R.id.extra_paper_wastage3);
        s3=(EditText)findViewById(R.id.extra_paper_wastage4);
        s2.setVisibility(View.GONE);
        s3.setVisibility(View.GONE);


        RadioButton r1=(RadioButton)findViewById(R.id.btech);
         RadioButton r2=(RadioButton)findViewById(R.id.ma);
         RadioButton r3=(RadioButton)findViewById(R.id.mba);
         RadioButton r4=(RadioButton)findViewById(R.id.barch);
         RadioButton r5=(RadioButton)findViewById(R.id.msc);
         RadioButton r6=(RadioButton)findViewById(R.id.dd);
         RadioButton r7=(RadioButton)findViewById(R.id.imsc);
         RadioButton r8=(RadioButton)findViewById(R.id.mres);
         RadioButton r9=(RadioButton)findViewById(R.id.phd);
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
                        if(task.getResult().getData().get("key0")!=null)
                        {
                            xIndex[0]=Integer.parseInt(task.getResult().getData().get("index0").toString());
                            xMarks[0]=Integer.parseInt(task.getResult().getData().get("marks0").toString());
                            xMain[0]=Integer.parseInt(task.getResult().getData().get("main0").toString());
                            xs1[0]=Integer.parseInt(task.getResult().getData().get("s10").toString());
                            xs2[0]=Integer.parseInt(task.getResult().getData().get("s20").toString());
                            xs3[0]=Integer.parseInt(task.getResult().getData().get("s30").toString());
                            String k0=task.getResult().getData().get("key0").toString();
                            keyOfData[0]=k0;
                        }
                        if(task.getResult().getData().get("key1")!=null)
                        {
                            xIndex[1]=Integer.parseInt(task.getResult().getData().get("index1").toString());
                            xMarks[1]=Integer.parseInt(task.getResult().getData().get("marks1").toString());
                            xMain[1]=Integer.parseInt(task.getResult().getData().get("main1").toString());
                            xs1[1]=Integer.parseInt(task.getResult().getData().get("s11").toString());
                            xs2[1]=Integer.parseInt(task.getResult().getData().get("s21").toString());
                            xs3[1]=Integer.parseInt(task.getResult().getData().get("s31").toString());
                            String k1=task.getResult().getData().get("key1").toString();
                            keyOfData[1]=k1;
                        }
                        if(task.getResult().getData().get("key2")!=null)
                        {
                            xIndex[2]=Integer.parseInt(task.getResult().getData().get("index2").toString());
                            xMarks[2]=Integer.parseInt(task.getResult().getData().get("marks2").toString());
                            xMain[2]=Integer.parseInt(task.getResult().getData().get("main2").toString());
                            xs1[2]=Integer.parseInt(task.getResult().getData().get("s12").toString());
                            xs2[2]=Integer.parseInt(task.getResult().getData().get("s22").toString());
                            xs3[2]=Integer.parseInt(task.getResult().getData().get("s32").toString());
                            String k2=task.getResult().getData().get("key2").toString();
                            keyOfData[2]=k2;
                        }
                        if(task.getResult().getData().get("key3")!=null)
                        {
                            xIndex[3]=Integer.parseInt(task.getResult().getData().get("index3").toString());
                            xMarks[3]=Integer.parseInt(task.getResult().getData().get("marks3").toString());
                            xMain[3]=Integer.parseInt(task.getResult().getData().get("main3").toString());
                            xs1[3]=Integer.parseInt(task.getResult().getData().get("s13").toString());
                            xs2[3]=Integer.parseInt(task.getResult().getData().get("s23").toString());
                            xs3[3]=Integer.parseInt(task.getResult().getData().get("s33").toString());
                            String k3=task.getResult().getData().get("key3").toString();
                            keyOfData[3]=k3;
                        }
                        if(task.getResult().getData().get("key4")!=null)
                        {
                            xIndex[4]=Integer.parseInt(task.getResult().getData().get("index4").toString());
                            xMarks[4]=Integer.parseInt(task.getResult().getData().get("marks4").toString());
                            xMain[4]=Integer.parseInt(task.getResult().getData().get("main4").toString());
                            xs1[4]=Integer.parseInt(task.getResult().getData().get("s14").toString());
                            xs2[4]=Integer.parseInt(task.getResult().getData().get("s24").toString());
                            xs3[4]=Integer.parseInt(task.getResult().getData().get("s34").toString());
                            String k4=task.getResult().getData().get("key4").toString();
                            keyOfData[4]=k4;
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
        uiRef();
    }

public void onClick(View view) {
    EditText marks = (EditText) findViewById(R.id.marks_entry);
    EditText paper1 = (EditText) findViewById(R.id.main_paper_wastage);
    EditText paper2 = (EditText) findViewById(R.id.extra_paper_wastage);
    EditText paper3 = (EditText) findViewById(R.id.extra_paper_wastage3);
    EditText paper4 = (EditText) findViewById(R.id.extra_paper_wastage4);
    String key;
    if (!marks.getText().toString().isEmpty() && !paper1.getText().toString().isEmpty() && !paper2.getText().toString().isEmpty())// &&  && !paper4.getText().toString().isEmpty()) {
    {
        marksValue = Integer.parseInt(marks.getText().toString());
        mainValue = Integer.parseInt(paper1.getText().toString());
        sup1 = Integer.parseInt(paper2.getText().toString());
        if (!paper3.getText().toString().isEmpty())
            sup2 = Integer.parseInt(paper3.getText().toString());
        if (!paper4.getText().toString().isEmpty())
            sup3 = Integer.parseInt(paper4.getText().toString());

        user.put("Year", yearValue);
        user.put("Semester", semesterValue);
        user.put("marks", marksValue);
        user.put("Main sheet wasted", mainValue);
        user.put("Supplementary 1", sup1);
        user.put("Stream", stream);
        user.put("Mid or end sem", midend);
        if (paper3.getText().toString().isEmpty())
         user.put("Supplementary 2", 0);
        else
            user.put("Supplementary 2", sup2);
        if (paper4.getText().toString().isEmpty())
            user.put("Supplementary 3", 0);
        else
            user.put("Supplementary 3", sup3);
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
        marks.setText("");
        paper1.setText((""));
        paper2.setText("");
        paper3.setText("");
        paper4.setText("");
    } else
        Toast.makeText(getApplicationContext(), "Don't leave the fields blank", Toast.LENGTH_SHORT).show();
    // setContentView(R.layout.page_entry);
    s2.setVisibility(View.GONE);
    s3.setVisibility(View.GONE);
    bs2.setVisibility(View.VISIBLE);
    bs3.setVisibility(View.VISIBLE);

}
    public void streamChoice1(View view)
    {

       int selectedId=stream1.getCheckedRadioButtonId();
       Button rb1=(Button)findViewById(selectedId);
       stream=rb1.getText().toString();
        if(stream2.getCheckedRadioButtonId()!=-1)
       stream2.clearCheck();
    }
    public void streamChoice2(View view)
    {
        int selectedId=stream2.getCheckedRadioButtonId();
        Button rb2=(Button)findViewById(selectedId);
        stream=rb2.getText().toString();
        if(stream1.getCheckedRadioButtonId()!=-1)
       stream1.clearCheck();
    }

public void savingData(View view)
{
    user.put("Last Index", index);
    user.put("Last Subject Code",scode);
    user.put("Last Semester",semesterValue);
    user.put("Last Year value",yearValue);
    user.put("Index",-1);
    if(keyOfData[0]!=null){
    user.put("index0",xIndex[0]);
    user.put("marks0",xMarks[0]);
    user.put("main0",xMain[0]);
    user.put("s10",xs1[0]);
    user.put("s20",xs2[0]);
    user.put("s30",xs3[0]);
    user.put("key0",keyOfData[0]);
}
    if(keyOfData[1]!=null){
        user.put("index1",xIndex[1]);
        user.put("marks1",xMarks[1]);
        user.put("main1",xMain[1]);
        user.put("s11",xs1[1]);
        user.put("s21",xs2[1]);
        user.put("s31",xs3[1]);
        user.put("key1",keyOfData[1]);
    }
    if(keyOfData[2]!=null){
        user.put("index2",xIndex[2]);
        user.put("marks2",xMarks[2]);
        user.put("main2",xMain[2]);
        user.put("s12",xs1[2]);
        user.put("s22",xs2[2]);
        user.put("s32",xs3[2]);
        user.put("key2",keyOfData[2]);
    }
    if(keyOfData[3]!=null){

        user.put("index3",xIndex[3]);
        user.put("marks3",xMarks[3]);
        user.put("main3",xMain[3]);
        user.put("s13",xs1[3]);
        user.put("s23",xs2[3]);
        user.put("s33",xs3[3]);        user.put("key3",keyOfData[3]);
    }
    if(keyOfData[4]!=null){
        user.put("index4",xIndex[4]);
        user.put("marks4",xMarks[4]);
        user.put("main4",xMain[4]);
        user.put("s14",xs1[4]);
        user.put("s24",xs2[4]);
        user.put("s34",xs3[4]);
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
       // data[index]="#"+ Integer.toString(index+1)+" Marks:"+ Integer.toString(marksValue)+" Main "+ Integer.toString(mainValue)+" S1 "+ Integer.toString(sup1)+ " S2 "+ Integer.toString(sup2)+ " S3 "+Integer.toString(sup3);
        keyOfData[index]=key;
        xIndex[index]=index+1;
        xMarks[index]=marksValue;
        xMain[index]=mainValue;
        xs1[index]=sup1;
        xs2[index]=sup2;
        xs3[index]=sup3;
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
        for(int i=0;i<4;i++)
        {
            xIndex[i]=xIndex[i+1];
            xMarks[i]=xMarks[i+1];
            xMain[i]=xMain[i+1];
            xs1[i]=xs1[i+1];
            xs2[i]=xs2[i+1];
            xs3[i]=xs3[i+1];
        }

        keyOfData[4]=key;
        //data[4]="#"+ Integer.toString(index+1)+" Marks:"+ Integer.toString(marksValue)+" Main "+ Integer.toString(mainValue)+" S1 "+ Integer.toString(sup1)+ " S2 "+ Integer.toString(sup2)+ " S3 "+Integer.toString(sup3);
        xIndex[4]=index+1;
        xMarks[4]=marksValue;
        xMain[4]=mainValue;
        xs1[4]=sup1;
        xs2[4]=sup2;
        xs3[4]=sup3;



    }
    index++;
    uiRef();
}

public void uiRef()
{
    TableLayout stk = (TableLayout) findViewById(R.id.table_layout);
    stk.removeAllViews();
    Typeface typeface = ResourcesCompat.getFont(this, R.font.q);
    stk.setColumnStretchable(6,true);
    TableRow tbrow0 = new TableRow(this);
    TextView tv0 = new TextView(this);
    tv0.setTypeface(typeface);
    tv0.setText(" Sl.No ");
    tv0.setTextSize(20);
    tv0.setTextColor(Color.BLACK);
    tbrow0.addView(tv0);
    TextView tv1 = new TextView(this);
    tv1.setText(" Marks     ");
    tv1.setTypeface(typeface);
    tv1.setTextSize(20);
    tv1.setTextColor(Color.BLACK);
    tbrow0.addView(tv1);
    TextView tv2 = new TextView(this);
    tv2.setText("Main sheet  ");
    tv2.setTextSize(20);
    tv2.setTypeface(typeface);
    tv2.setTextColor(Color.BLACK);
    tbrow0.addView(tv2);
    TextView tv3 = new TextView(this);
    tv3.setText("  S1   ");
    tv3.setTextColor(Color.BLACK);
    tv3.setTextSize(20);
    tv3.setTypeface(typeface);
    tbrow0.addView(tv3);
    TextView tv4 = new TextView(this);
    tv4.setTextSize(20);
    tv4.setText("  S2   ");
    tv4.setTypeface(typeface);
    tv4.setTextColor(Color.BLACK);
    tbrow0.addView(tv4);
    TextView tv5 = new TextView(this);
    tv5.setText("  S3   ");
    tv5.setTextSize(20);
    tv5.setTypeface(typeface);
    tv5.setTextColor(Color.BLACK);
    tbrow0.addView(tv5);
    stk.addView(tbrow0);
    int limit;
    if(index>4)
        limit=5;
    else
        limit=index;
    for (int i = 0; i <limit; i++) {
        TableRow tbrow = new TableRow(this);
        TextView t1v = new TextView(this);

        t1v.setText(Integer.toString(xIndex[i]));
        t1v.setTextColor(Color.BLACK);
        t1v.setTextSize(20);
        t1v.setTypeface(typeface);
        t1v.setGravity(Gravity.CENTER);
        tbrow.addView(t1v);
        TextView t2v = new TextView(this);
        t2v.setText(Integer.toString(xMarks[i]));
        t2v.setTextColor(Color.BLACK);
        t2v.setTypeface(typeface);
        t2v.setTextSize(20);
        t2v.setGravity(Gravity.CENTER);
        tbrow.addView(t2v);
        TextView t3v = new TextView(this);
        t3v.setText(Integer.toString(xMain[i]));
        t3v.setTextColor(Color.BLACK);
        t3v.setTypeface(typeface);
        t3v.setTextSize(20);
        t3v.setGravity(Gravity.CENTER);
        tbrow.addView(t3v);
        TextView t4v = new TextView(this);
        t4v.setText(Integer.toString(xs1[i]));
        t4v.setTextColor(Color.BLACK);
        t4v.setTypeface(typeface);
        t4v.setTextSize(20);
        t4v.setGravity(Gravity.CENTER);
        tbrow.addView(t4v);
        TextView t5v= new TextView(this);
        t5v.setText(Integer.toString(xs2[i]));
        t5v.setTextColor(Color.BLACK);
        t5v.setTypeface(typeface);
        t5v.setTextSize(20);
        t5v.setGravity(Gravity.CENTER);
        tbrow.addView(t5v);
        TextView t6v = new TextView(this);
        t6v.setText(Integer.toString(xs3[i]));
        t6v.setTextColor(Color.BLACK);
        t6v.setTypeface(typeface);
        t6v.setGravity(Gravity.CENTER);
        t6v.setTextSize(20);
        tbrow.addView(t6v);
        stk.addView(tbrow);
    }
}
public void pageAdd2(View view)
    {

        bs3.setVisibility(View.GONE);
        s3.setVisibility(View.VISIBLE);
    }
public void pageAdd1(View view)
{

    bs2.setVisibility(View.GONE);
    s2.setVisibility(View.VISIBLE);
}
public void onBackPressed(View view)
{
    savingData(view);
}
public void goBack(View view)
{
    startActivity(new Intent(this, interimActivity.class));
    finish();
}

}
