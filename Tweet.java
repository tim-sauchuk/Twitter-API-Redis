package main;

import java.util.Date;

// class representing a single tweet
public class Tweet {

    // user who the tweet belongs to
    private String userId;
    // milliseconds from Jan 1, 1970 to time of tweet
    private Date tweetTs;
    // text in the tweet
    private String tweetText;

    public Tweet(String userId, Date tweetTs, String tweetText){
        this.userId = userId;
        this.tweetTs = tweetTs;
        this.tweetText = tweetText;
    }

    // returns user's ID
    public String getUserId(){
        return this.userId;
    }

    // returns date of tweet
    public String getTweetTs(){
        return Long.toString(this.tweetTs.getTime());
    }

    // returns text from tweet
    public String getTweetText(){
        return this.getTweetTs() + ":" + this.tweetText;
    }
}