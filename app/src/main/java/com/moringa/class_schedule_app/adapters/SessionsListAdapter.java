package com.moringa.class_schedule_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.models.ModuleModel;
import com.moringa.class_schedule_app.models.SessionsModel;
import com.moringa.class_schedule_app.services.ClassScheduleApi;
import com.moringa.class_schedule_app.services.ClassScheduleClient;
import com.moringa.class_schedule_app.ui.SessionDetailsActivity;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        SessionsListAdapter.SessionViewHolder  viewHolder = new SessionsListAdapter.SessionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SessionsListAdapter.SessionViewHolder holder, int position) {
        holder.bindSessionList(sessionsList.get(position));
    }

    @Override
    public int getItemCount() {
        return sessionsList.size();
    }

    public class SessionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.sessionName)
        TextView mSessionName;
        @BindView(R.id.sessionModule)
        TextView mSessionModule;
        @BindView(R.id.sessionStartTime)
        TextView mSessionStartTime;
        @BindView(R.id.sessionEndTime)
        TextView mSessionEndTime;
        @BindView(R.id.sessionCardItem)
        CardView mSessionCard;

        private String TAG = SessionViewHolder.class.getSimpleName();
        private Context context;
        private ModuleModel module;

        public SessionViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view == mSessionCard) {
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(context, SessionDetailsActivity.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("sessions", Parcels.wrap(sessionsList));
                Log.d(TAG, "card view clicked");
                context.startActivity(intent);
            }
        }

        //get the data and bind it the views
        public void bindSessionList(SessionsModel sessions){
            mSessionName.setText(sessions.getSessionName());
            //call our method and pass it the id we get from sessions
            //yet to figure out these methods :(, otherwise the data is retrieved fine
//            getModuleById(sessions.getModuleId());
//            mSessionModule.setText(module.getName());
            // we initialize a SimpleDateFormat format so as to format the timestamp we get from api
            SimpleDateFormat sdf = new SimpleDateFormat("hh.mm aa"); //we define the pattern which returns Hour:Minute
            mSessionStartTime.setText(sdf.format(sessions.getStartTime()));
            mSessionEndTime.setText(sdf.format(sessions.getEndTime()));
        }

        //this method allows us to get the individual method and return it
        private ModuleModel getModuleById(int id){
            ClassScheduleApi client = ClassScheduleClient.getClient();
            Call<ModuleModel> call = client.getModuleById(id);
            call.enqueue(new Callback<ModuleModel>() {
                @Override
                public void onResponse(Call<ModuleModel> call, Response<ModuleModel> response) {
                    if(response.isSuccessful()) {
                        module = response.body();
                    } else {
                        Log.d(TAG, "Something went wrong");
                    }
                }

                @Override
                public void onFailure(Call<ModuleModel> call, Throwable t) {
                    Log.d(TAG, "on failure", t);
                }
            });
            return module;
        }
    }
}
