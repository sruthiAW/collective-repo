package com.example.mydbapplication;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ssurendran on 10/13/2015.
 */
public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {

    List<Contact> contactList;

    public RecyclerContactAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_contact_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mContactDetails.setText(contactList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void updateList(List<Contact> contactList) {
        this.contactList = contactList;
        this.notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.contact_details)
        TextView mContactDetails;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
