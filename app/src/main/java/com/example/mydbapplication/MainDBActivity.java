package com.example.mydbapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainDBActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.add_btn)
    Button mAddBtn;
    @Bind(R.id.del_btn)
    Button mDelBtn;
    @Bind(R.id.update_btn)
    Button mUpdateBtn;

    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_db);
        ButterKnife.bind(this);

        dbHelper = new DbHelper(this);
        dbHelper.clearTable();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setUpRecyclerView();

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.addContact(new Contact("Sample Name", 23));
                ((RecyclerContactAdapter)mRecyclerView.getAdapter()).updateList(dbHelper.getAllContacts());
            }
        });

        mDelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteContact(dbHelper.getLastId());
                ((RecyclerContactAdapter)mRecyclerView.getAdapter()).updateList(dbHelper.getAllContacts());
            }
        });

        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.updateContact(new Contact(dbHelper.getLastId(), "Updated Name", 67));
                ((RecyclerContactAdapter)mRecyclerView.getAdapter()).updateList(dbHelper.getAllContacts());
            }
        });

    }

    private void setUpRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyclerContactAdapter(dbHelper.getAllContacts()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_db, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
