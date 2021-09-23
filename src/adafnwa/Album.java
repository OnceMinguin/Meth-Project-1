//@author Andy Li Henry Lin
package adafnwa;

public class Album {
    private String title;
    private String artist;
    private Genre genre; //enum class; Classical, Country, Jazz, Pop, Unknown
    private Date releaseDate;
    private boolean isAvailable;

    public Album(String title, String artist, Genre genre, Date releaseDate, boolean isAvailable){
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.isAvailable = isAvailable;
    }

    @Override
    public boolean equals(Album obj) {
        return this.title.equals(obj.title) && this.artist.equals(obj.artist);
    }
    @Override
    public String toString() {
        String album = this.title + "::" + this.artist + "::" + genre.toString(genre)
                + "::" + this.releaseDate.toString() + "::";
        if (this.isAvailable){
            album += "is available";
        }
        else{
            album += "is not available";
        }
        return album;
    }

    public String titleToString() {
        return this.title + "::" + this.artist;
    }

    public boolean loanedOut(){
        if (this.isAvailable){
            this.isAvailable = false;
            return true;
        }
        return false;
    }
    public boolean returnAlbum(){
        if (!this.isAvailable){
            this.isAvailable = true;
            return true;
        }
        return false;
    }
    public String getGenre(){
        return this.genre.toString();
    }
    public Date getDate(){
        return this.releaseDate;
    }
}
