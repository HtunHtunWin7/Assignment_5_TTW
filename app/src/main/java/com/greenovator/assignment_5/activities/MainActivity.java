package com.greenovator.assignment_5.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.greenovator.assignment_5.R;
import com.greenovator.assignment_5.adapters.EventListAdapter;
import com.greenovator.assignment_5.data.VO.EventVo;
import com.greenovator.assignment_5.data.model.EventModel;
import com.greenovator.assignment_5.data.model.EventModelImpl;
import com.greenovator.assignment_5.delegate.EventDetailDelegate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.widget.LinearLayout.*;

public class MainActivity extends BaseActivity implements EventDetailDelegate, View.OnClickListener {
    private TextView mTextMessage;
    private EventDetailDelegate eventDetailDelegate;
    private TextView horizontal, vertical;
    private EventListAdapter adapter;
    private RecyclerView recyclerView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        horizontal = findViewById(R.id.item_horizontal);
        vertical = findViewById(R.id.item_vertical);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventListAdapter(this);
        recyclerView.setAdapter(adapter);
        eventModel.getEvents(new EventModel.GetEventsFromNetworkDelegate() {
            @Override
            public void onSuccess(List<EventVo> eventVos) {
                adapter.setNewData(eventVos);
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        horizontal.setOnClickListener(this);
        vertical.setOnClickListener(this);
    }


    @Override
    public void onClickDetail(int id) {
        Intent intent = EventDetailActivity.newIntent(getApplicationContext(), id);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_horizontal:
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
                recyclerView.setAdapter(adapter);
                break;
            case R.id.item_vertical:
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                recyclerView.setAdapter(adapter);
                break;
        }
    }
}
