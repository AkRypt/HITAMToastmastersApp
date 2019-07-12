package com.example.hitam_toastmasters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GrammarianAdapter extends BaseAdapter {

    private TextView spkrName, remarks;
    private ImageView wod, iod;

    public Context context;
    public List<GramSingleItem> singleMemberItem;


    public GrammarianAdapter(@NonNull Context context, int resource, @NonNull List<GramSingleItem> objects) {
        super();
        this.context = context;
        this.singleMemberItem = objects;
    }


    @Override
    public int getCount() { return singleMemberItem.size(); }
    @Override
    public Object getItem(int i) { return singleMemberItem.get(i); }
    @Override
    public long getItemId(int i) { return i; }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        GramSingleItem currentMemberItem = (GramSingleItem) getItem(position);

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.gram_results, parent, false);
        }

        spkrName = listItemView.findViewById(R.id.gramResultSpeakerName);
        spkrName.setText(currentMemberItem.getSpeakerName());

        wod = listItemView.findViewById(R.id.gramResultWod);
        if (currentMemberItem.getWod().equals("yes")) {
            wod.setVisibility(View.VISIBLE);
        } else if (currentMemberItem.getWod().equals("no")) {
            wod.setVisibility(View.INVISIBLE);
        }

        iod = listItemView.findViewById(R.id.gramResultIod);
        if (currentMemberItem.getIod().equals("yes")) {
            iod.setVisibility(View.VISIBLE);
        } else if (currentMemberItem.getIod().equals("no")) {
            iod.setVisibility(View.INVISIBLE);
        }

        remarks = listItemView.findViewById(R.id.gramResultRemark);
        remarks.setText("Remarks:\n"+currentMemberItem.getRemarks());

        return listItemView;
    }

}
