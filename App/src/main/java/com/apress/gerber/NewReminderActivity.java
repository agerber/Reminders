package com.apress.gerber;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class NewReminderActivity extends ActionBarActivity implements EditFragment.OnEditFragmentInteractionListener{

    public static final int NEW_REMINDER = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, EditFragment.newInstance(NEW_REMINDER))
                    .commit();
        }
    }


    @Override
    public void onEditFragmentInteraction() {
        finish();
    }


}
