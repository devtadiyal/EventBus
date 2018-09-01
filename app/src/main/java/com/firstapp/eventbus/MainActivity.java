package com.firstapp.eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.eventbus.eventbus.Events;
import com.firstapp.eventbus.eventbus.GlobalBus;
import com.firstapp.eventbus.fragment.FirstFragment;

import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {
    EditText e_getvalue;
    Button b_submit;
    TextView t_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e_getvalue = findViewById(R.id.activity_edit);
        b_submit = findViewById(R.id.activity_submit);
        t_show = findViewById(R.id.activity_msg);
        addFragment();
        b_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Events.ActivityFragmentMessage activityFragmentMessageEvent =
                        new Events.ActivityFragmentMessage(String.valueOf(e_getvalue.getText().toString()));
                GlobalBus.getBus().post(activityFragmentMessageEvent);

            }
        });
    }

    @Subscribe
    public void getMessage(Events.FragmentActivityMessage fragmentActivityMessage) {

        Toast.makeText(getApplicationContext(),
                fragmentActivityMessage.getMessage(),
                Toast.LENGTH_SHORT).show();
    }


    private void addFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_one, new FirstFragment())
                .commit();
    }


}
