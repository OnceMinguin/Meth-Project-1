package adafnwa;

public enum Genre {
    Classical, Country, Jazz, Pop, Unknown;
    public String toString(Genre genre){
        if (genre == Classical){
            return "Classical";
        }
        else if (genre == Country){
            return "Country";
        }
        else if (genre == Jazz){
            return "Jazz";
        }
        else if (genre == Pop){
            return "Pop";
        }
        else {
            return "Unknown";
        }
    }
}
