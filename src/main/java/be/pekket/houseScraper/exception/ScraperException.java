package be.pekket.houseScraper.exception;

public class ScraperException extends Exception {

    public ScraperException( final String message ) {
        super(message);
    }

    public ScraperException( final String message, final Exception exception ) {
        super(message, exception);
    }
}
