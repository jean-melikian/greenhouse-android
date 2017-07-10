package com.android.greenhouse.greenhouseapp.model;

/**
 * Created by antoinepelletier on 04/07/2017.
 */

public class SensorEntry {

    private int count;
    private Entries[] entries;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Entries[] getEntries() {
        return entries;
    }

    public void setEntries(Entries[] entries) {
        this.entries = entries;
    }

    /**
     * Constructor
     *
     * @param mCount
     * @param mEntries
     */
    public SensorEntry(int mCount, Entries[] mEntries) {
        count = mCount;
        entries = mEntries;
    }

    @Override
    public String toString() {
        String str = "SensorEntry [count = " + count + "], Entries :\n";
        for (Entries entry : entries) {
            str += entry.toString() + ", \n";
        }
        return str;
    }
}
