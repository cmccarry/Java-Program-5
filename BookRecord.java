package book;

import java.util.Arrays;

public class BookRecord {
    static int uniqueID = 1;
    int record_id;
    String title;
    BookGenre genre;
    String[] listOfAuthors;
   
   
   
    public BookRecord() {
        this.record_id = this.uniqueID;
        this.uniqueID++;
    }

    public int getUnique_record_id() {
        return uniqueID;
    }

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public String[] getListOfAuthors() {
        return listOfAuthors;
    }

    public void setListOfAuthors(String[] listOfAuthors) {
        this.listOfAuthors = listOfAuthors;
    }
}
