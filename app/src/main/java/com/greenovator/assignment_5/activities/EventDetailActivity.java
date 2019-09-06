package com.greenovator.assignment_5.activities;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.greenovator.assignment_5.R;
import com.greenovator.assignment_5.data.VO.EventVo;
import com.greenovator.assignment_5.delegate.EventDetailDelegate;

import butterknife.BindView;

public class EventDetailActivity extends BaseActivity  {

    private TextView detail_price, detail_address, detail_Name, detail_description, detail_squarehouse;
    private ImageView detail_image;
    private Button detail_floatingaction;

    public static final String EXTRA_TO_EXTRA = "eventIdExtra";

    public static Intent newIntent(Context context, int eventIdExtra) {
        Intent intent = new Intent(context, EventDetailActivity.class);
        intent.putExtra(EXTRA_TO_EXTRA, eventIdExtra);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evetn_item_detail);
        detail_address = findViewById(R.id.detail_place);
        detail_description = findViewById(R.id.detail_description);
        detail_Name = findViewById(R.id.detail_name);
        detail_price = findViewById(R.id.detail_price);
        detail_squarehouse = findViewById(R.id.detail_squarehouse);
        detail_image = findViewById(R.id.detail_image);
        detail_floatingaction = findViewById(R.id.detail_btn_favoriate);
        detail_floatingaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EventDetailActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
        int eventId = getIntent().getIntExtra(EXTRA_TO_EXTRA,0);
        EventVo event = eventModel.findById(eventId);
        bindData(event);
    }

    private void bindData(EventVo eventVo) {
        Glide.with(this)
                .load(Uri.parse(eventVo.getHouseImageUri()))
                .into(detail_image);
        detail_Name.setText(eventVo.getName());
        detail_squarehouse.setText(String.valueOf(eventVo.getSquareFeet()) + " staft");
        detail_description.setText(eventVo.getDescription());
        detail_address.setText(eventVo.getAddress());
        detail_price.setText("MMK " + eventVo.getPrice());

    }

}
