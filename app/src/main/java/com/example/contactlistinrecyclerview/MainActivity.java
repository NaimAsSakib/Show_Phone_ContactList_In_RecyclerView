package com.example.contactlistinrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter programAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ContactPojo> contactPojoArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.rcvContactList);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        contactPojoArrayList=new ArrayList<>();

        programAdapter=new ProgramAdapter(this,contactPojoArrayList);
        recyclerView.setAdapter(programAdapter);

        //For User permission to read contacts
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_CONTACTS)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        if(response.getPermissionName().equals(Manifest.permission.READ_CONTACTS)){

                            getContacts(); //method for showing contacts after getting permission from user
                        }
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                        Toast.makeText(MainActivity.this,"Permission must be granted",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                        token.continuePermissionRequest();
                    }
                }).check();

    }

    private void getContacts() {
        Cursor cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,null,null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        while (cursor.moveToNext()){
            @SuppressLint("Range") String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            @SuppressLint("Range") String phoneNumber=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            @SuppressLint("Range") String phoneUri=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));

            //These (82-85) codes are for only showing contacts in RCV
           /* ContactPojo contact=new ContactPojo(phoneUri,name,phoneNumber);
            contactPojoArrayList.add(contact);
            programAdapter.notifyDataSetChanged();*/


            //these (89-107) codes are for eliminating duplicate contacts with same name and number
            int flag = 0;
            if(contactPojoArrayList.size() == 0){
                contactPojoArrayList.add(new ContactPojo(phoneUri,name, phoneNumber));
            }
            for(int i=0;i<contactPojoArrayList.size();i++){

                if(!contactPojoArrayList.get(i).getContactName().trim().equals(name) || !contactPojoArrayList.get(i).getContactNumber().trim().equals(phoneNumber)){
                    flag = 1;

                }else{
                    flag =0;
                    break;
                }

            }
            if(flag == 1){
                contactPojoArrayList.add(new ContactPojo(phoneUri,name, phoneNumber));
            }
            programAdapter.notifyDataSetChanged();
        }
    }

}