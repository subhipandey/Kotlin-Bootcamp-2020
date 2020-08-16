/*
 * This example adapted from 'Refactoring' by Martin Fowler
 *  
 */
package videostore.start;

public class Rental {
    private Movie movie;
    private int daysRented;
    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }
    public int getDaysRented() {
        return daysRented;
    }
    public Movie getMovie() {
        return movie;
    }
	double cost() {
		return movie.cost(daysRented);
	}
	int points() {
		return movie.points(daysRented);
	}
}