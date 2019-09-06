package com.greenovator.assignment_5.view.holder;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.greenovator.assignment_5.R;
import com.greenovator.assignment_5.data.VO.EventVo;
import com.greenovator.assignment_5.delegate.EventDetailDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventListHolder extends BaseViewHolder<EventVo> {
    /*@BindView(R.id.image_house)
    ImageView house_image;
    @BindView(R.id.house_price)
    TextView house_price;
    @BindView(R.id.adresss_house)
    TextView house_adress;
    @BindView(R.id.location_house)
    TextView house_location;
    @BindView(R.id.squarefeet_house)
    TextView house_squarefeet;*/
    private ImageView house_image;
    private TextView house_price, house_name, house_location, house_squarefeet;
    private EventDetailDelegate detailDelegate;

    public EventListHolder(@NonNull View itemView, EventDetailDelegate delegate) {
        super(itemView);
        house_image = itemView.findViewById(R.id.image_house);
        house_location = itemView.findViewById(R.id.location_house);
        house_name = itemView.findViewById(R.id.house_name);
        house_squarefeet = itemView.findViewById(R.id.squarefeet_house);
        house_price = itemView.findViewById(R.id.house_price);
       // ButterKnife.bind(itemView);
        detailDelegate = delegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailDelegate.onClickDetail(maData.getId());
            }
        });
    }

    @Override
    public void bindData(EventVo data) {
        maData = data;
        house_price.setText("MMK "+String.valueOf(data.getPrice()));
        house_name.setText(data.getName());
        house_squarefeet.setText(String.valueOf(data.getSquareFeet())+" saft");
        house_location.setText(data.getAddress());
        Glide.with(itemView)
                .load(Uri.parse(data.getHouseImageUri()))
                .into(house_image);
    }
}
