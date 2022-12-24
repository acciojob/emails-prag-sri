package com.driver;

import java.util.ArrayList;
import java.util.Date;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity= inboxCapacity;
    }


    ArrayList<ArrayList<String>> mails= new ArrayList<>();
    ArrayList<ArrayList<String>> trashmails= new ArrayList<>();
    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

        String dt= date.toString();
        ArrayList<String> currMail= new ArrayList<>();
        currMail.add(dt);
        currMail.add(sender);
        currMail.add(message);
        mails.add(currMail);
//        Collections.sort(mails,(a, b)-> a.get(0).compareTo(b.get(0))>1?1:-1);
        if(mails.size()>inboxCapacity) {
            ArrayList<String> curr = mails.get(0);
            mails.remove(0);
            trashmails.add(curr);
        }
    }

//    ArrayList<ArrayList<String>> printMail(){
//        return mails;
//    }
//
//    ArrayList<ArrayList<String>> printTrash(){
//        return trashmails;
//    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(int i=0; i<mails.size(); i++)
        {
            if(mails.get(i).get(2).equals(message))
            {
                ArrayList<String> currMail= mails.get(i);
                mails.remove(i);
                trashmails.add(currMail);
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

        if(mails.size()==0)
            return null;

        return mails.get(mails.size()-1).get(2);
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(mails.size()==0)
            return null;

        return mails.get(0).get(2);

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date

        int count=0;
        String startDt= start.toString();
        String endDt= end.toString();

        int i=0;
        while(i<mails.size() && startDt.compareTo(mails.get(i).get(0).toString())<=0)
        {
//           System.out.println(mails.get(i).get(0).toString());
            i++;
        }

        while(i<mails.size() && endDt.compareTo(mails.get(i).get(0).toString())<=0)
        {
//            System.out.println(mails.get(i).get(0).toString());
            i++;
            count++;
        }

        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return mails.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trashmails.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trashmails.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
