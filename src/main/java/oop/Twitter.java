package oop;

import java.util.*;
/**
 * 6. Design Twitter 
 * Design a simplified version of Twitter where users can post tweets, 
 * follow/unfollow another user, and is able to see the 10 most recent tweets in 
 * the user's news feed. 
 *  
 * Implement the Twitter class: 
 *  
 * Twitter() Initializes your twitter object. 
 * void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId 
 * by the user userId. Each call to this function will be made with a unique 
 * tweetId. 
 * List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs 
 * in the user's news feed. Each item in the news feed must be posted by users 
 * who the user followed or by the user themself. Tweets must be ordered from 
 * most recent to least recent. 
 * void follow(int followerId, int followeeId) The user with ID followerId 
 * started following the user with ID followeeId. 
 * void unfollow(int followerId, int followeeId) The user with ID followerId 
 * started unfollowing the user with ID followeeId. 
 * 
 *  
 * Example 1: 
 *  
 * Input 
 * ["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", 
 * "unfollow", "getNewsFeed"] 
 * [[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]] 
 * Output 
 * [null, null, [5], null, null, [6, 5], null, [5]] 
 *  
 * Explanation 
 * Twitter twitter = new Twitter(); 
 * twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5). 
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 
 * tweet id -> [5]. return [5] 
 * twitter.follow(1, 2);    // User 1 follows user 2. 
 * twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6). 
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 
 * tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is 
 * posted after tweet id 5. 
 * twitter.unfollow(1, 2);  // User 1 unfollows user 2. 
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 
 * tweet id -> [5], since user 1 is no longer following user 2. 
 * 
 *  
 * Constraints: 
 *  
 * 1 <= userId, followerId, followeeId <= 500 
 * 0 <= tweetId <= 104 
 * All the tweets have unique IDs. 
 * At most 3 * 104 calls will be made to postTweet, getNewsFeed, follow, and 
 * unfollow. 
 * A user cannot follow himself. 
 * -----------------------------
 * 
 */

 class Twitter {
    private Deque<Pair>[] tweets;
    private Set<Integer>[] followees;
    private long sn = 0;
  
    public Twitter() {
        tweets = new Deque[501];
        followees = new Set[501];
    }
    
    public void postTweet(int userId, int tweetId) {
      if (tweets[userId] == null) {
        tweets[userId] = new ArrayDeque<>();
        follow(userId, userId);
      }
      tweets[userId].addFirst(new Pair(sn++, tweetId));
      //if (queue.size() > 10) queue.removeLast();
    }
    
    public List<Integer> getNewsFeed(int userId) {
        if(followees[userId] == null) return new ArrayList<>();
  
        PriorityQueue<Pair> heap = new PriorityQueue<>(Comparator.comparingLong(a -> a.time));
        for(int followeeId : followees[userId]) {
          if (tweets[followeeId] == null) continue;
          for(Pair pair : tweets[followeeId]) {
            if(heap.size() == 10 && pair.time <= heap.peek().time) break;
            heap.add(pair);
            if (heap.size()>10) heap.remove();
          }
        }
  
        Deque<Integer> queue = new ArrayDeque<>();
        while(!heap.isEmpty()) {
          queue.addFirst(heap.remove().tweetId);
        }
        return new ArrayList<>(queue);
    }
    
    public void follow(int followerId, int followeeId) {
      if(followees[followerId] == null) followees[followerId] = new HashSet<>();
      followees[followerId].add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
      if(followees[followerId] != null) {
        followees[followerId].remove(followeeId);
      }
    }
  }
  class Pair {
    long time;
    int tweetId;
    public Pair(long time, int tid) {
      this.time = time;
      tweetId = tid;
    }
  }
  