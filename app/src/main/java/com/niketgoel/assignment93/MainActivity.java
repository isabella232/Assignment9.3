package com.niketgoel.assignment93;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating ArrayList of objects of ContactDetails class
        // to assign the mContactNames and mContactNumbers.
        ArrayList<ContactDetails> contactList = new ArrayList<>();
        contactList.add(new ContactDetails("ABC", "1111111111"));
        contactList.add(new ContactDetails("DEF", "2222222222"));
        contactList.add(new ContactDetails("GHI", "3333333333"));
        contactList.add(new ContactDetails("JKL", "444444444"));
        contactList.add(new ContactDetails("MNO", "5555555555"));

        // Creating ContactDetailsAdapter object to store the ArrayList of ContactDetails objects.
        ContactDetailsAdapter contactDetailsAdapter =
                new ContactDetailsAdapter(this, contactList);

        //Setting custom adapter to the ListView
        listView = (ListView) findViewById(R.id.contact_list);
        listView.setAdapter(contactDetailsAdapter);
        // Register the ListView  for Context menu
        registerForContextMenu(listView);
        // In an actual app, you'd want to request a permission when the user performs an action
        // that requires that permission.


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Handle menu selections

        switch (item.getItemId()) {
            case R.id.call_item:
                if (isPermissionGranted()) {
                    call_action();
                }


                Toast.makeText(MainActivity.this, "Call", Toast.LENGTH_LONG).show();
                return true;
            case R.id.send_sms_item:
                //Getting intent and PendingIntent instance
                shareData();
                Toast.makeText(MainActivity.this, "Send SMS", Toast.LENGTH_LONG).show();

                return true;
            default:
                return false;
        }
    }

    /**
     * to do call on picked list item
     */
    public void call_action() {
        TextView contactText = (TextView) findViewById(R.id.contact_numbers);
        String number = contactText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }
        startActivity(intent);
    }

    /**
     *
     * @return
     */
    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {

                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }
    }

    /**
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    call_action();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }



        }
    }
    //sending messages
    public void shareData() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        TextView contactText = (TextView) findViewById(R.id.contact_numbers);
        String number = contactText.getText().toString();
        sendIntent.setData(Uri.parse("tel:" + number));
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}



