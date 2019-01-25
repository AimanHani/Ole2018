package com.example.ole.oleandroid.pageController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.ole.oleandroid.R;

public class FAQ extends AppCompatActivity {

    LinearLayout frame;
    TextView qOne;
    TextView aOne;
    TextView qTwo;
    TextView aTwo;
    TextView qThree;
    TextView aThree;
    TextView qFour;
    TextView aFour;
    TextView askMore;
    Button ctaAsk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        frame = findViewById(R.id.faqFrame);

        qOne = findViewById(R.id.faqQuesOne);
        aOne = findViewById(R.id.faqAnsOne);
        qTwo = findViewById(R.id.faqQuesTwo);
        aTwo = findViewById(R.id.faqAnsTwo);
        qThree = findViewById(R.id.faqQuesThree);
        aThree = findViewById(R.id.faqAnsThree);
        qFour = findViewById(R.id.faqQuesFour);
        aFour = findViewById(R.id.faqAnsFour);
        askMore = findViewById(R.id.faqAskMore);

        ctaAsk = findViewById(R.id.askOleButton);

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
