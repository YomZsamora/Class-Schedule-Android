package com.moringa.class_schedule_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringa.class_schedule_app.models.StudentModel;

import java.util.List;

import com.moringa.class_schedule_app.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentContactListAdapter extends RecyclerView.Adapter<StudentContactListAdapter.StudentViewHolder> {

    private List<StudentModel> studentsList;
    Context mContext;

    public StudentContactListAdapter(List<StudentModel> studentsList, Context context) {
        this.studentsList = studentsList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public StudentContactListAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        StudentContactListAdapter.StudentViewHolder  viewHolder = new StudentContactListAdapter.StudentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentContactListAdapter.StudentViewHolder holder, int position) {
        holder.bindStudentList(studentsList.get(position));
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.contactUsername)
        TextView mUsername;

        private String TAG = StudentViewHolder.class.getSimpleName();
        private Context context;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        public void bindStudentList(StudentModel student) {
            mUsername.setText(student.getName());
        }
    }

}
