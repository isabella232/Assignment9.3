package com.niketgoel.assignment93;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by niketgoel on 05/11/17.
 */


public class ContactDetailsAdapter extends ArrayAdapter<ContactDetails> {
    TextView numberText;
    TextView nameText;
    public ContactDetailsAdapter(@NonNull Context context, ArrayList<ContactDetails> contactInfo) {
        super(context,0, contactInfo);
    }

    /**
     * Overrided method to inflate the custom layout into ListView
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //To check if the View is being used, otherwise inflate the View.
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_items,parent,false);
        }
        //Getting the ContactDeatails object at this position
        ContactDetails contactDetails = getItem(position);

        //TextView to handle the contact names
        nameText = (TextView)convertView.findViewById(R.id.contact_names);
        nameText.setText(contactDetails.getmContactNames());

        //TextView to handle the contact numbers
        numberText = (TextView)convertView.findViewById(R.id.contact_numbers);
        numberText.setText(contactDetails.getmContactNumbers());

        return convertView;
    }
}
