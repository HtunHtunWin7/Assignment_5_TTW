package com.greenovator.assignment_5.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.greenovator.assignment_5.data.model.EventModelImpl;

public class BaseActivity extends AppCompatActivity {
    protected EventModelImpl eventModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventModel = EventModelImpl.getObjInstance();
    }
}
