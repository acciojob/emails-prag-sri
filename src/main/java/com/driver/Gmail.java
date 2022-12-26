package com.driver;

import java.util.ArrayList;
import java.util.Date;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    private ArrayList<Mail> mails;
    private ArrayList<Mail> trashmails;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity= inboxCapacity;
        this.mails= new ArrayList<>();
        this.trashmails= new ArrayList<>();
    }

    public ArrayList<Mail> getMails() {
        return mails;
    }

    public ArrayList<Mail> getTrashmails() {
        return trashmails;
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.


        Mail newMail = new Mail(date,sender,message);

        if(inboxCapacity==mails.size()) {
            mails.remove(0);
            trashmails.add(newMail);
        }
        mails.add(newMail);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(Mail m: mails)
        {
            if(m.getMessage().equals(message))
            {
                trashmails.add(m);
                mails.remove(m);
                break;
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

        if(mails.size()==0)
            return null;

        return mails.get(mails.size()-1).getMessage();
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(mails.size()==0)
            return null;

        return mails.get(0).getMessage();

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date

        int count=0;

        for(int i=0; i<mails.size(); i++)
        {
            if(start.compareTo(mails.get(i).getDate())<=0)
            {
                while(i<mails.size() && end.compareTo(mails.get(i).getDate())>=0)
                {
                    count++;
                    i++;
                }
            }
            if(count>0)
                break;
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
