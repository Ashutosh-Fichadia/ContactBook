package com.example.contactbook;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.provider.ContactsContract;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {
    ListView lv;
    String name = "";
    List<String> data;
    ArrayAdapter<String> adapter;
    String num = "";
    Dialog dialog;
    String nm="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        lv = findViewById(R.id.lv);
        data = new ArrayList<>();
        adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.clr, data);
        lv.setAdapter(adapter);
        getContacts();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String contactclick = data.get(i);
                String[] lines = contactclick.split("\\r?\\n");
                nm = lines[0];
                num = lines[1];
                dialog = new Dialog(ScrollingActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog);
                dialog.show();

                TextView t2 = (TextView) dialog.findViewById(R.id.title);
                t2.setText(t2.getText() + " " + nm + " ?");
                ImageButton ib = (ImageButton) dialog.findViewById(R.id.callimg);
                TextView t1 = (TextView) dialog.findViewById(R.id.callbtn);

                ib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String call = "tel:" + num;

                        try {
                            Intent is = new Intent(Intent.ACTION_CALL, Uri.parse(call));
                            if (ActivityCompat.checkSelfPermission(ScrollingActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                                return;
                            }

                            startActivity(is);

                        }
                        catch(Exception e)
                        {
                            Toast.makeText(getApplicationContext(),"Error" + e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
                t1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String call = "tel:" + num;

                        try {
                            Intent is = new Intent(Intent.ACTION_CALL, Uri.parse(call));
                            if (ActivityCompat.checkSelfPermission(ScrollingActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                                return;
                            }

                            startActivity(is);

                        }
                        catch(Exception e)
                        {
                            Toast.makeText(getApplicationContext(),"Error" + e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                });
              /*  AlertDialog.Builder mBuilder = new AlertDialog.Builder(ScrollingActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog,null);

                ImageButton i1 = (ImageButton)  findViewById(R.id.callimg);
                ImageButton i2 = (ImageButton) findViewById(R.id.msgimg);
                TextView t1 =(TextView) findViewById(R.id.callbtn);
                TextView t2 = (TextView) findViewById(R.id.msgbtn);
                if(i1 == null)
                {
                    Toast.makeText(ScrollingActivity.this,"NULL " + num,Toast.LENGTH_SHORT).show();
                }
                else{
                    i1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(!num.equals(""))
                            {
                                Toast.makeText(ScrollingActivity.this,"Number : " + num ,Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(ScrollingActivity.this,"No Number Found!",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                if(i2==null)
                {
                    Toast.makeText(ScrollingActivity.this,"NULL " + num,Toast.LENGTH_SHORT).show();
                }
                else{
                    i2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(!num.equals(""))
                            {
                                Toast.makeText(ScrollingActivity.this,"Number : " + num,Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(ScrollingActivity.this,"No Number Found!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                if(t1==null)
                {
                    Toast.makeText(ScrollingActivity.this,"NULL " + num,Toast.LENGTH_SHORT).show();
                }
                else
                {
                    t1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(!num.equals(""))
                            {
                                Toast.makeText(ScrollingActivity.this,"Number : " + num,Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(ScrollingActivity.this,"No Number Found!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                if(t1==null)
                {
                    Toast.makeText(ScrollingActivity.this,"NULL " + num,Toast.LENGTH_SHORT).show();
                }
                else
                {
                    t2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(!num.equals(""))
                            {
                                Toast.makeText(ScrollingActivity.this,"Number : " + num,Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(ScrollingActivity.this,"No Number Found!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
*/

            }
        });

    }
    void getContacts()
    {

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, ContactsContract.Contacts.SORT_KEY_PRIMARY + " ASC");

        while (phones.moveToNext())
        {
            String names=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            if(name.equals(names))
            {

            }
            else
            {
                data.add(names + "\n" + phoneNumber);
                adapter.notifyDataSetChanged();
            }

            name = names;
        }

    }



}