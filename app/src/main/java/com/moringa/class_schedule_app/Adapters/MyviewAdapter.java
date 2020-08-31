package com.moringa.class_schedule_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.fireModel.Fmodel;

import java.util.ArrayList;

public class MyviewAdapter extends RecyclerView.Adapter<MyviewAdapter.MyviewHolder> implements Filterable {
    Context context;
    ArrayList<Fmodel> profile;
    ArrayList<Fmodel> fullprofile;
    public MyviewAdapter(Context c, ArrayList<Fmodel> p){
        this.context=c;
        this.profile=p;
        this.fullprofile=new ArrayList<>(p);
    }
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyviewHolder(LayoutInflater.from(context).inflate(R.layout.single_view_layout,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        holder.name.setText(profile.get(position).getName());
        holder.age.setText(profile.get(position).getCohort());
        holder.email.setText(profile.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return profile.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder{

        TextView name,email,age;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.rec_name);
            email=itemView.findViewById(R.id.rec_email);
            age=itemView.findViewById(R.id.rec_cohort);

        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter =new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Fmodel> filteredlist=new ArrayList<>();
            if(constraint == null || constraint.length()==0){
                filteredlist.addAll(fullprofile);
            }else{
                String search=constraint.toString().toLowerCase().trim();
                for (Fmodel p :fullprofile){
                    if(p.getName().toLowerCase().contains(search) || p.getEmail().toLowerCase().contains(search) || p.getCohort().toLowerCase().contains(search)){
                        filteredlist.add(p);
                    }
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredlist;
            return  filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            profile.clear();
            profile.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };
}
