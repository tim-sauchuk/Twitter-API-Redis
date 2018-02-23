package main;

import java.util.List;
import java.util.Set;
import redis.clients.jedis.*;

/**
 * Created by Tim on 2/3/2018.
 */
public class TwitterAPIRedis implements TwitterAPI {

    // server client to be used with Redis
    Jedis jedis;

    // constructor
    public TwitterAPIRedis(){
        this.jedis = new Jedis("localhost");
    }

    // increments and returns the Tweet ID of the next tweet
    public long getNextTweetId(){
        long tweetId = jedis.incr("nextTweetId");
        return tweetId;
    }
    // posts a single tweet
    public void postTweet(Tweet t, boolean broadcast){

        String key = "Tweet:" + t.getUserId() + ":" + Long.toString(this.getNextTweetId());
        String value = t.getTweetTs() + t.getTweetText();

        // add tweet, regardless of broadcast
        this.jedis.set(key, value);

        // add tweet if broadcast is on
        if (broadcast)
        {
            Set<String> followers = jedis.smembers("Followers:"+t.getUserID());
            for (String f : followers)
                addToTimeline(t, f);

        }
    }

    // adds a follower to the followee
    public void addFollower(String followerId, String followeeId){
        String key = "Followers:" + followeeId;
        this.jedis.sadd(key, followerId);
    }

    // add tweet to a follower's timeline
    public void addToTimeline(Tweet t, String followerId){
        String key = "Timeline:" + followerId;
        String value = t.toString();
        this.jedis.lpush(key, value);
    }

    // returns all tweets from who the user follows
    public List<Tweet> getTimeline(String userId){
        String key = "Timeline:" + userId;
        List<Tweet> timeline = this.jedis.get(key);
        return timeline;
    }

    // returns all followers of the user
    public List<String> getFollowers(String userId){
        String key = "Followers:" + userId;
        List<String> followers = this.jedis.get(key);
        return followers;
    }

    // return all users the user follows
    public List<String> getFollowees(String userId){
        // TODO
    }

    // return all tweets by the user
    public List<Tweet> getTweets(String userId){
        String key = "Tweet:" + userId + "*";
        List<Tweet> tweets = this.jedis.keys(key);
        return tweets;
    }

    // clears all of the data from the database
    public void reset(){
        this.jedis.flushAll();
    }
}
