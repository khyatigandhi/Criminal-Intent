package com.knoxpo.khyati.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private boolean mSeriousCrime;

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public boolean isSeriousCrime() {
        return mSeriousCrime;
    }

    public void setSeriousCrime(boolean seriousCrime) {
        mSeriousCrime = seriousCrime;
    }

    public  Crime()


    {
        mId=UUID.randomUUID();
        mDate = new Date();
    }
}
