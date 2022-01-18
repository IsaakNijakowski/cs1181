/*
 * CS 1181L-06
 * Isaak Nijakowski
 * Practice 1
 * 1/18/2022
 */

import java.util.*;

class Main{
  public static void main(String[] args){
    System.out.println("Hello World!");

    Rating r1 = new Rating();
    System.out.println("r1: " + r1);
    r1.addRating(5);
    System.out.println("r1: " + r1);
    r1.addRating(3.5);
    System.out.println("r1: " + r1);
   
    Rating r2 = new Rating(40, 10);
    System.out.println("r2: " + r2);
   
    Rating r3 = new Rating(12.75, 3);
    System.out.println("r3: " + r3);
   
    ArrayList<Rating> ratings = new ArrayList<>();
    ratings.add(r1);
    ratings.add(r2);
    ratings.add(r3);
    //Collections.sort(ratings);
   
    /*for (Rating r: ratings) {
    System.out.println(r);
    }*/
  }
}
