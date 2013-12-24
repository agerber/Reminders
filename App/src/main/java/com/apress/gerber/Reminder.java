package com.apress.gerber;

/**
 * Created by Adam Gerber on 12/22/13.
 */
public class Reminder {

    private String mTitle;

    //constructor
    public Reminder(String title) {
        mTitle = title;
    }

    //getters and setters
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }

    /* we need to override the equals method so that the contains() method of ArrayList
    can identify duplicates */
    @Override
    public boolean equals(Object o) {
         Reminder reminder = (Reminder) o;
         if(mTitle.equals(reminder.getTitle())){
            return  true;
         } else {
             return false;
         }

    }

    //this will be used to display Reminders in the list
    @Override
    public String toString() {
        return  mTitle;
    }


}
