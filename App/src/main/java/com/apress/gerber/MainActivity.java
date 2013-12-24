package com.apress.gerber;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity implements MainFragment.OnMainFragmentInteractionListener {

    public static final String REMINDER_ID = "reminder_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RemindersDepot.getInstance(this).readInAtStart();

        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
            case R.id.action_exit:
                finish();
                return true;
            case R.id.action_add:
                //call NewReminderActivity
                Intent intent = new Intent(this, NewReminderActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onMainFragmentInteraction(int position) {

         //fire-up DetailActivity and pass id to the bundle
        Bundle bundle = new Bundle();
        bundle.putInt(REMINDER_ID, position);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        RemindersDepot.getInstance(this).writeOutAtFinish();
        super.onDestroy();
    }
}
