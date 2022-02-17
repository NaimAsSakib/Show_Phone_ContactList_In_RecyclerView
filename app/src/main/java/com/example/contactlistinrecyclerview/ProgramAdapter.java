package com.example.contactlistinrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder> {

    Context context;
    ArrayList<ContactPojo> contactPojos;


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cImage;
        TextView cName;
        TextView cNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cImage = itemView.findViewById(R.id.ivContact);
            cName = itemView.findViewById(R.id.tvName);
            cNumber = itemView.findViewById(R.id.tvNumber);
        }

    }

    public ProgramAdapter(Context context, ArrayList<ContactPojo> contactPojos) {
        this.context = context;
        this.contactPojos = contactPojos;
    }

    @NonNull
    @Override
    public ProgramAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_contact_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramAdapter.ViewHolder holder, int position) {
        ContactPojo item = contactPojos.get(position);

        holder.cName.setText(item.getContactName());
        holder.cNumber.setText(item.getContactNumber());

        holder.cImage.setImageResource(R.drawable.ic_baseline_person_24);

    }

    @Override
    public int getItemCount() {
        return contactPojos.size();
    }


}
