package main;

import java.util.Date;

/**
 * Created by Tim on 2/3/2018.
 */
public class Tester {

    private static TwitterAPI api = new TwitterAPIRedis();

    public static void main(String[] args) {
        Tweet t = new Tweet("5", new Date(), "NoSQL is fun");
        api.postTweet(t, true);
    }
}
