package bookmyshow.models;

public class Movie {
    private final String movieId;
    private final String title;
    private final String language;
    private final int    durationMinutes;
    private final String genre;

    public Movie(String movieId, String title, String language,
                 int durationMinutes, String genre) {
        this.movieId         = movieId;
        this.title           = title;
        this.language        = language;
        this.durationMinutes = durationMinutes;
        this.genre           = genre;
    }

    public String getMovieId()         { return movieId; }
    public String getTitle()           { return title; }
    public String getLanguage()        { return language; }
    public int    getDurationMinutes() { return durationMinutes; }
    public String getGenre()           { return genre; }
}
