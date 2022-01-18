public class Rating {
    private double sumOfRatings;
    // the sum of all of the ratings
    private int raters;
    // the number of people who have rated an item

    public Rating() {
        sumOfRatings = 0;
        raters = 0;
    }
    // initialize both fields to zero
    public Rating(double sumOfRatings, int raters) {
        this.sumOfRatings = sumOfRatings;
        this.raters = raters;
    }
    // initialize the fields to the parameter values
    public void addRating(double newRating) {
        sumOfRatings += newRating;
        raters++;
    } 
    // increase the sumOfRatings by newRating and increase the number of raters by one
    public double getAverageRating() {
        double averageRating = (raters == 0) ? 0: (double) sumOfRatings / raters;
        return averageRating;
    } 
    // return the average rating; if there are no raters so far, return 0.0
    public String toString() {
        String returnString = getAverageRating() + " based on " + raters + " reviews";
        return returnString;
    }
    /* display the average rating and how many
    reviews it is based on; for example, if the sum of the ratings is 32
    and that is based on ratings from ten people, the toString method will
    return “3.2 based on 10 reviews”*/
}
