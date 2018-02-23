package main;

import java.util.List;

// interface for all Twitter API's
public interface TwitterAPI {

    // increments and returns Tweet ID of next tweet
    long getNextTweetId();

    // posts a single tweet
    void postTweet(Tweet t, boolean broadcast);

    // adds a follower to the followee
    void addFollower(String followerId, String followeeId);

    // adds tweet to follower's timeline
    void addToTimeline(Tweet t, String followerId);

    // returns all tweets from who the user follows
    List<Tweet> getTimeline(String userId);

    // returns all followers of the user
    List<String> getFollowers(String userId);

    // return all users the user follows
    List<String> getFollowees(String userId);

    // return all tweets by the user
    List<Tweet> getTweets(String userId);

    // clears all of the data from the database
    void reset();
}