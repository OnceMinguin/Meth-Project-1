//@author Andy Li Henry Lin
package adafnwa;

public class Collection {
    private static final int NOT_FOUND = -1;
    private Album[] albums;
    private int numAlbums; //number of albums currently in the collection

    private int find(Album album) {
        for (int i = 0; i < numAlbums; i++){
            if (albums[i].equals(album)){
                return i;
            }
        }
        return NOT_FOUND;
    } //find the album index, or return NOT_FOUND
    private void grow() {
        Album[] newarray = new Album[numAlbums + 4];
        if (numAlbums >= 0) System.arraycopy(albums, 0, newarray, 0, numAlbums);
        albums = newarray;
    } //increase the capacity of the array list by 4
    public boolean add(Album album) {
        if (numAlbums == 0){
            albums = new Album[4];
        }

        if (find(album) != NOT_FOUND){
            return false;
        }

        if (numAlbums % 4 == 0){ //if the number of albums is divisible by 4 you need to grow the arraylist by 4
            grow();
        }
        albums[numAlbums++] = album;
        return true;
    }
    public boolean remove(Album album) {
        int index = find(album);
        if (index == NOT_FOUND){
            return false;
        }
        Album[] removedalbum = new Album[albums.length];
        for (int i = 0; i<numAlbums; i++){
            if (i!=index){
                removedalbum[i] = albums[i];
            }
        }
        albums = removedalbum;
        numAlbums--;
        return true;
    }
    public boolean lendingOut(Album album) {
        int index = find(album);
        if (index == NOT_FOUND){
            return false;
        }
        return albums[index].loanedOut();
    } //set to not available
    public boolean returnAlbum(Album album) {
        int index = find(album);
        if (index == NOT_FOUND){
            return false;
        }
        return albums[index].returnAlbum();
    } //set to available
    public void print() {
        for (int i = 0; i < numAlbums; i++){
            System.out.println(albums[i].toString());
        }
    } //display the list without specifying the order
    public void printByReleaseDate() {
        for (int i = 0; i < numAlbums - 1; i++){
            int minIndex = i;
            for (int j = i+1; j < numAlbums; j++){
                if (albums[j].getDate().compareTo(albums[minIndex].getDate()) < 0 ){
                    minIndex = j;
                }
            }
            Album temp = albums[minIndex];
            albums[minIndex] = albums[i];
            albums[i] = temp;
        }
        for (int i = 0; i < numAlbums; i++){
            System.out.println(albums[i].toString());
        }
    }
    public void printByGenre() {
        for (int i = 0; i < numAlbums - 1; i++){
            int minIndex = i;
            for (int j = i+1; j < numAlbums; j++){
                if (albums[j].getGenre().compareTo(albums[minIndex].getGenre()) < 0 ){
                    minIndex = j;
                }
            }
            Album temp = albums[minIndex];
            albums[minIndex] = albums[i];
            albums[i] = temp;
        }
        for (int i = 0; i < numAlbums; i++){
            System.out.println(albums[i].toString());
        }
    }

}
