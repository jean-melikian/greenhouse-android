package com.android.greenhouse.greenhouseapp.model;

/**
 * Created by antoinepelletier on 04/07/2017.
 */

public class SensorsEntries {

    private int count;
    private Sensors[] entries;

    /**
     * Constructor
     *
     * @param mCount
     * @param mEntries
     */
    public SensorsEntries(int mCount, Sensors[] mEntries) {
        count = mCount;
        entries = mEntries;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Sensors[] getEntries() {
        return entries;
    }

    public void setEntries(Sensors[] entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        String str = "SensorsEntries [count = " + count + "], Sensors :\n";
        for (Sensors entry : entries) {
            str += entry.toString() + ", \n";
        }
        return str;
    }
}
