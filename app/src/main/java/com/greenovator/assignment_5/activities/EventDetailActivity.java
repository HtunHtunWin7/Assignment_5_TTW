package com.greenovator.assignment_5.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.greenovator.assignment_5.R;
import com.greenovator.assignment_5.delegate.EventDetailDelegate;

public class EventDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evetn_item_detail);
    }

}
