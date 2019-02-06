package com.example.ole.oleandroid.pageController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.ole.oleandroid.R;

public class FAQ extends AppCompatActivity {

    LinearLayout frame;
    CardView profile;
    CardView pubLeague;
    CardView privLeague;
    CardView askOle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        frame = findViewById(R.id.faqFrame);

        profile = findViewById(R.id.profileCard);
        pubLeague = findViewById(R.id.publicCard);
        privLeague = findViewById(R.id.privateCard);
        askOle = findViewById(R.id.askCard);

        profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(FAQ.this, FAQProfile.class);
                Bundle b = getIntent().getExtras();
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        pubLeague.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(FAQ.this, FAQPublic.class);
                Bundle b = getIntent().getExtras();
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        privLeague.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(FAQ.this, FAQPrivate.class);
                Bundle b = getIntent().getExtras();
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        askOle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(FAQ.this, AskOle.class);
                Bundle b = getIntent().getExtras();
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        /*frame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                aOne.setVisibility(View.GONE);
                return true;
            }
        });*/



        /*qOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_faq);
                aOne.setVisibility(View.VISIBLE);
            }
        });*/
    }
}
