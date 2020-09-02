package com.moringa.class_schedule_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.models.SessionsModel;

import java.util.List;

public class SessionsListAdapter extends RecyclerView.Adapter<SessionsListAdapter.SessionViewHolder> {

    private List<SessionsModel> sessionsList;
    private Context context;

    public SessionsListAdapter(List<SessionsModel> sessionsList, Context context) {
        this.sessionsList = sessionsList;
        this.context = context;
    }

    @NonNull
    @Override
    public SessionsListAdapter.SessionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.session_list_item, parent, false);
        SessionViewHolder  viewHolder = new SessionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SessionViewHolder holder, int position) {
        holder.bindSessionList(sessionsList.get(position));
    }

    @Override
    public int getItemCount() {
        return sessionsList.size();
    }

    public class SessionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Context context;
        public SessionViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
        public void bindSessionList(SessionsModel sessions){

        }
    }
}
