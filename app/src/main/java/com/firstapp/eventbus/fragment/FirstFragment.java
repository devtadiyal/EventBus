package com.firstapp.eventbus.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firstapp.eventbus.R;
import com.firstapp.eventbus.eventbus.Events;
import com.firstapp.eventbus.eventbus.GlobalBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    public FirstFragment() {
        // Required empty public constructor
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setClickListener(view);
    }

    public void setClickListener(final View view) {
        Button btnSubmit = view.findViewById(R.id.submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etMessage = view.findViewById(R.id.editText);
                // We are broadcasting the message here to listen to the subscriber.
                Events.FragmentActivityMessage fragmentActivityMessageEvent =
                        new Events.FragmentActivityMessage(
                                String.valueOf(etMessage.getText()));
                System.out.println("STRING : "+String.valueOf(etMessage.getText()));
                GlobalBus.getBus().post(fragmentActivityMessageEvent);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Register the event to subscribe.
        GlobalBus.getBus().register(this);
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

@Subscribe
public void getMessage(Events.ActivityFragmentMessage activityFragmentMessage)
{
    Toast.makeText(getActivity(),
            activityFragmentMessage.getMessage(),
            Toast.LENGTH_SHORT).show();

}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Unregister the registered event.
        GlobalBus.getBus().unregister(this);
    }
}
