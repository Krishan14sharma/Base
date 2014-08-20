
        package krishan.dhancha.model;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("MovieTitle")
    @Expose
    private String movieTitle;
    @SerializedName("MovieCover")
    @Expose
    private String movieCover;
    @SerializedName("ImdbCode")
    @Expose
    private String imdbCode;
    @SerializedName("ImdbLink")
    @Expose
    private String imdbLink;
    @SerializedName("Uploader")
    @Expose
    private String uploader;
    @SerializedName("UploaderUID")
    @Expose
    private String uploaderUID;
    @SerializedName("DateAdded")
    @Expose
    private String dateAdded;
    @SerializedName("DateAddedEpoch")
    @Expose
    private int dateAddedEpoch;

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieCover() {
        return movieCover;
    }

    public void setMovieCover(String movieCover) {
        this.movieCover = movieCover;
    }

    public String getImdbCode() {
        return imdbCode;
    }

    public void setImdbCode(String imdbCode) {
        this.imdbCode = imdbCode;
    }

    public String getImdbLink() {
        return imdbLink;
    }

    public void setImdbLink(String imdbLink) {
        this.imdbLink = imdbLink;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getUploaderUID() {
        return uploaderUID;
    }

    public void setUploaderUID(String uploaderUID) {
        this.uploaderUID = uploaderUID;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getDateAddedEpoch() {
        return dateAddedEpoch;
    }

    public void setDateAddedEpoch(int dateAddedEpoch) {
        this.dateAddedEpoch = dateAddedEpoch;
    }

}

