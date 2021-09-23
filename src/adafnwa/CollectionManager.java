//@author Andy Li Henry Lin
package adafnwa;
import java.util.Scanner;

public class CollectionManager {
    Collection collection;

    public void run(){
        Scanner scanner = new Scanner(System.in);
        collection = new Collection();
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if (line.charAt(0) == 'A' && line.charAt(1) == ',') {
                addAlbum(line);
            } else if (line.charAt(0) == 'D' && line.charAt(1) == ','){
                removeAlbum(line);
            } else if (line.charAt(0) == 'L' && line.charAt(1) == ','){
                lendAlbum(line);
            } else if (line.charAt(0) == 'R' && line.charAt(1) == ','){
                returnAlbum(line);
            } else if (line.charAt(0) == 'P'){
                display(line);
            } else if (line.charAt(0) == 'Q'){
                break;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }

    private void addAlbum(String text) {
        int index = text.indexOf(',');
        int secondIndex = text.indexOf(',', index + 1);
        String title = text.substring(index + 1,  secondIndex);
        index = secondIndex;
        secondIndex = text.indexOf(',', index + 1);
        String artist = text.substring(index + 1, secondIndex);
        index = secondIndex;
        secondIndex = text.indexOf(',', index + 1);
        String genre = text.substring(index + 1, secondIndex);
        String date = text.substring(secondIndex + 1);

        Album album;
        Date day = new Date(date);
        if (!day.isValid()){
            System.out.println("Invalid Date!");
        } else {
            album = switch (genre) {
                case "Classical" -> new Album(title, artist, Genre.Classical, day, true);
                case "Country" -> new Album(title, artist, Genre.Country, day, true);
                case "Jazz" -> new Album(title, artist, Genre.Jazz, day, true);
                case "Pop" -> new Album(title, artist, Genre.Pop, day, true);
                default -> new Album(title, artist, Genre.Unknown, day, true);
            };

            if (collection.add(album)) {
                System.out.println(album.toString() + " >> added.");
            } else {
                System.out.println(album.toString() + " >> is already in the collection.");
            }
        }
    }

    private void removeAlbum (String text){
        int index = text.indexOf(',');
        int secondIndex = text.lastIndexOf(',');
        String title = text.substring(index + 1, secondIndex);
        String artist = text.substring(secondIndex + 1);
        Album album = new Album (title, artist, Genre.Unknown, new Date("1/1/1111"), true);
        if (collection.remove(album)){
            System.out.println(album.titleToString() + " >> deleted.");
        } else {
            System.out.println(album.titleToString() + " >> is not in the collection.");
        }
    }
    private void lendAlbum (String text){
        int index = text.indexOf(',');
        int secondIndex = text.lastIndexOf(',');
        String title = text.substring(index + 1, secondIndex);
        String artist = text.substring(secondIndex + 1);
        Album album = new Album (title, artist, Genre.Unknown, new Date("1/1/1111"), true);
        if (collection.lendingOut(album)){
            System.out.println(album.titleToString()  + " >> lending out and set to not available.");
        } else {
            System.out.println(album.titleToString()  + " >> is not available.");
        }
    }
    private void returnAlbum (String text){
        int index = text.indexOf(',');
        int secondIndex = text.lastIndexOf(',');
        String title = text.substring(index + 1, secondIndex);
        String artist = text.substring(secondIndex + 1);
        Album album = new Album (title, artist, Genre.Unknown, new Date("1/1/1111"), true);
        if (collection.returnAlbum(album)){
            System.out.println(album.titleToString()  + " >> returning and set to available.");
        } else {
            System.out.println(album.titleToString()  + " >> return cannot be completed.");
        }
    }
    private void display (String text) {
        if (text.charAt(1) == 'D'){
            System.out.println("*Album collection by the release dates.");
            collection.printByReleaseDate();
            System.out.println("*End of list");
        } else if (text.charAt(1) == 'G'){
            System.out.println("*Album collection by genre.");
            collection.printByGenre();
            System.out.println("*End of list");
        } else if(text.charAt(1) == '\0'){
            System.out.println("*List of albums in the collection.");
            collection.print();
            System.out.println("*End of list");
        } else {
            System.out.println("Invalid Command");
        }
    }
}

