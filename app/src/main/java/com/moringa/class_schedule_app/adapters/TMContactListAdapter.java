package com.moringa.class_schedule_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.models.StudentModel;
import com.moringa.class_schedule_app.models.TechnicalMentorModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TMContactListAdapter extends RecyclerView.Adapter<TMContactListAdapter.TMViewHolder> {

    private List<TechnicalMentorModel> tmList;
    Context context;

    public TMContactListAdapter(List<TechnicalMentorModel> tmList, Context context) {
        this.tmList = tmList;
        this.context = context;
    }
    @NonNull
    @Override
    public TMContactListAdapter.TMViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        TMContactListAdapter.TMViewHolder  viewHolder = new TMContactListAdapter.TMViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TMContactListAdapter.TMViewHolder holder, int position) {
        holder.bindTMList(tmList.get(position));
    }

    @Override
    public int getItemCount() {
        return tmList.size();
    }

    public class TMViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.contactUsername)
        TextView mUsername;

        private String TAG = TMContactListAdapter.TMViewHolder.class.getSimpleName();
        private Context context;

        public TMViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        public void bindTMList(TechnicalMentorModel tm) {
            mUsername.setText(tm.getName());
        }

    }
}
