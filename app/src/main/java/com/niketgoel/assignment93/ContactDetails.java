package com.niketgoel.assignment93;

/**
 * Created by niketgoel on 05/11/17.
 */


public class ContactDetails {
    private String mContactNames;
    private String mContactNumbers;

    /**
     * Constructor to initialize the data members
     * @param contactNames
     * @param contactNumbers
     */
    public ContactDetails(String contactNames, String contactNumbers){
        mContactNames = contactNames;
        mContactNumbers = contactNumbers;
    }
    /**
     * Getters to retrieve the data
     * @return
     */
    public String getmContactNames() {
        return mContactNames;
    }

    public String getmContactNumbers() {
        return mContactNumbers;
    }
}