package oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;



class TwitterTest {

  @Test
  void testPostTweetAndGetNewsFeed() {
    Twitter twitter = new Twitter();
    twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5)
    List<Integer> newsFeed = twitter.getNewsFeed(1); // User 1's news feed
    assertEquals(List.of(5), newsFeed, "News feed should contain tweet ID 5");
  }

  @Test
  void testFollowAndGetNewsFeed() {
    Twitter twitter = new Twitter();
    twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5)
    twitter.follow(1, 2); // User 1 follows user 2
    twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6)
    List<Integer> newsFeed = twitter.getNewsFeed(1); // User 1's news feed
    assertEquals(List.of(6, 5), newsFeed, "News feed should contain tweet IDs 6 and 5 in order");
  }

  @Test
  void testUnfollowAndGetNewsFeed() {
    Twitter twitter = new Twitter();
    twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5)
    twitter.follow(1, 2); // User 1 follows user 2
    twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6)
    twitter.unfollow(1, 2); // User 1 unfollows user 2
    List<Integer> newsFeed = twitter.getNewsFeed(1); // User 1's news feed
    assertEquals(List.of(5), newsFeed, "News feed should only contain tweet ID 5 after unfollowing user 2");
  }

  @Test
  void testMultipleTweetsAndGetNewsFeed() {
    Twitter twitter = new Twitter();
    twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5)
    twitter.postTweet(1, 10); // User 1 posts another tweet (id = 10)
    List<Integer> newsFeed = twitter.getNewsFeed(1); // User 1's news feed
    assertEquals(List.of(10, 5), newsFeed, "News feed should contain tweet IDs 10 and 5 in order");
  }

  @Test
  void testNewsFeedLimit() {
    Twitter twitter = new Twitter();
    for (int i = 1; i <= 15; i++) {
      twitter.postTweet(1, i); // User 1 posts 15 tweets
    }
    List<Integer> newsFeed = twitter.getNewsFeed(1); // User 1's news feed
    assertEquals(10, newsFeed.size(), "News feed should contain at most 10 tweets");
    assertEquals(List.of(15, 14, 13, 12, 11, 10, 9, 8, 7, 6), newsFeed, "News feed should contain the 10 most recent tweets");
  }

  @Test
  void testFollowSelf() {
    Twitter twitter = new Twitter();
    twitter.follow(1, 1); // User 1 follows themselves
    twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5)
    List<Integer> newsFeed = twitter.getNewsFeed(1); // User 1's news feed
    assertEquals(List.of(5), newsFeed, "News feed should contain tweet ID 5");
  }
}