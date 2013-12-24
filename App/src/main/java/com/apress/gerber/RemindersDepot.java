package com.apress.gerber;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Adam Gerber on 12/22/13.
 */

//singleton class used to store Reminders
public class RemindersDepot {

    public static final String REMINDERS_PREF_KEY = "reminders_pref_key";
    private static RemindersDepot sRemindersDepot;
    private SharedPreferences mSharedPreferences;
    private Context mContext;

    private ArrayList<Reminder> mReminders;

    //singleton constructor is private
    private RemindersDepot(Context appContext) {
        mReminders = new ArrayList<Reminder>();
        mSharedPreferences = appContext.getSharedPreferences("com.apress.gerber", Context.MODE_PRIVATE);
        mContext = appContext;
    }


    public static RemindersDepot getInstance(Context context){
        if (sRemindersDepot == null){
            sRemindersDepot = new RemindersDepot(context);
        }
        return sRemindersDepot;
    }

    public Reminder getReminder(int position){
      return mReminders.get(position);
    }


    public void addReminder(Reminder reminder){
         mReminders.add(reminder);
    }

    //make sure we do not add duplicates
    public boolean containsReminder(Reminder reminder){
        if (mReminders.contains(reminder)){
            return true;
        } else {
            return false;
        }
    }

    public void deleteReminder(Reminder reminder){
        mReminders.remove(reminder);
    }

    public void deleteReminder(int position){
        mReminders.remove(position);
    }

    public ArrayList<Reminder> getReminders(){
        return mReminders;
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void writeOutAtFinish(){

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB){
            return;
        }

        Set<String> set = new LinkedHashSet<String>();
        for (Reminder reminder : mReminders) {
            set.add(reminder.getTitle());
        }
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putStringSet(REMINDERS_PREF_KEY, set);
        editor.commit();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void readInAtStart(){

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB){
            String[] strDummies = mContext.getResources().getStringArray(R.array.array_reminders);
            mReminders.clear();
            for (String strDummy : strDummies) {
                addReminder(new Reminder(strDummy));
            }
            return;
        }

        Set<String> set =  mSharedPreferences.getStringSet(REMINDERS_PREF_KEY, null);
        //if the set is initially null, then populate with dummy data
        if (set == null){
            String[] strDummies = mContext.getResources().getStringArray(R.array.array_reminders);
            for (String strDummy : strDummies) {
                addReminder(new Reminder(strDummy));
            }
        } else {
            mReminders.clear();
            for (String str : set) {
              mReminders.add(new Reminder(str));
            }
        }


    }






}
