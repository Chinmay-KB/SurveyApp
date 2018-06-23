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
    List<String> year=new ArrayList<>();
    List<String> semester=new ArrayList<>();
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
        db=FirebaseFirestore.getInstance();
        db.collection(users.getEmail())
                .whereGreaterThanOrEqualTo("Subject Code"," ")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            for(DocumentSnapshot doc:task.getResult())
                            {
                               String s=doc.getString("Subject Code");
                                namesList.add(s);
                                midendsems.add(doc.getString("Mid or End sem"));
                                streams.add(doc.getString("Stream"));
                                year.add(doc.getString("Year"));
                                semester.add(doc.getString("Semester"));

                            }
                        }

                         adapter.notifyDataSetChanged();

                    }
                });
        RecyclerView recyclerView=findViewById(R.id.lv);
        adapter=new RVAdapter(this,namesList,streams,midendsems,year,semester);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
