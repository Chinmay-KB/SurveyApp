package surveyapp.thesmader.com.surveyapp;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import java.util.Arrays;
import java.util.List;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {
    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView subjectCode;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.parent_layout);
            subjectCode = (TextView)itemView.findViewById(R.id.subject_code);
        }
    }

    List<String> feature;

    RVAdapter(interimActivity interimActivity, List<String> feature){
        this.feature = feature;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public PersonViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_list, viewGroup, false);
        final PersonViewHolder pvh = new PersonViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int index=pvh.getAdapterPosition();
                Intent in=new Intent(viewGroup.getContext(),entryActivity.class);
                String item=feature.get(index);
                int a=item.indexOf(" ");
                String scode=item.substring(0,a);
                String year=item.substring(a+1,a+4);
                String sem=item.substring(a+4,item.length());
                in.putExtra("subject",scode);
                in.putExtra("year",year);
                in.putExtra("semester",sem);
                viewGroup.getContext().startActivity(in);

            }
        });
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.subjectCode.setText(feature.get(i));
    }


    @Override
    public int getItemCount() {
        return feature.size();
    }
}