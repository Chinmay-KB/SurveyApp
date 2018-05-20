package surveyapp.thesmader.com.surveyapp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class interimActivity extends AppCompatActivity {

    FirebaseFirestore db;
    TextView namev,mailv;
    ListView listView;
    String s[];
    List<String> namesList = new ArrayList<>();
    List<String> streams=new ArrayList<>();
    List<String> midendsems=new ArrayList<>();
    RVAdapter adapter;
    private AppBarLayout mAppBarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page_new);
        FirebaseUser users= FirebaseAuth.getInstance().getCurrentUser();
       final TextView namev=(TextView)findViewById(R.id.display_name);
        CollapsingToolbarLayout collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(users.getDisplayName());
        //mailv=(TextView)findViewById(R.id.textView7);
       // namev.setText(users.getDisplayName());
       // mailv.setText(users.getEmail());
        //listView=(ListView)findViewById(R.id.lv);
        db=FirebaseFirestore.getInstance();
        db.collection(users.getEmail())
                .whereGreaterThanOrEqualTo("Data"," ")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            for(DocumentSnapshot doc:task.getResult())
                            {
                               String s=doc.getString("Data");
                                namesList.add(s);
                                String otherdata=doc.getString("Stream")+ " "+doc.getString("Mid or End sem");
                                streams.add(otherdata);

                            }
                        }

                        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_list,R.id.subject_code,namesList);
                        adapter.notifyDataSetChanged();
                      /* listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String item = ((TextView)view).getText().toString();
                                int a=item.indexOf(" ");
                                String scode=item.substring(0,a);
                                String year=item.substring(a+1,a+4);
                                String sem=item.substring(a+4,item.length());
                                Intent i=new Intent(getApplicationContext(), entryActivity.class);
                                i.putExtra("subject",scode);
                                i.putExtra("year",year);
                                i.putExtra("semester",sem);
                                i.putExtra("stream",streams.get(position));
                                i.putExtra("midend",midendsems.get(position));
                                startActivity(i);

                                Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();

                            }
                        }); */
                    }
                });
        RecyclerView recyclerView=findViewById(R.id.lv);
        adapter=new RVAdapter(this,namesList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //adapter.setClickListener(this);



    }


@Override
public void onBackPressed()
{
    finish();
}




    public void redirect(View view)
    {
        MainActivity ob=new MainActivity();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
