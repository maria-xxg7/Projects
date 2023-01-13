/**
 * Maria Gharabaghi
 * December 1, 2021
 * This class creates patrons & allows users to see which books the patrons have taken out, as well as general information (name, ID number)
 */

package gharabaghilibrary;

//Import
import java.text.DecimalFormat;

public class Patron {
    //Attributes
    private String name; //Name of the patron
    private int numID; //ID number of the patron
    private Book [] book = new Book[3]; //Array - stores book objects
    
    /**
     * Primary Constructor
     * @param name - name of the patron
     * @param numID - ID number of the patron
     */
    public Patron(String name, int numID) {
        this.name = name; //Name of the patron
        this.numID = numID; //ID number of the patron
    }
    
    /**
     * Secondary Constructor
     * @param name - name of the patron
     * @param numID - ID number of the patron
     * @param book1 - first book
     * @param book2 - second book
     * @param book3 - third book
     */
    public Patron(String name, int numID, Book book1, Book book2, Book book3) {
        this(name, numID); //call on the primary constructor
        //Set books in the book array to the inputted books
        this.book[0] = book1;
        this.book[1] = book2;
        this.book[2] = book3;
    }
    
    /**
     * Accessor: Getting the name of the patron
     * @return patron's name
     */
    public String getName() {
        return this.name;
    }
    
    //No Mutator (can't change the name of existing patrons)
    
    /**
     * Accessor: Getting the ID of the patron
     * @return patron's ID number
     */
    public int getID() {
        return numID;
    }
    
    //No Mutator (can't change the ID number of the exisitng patrons)
    
    /**
     * Accessor: Getting the books of the patrons
     * @param i - book # (1, 2, or 3)
     * @return title of the book requested
     */
    public String getBook(int i) {
        if (i == 1) { 
            return book[0].getTitle(); //Return book 1
        } else if (i == 2) {
            return book[1].getTitle(); //Return book 2
        } else {
            return book[2].getTitle(); //Return book 3
        }
    }
    
    //No Mutator (can't change the book titles because then it would be a different book)
    
    /**
     * Accessor: Gets if the book has been borrowed
     * @param title - input the title of the book (check if it has been borrowed)
     * @return if the book has been borrowed
     */
    public boolean hasBorrowed(String title) {
        boolean hasBorrowed = false; //boolean for if the book has been borrowed
        for (Book books : book) { //Enhanced for (loop through all of the books in the book array)
            if(books == null) { 
                hasBorrowed = false; //If any of the books are null, then the book has not been borrowed (does not exist)
            }else if (title.equalsIgnoreCase(books.getTitle())) {
                hasBorrowed = true; //If the title inputted matches any of the existing book titles, then the book has been borrowed
            }
        }
        return hasBorrowed;
    }
    
    //No Mutator (can't change who has the book out when they haven't returned it)

    /**
     * Accessor: gets the author of one of the books in the system
     * @param author - the author of the book
     * @return the author's name if they exist in the system
     */
    public boolean getAuthor(String author) {
        boolean getAuthor = false; //Boolean for if the author exists
        for (Book books : book) { //Loop through all of the books
            if(books == null) {
                getAuthor = false; //If any of the books are null, then the author doesn't exist (don't return)
            }else if (author.equalsIgnoreCase(books.getAuthor())) {
                getAuthor = true; //If the inputted author's name matches any of the authors in the system, then the author exists
            }
        }
        return getAuthor;
    }
    
    //No Mutator (can't change the author of the book)
    
    /**
     * Accessor: gets a book that the author wrote
     * @param author - author of the book
     * @return title of the book
     */
    public String checkAuthor(String author) {
        String title = ""; //String that stores the title of the book
        for (Book book1 : book) { //Loop through all of the books
            if (author.equalsIgnoreCase(book1.getAuthor())) { //If the author inputted matches an existing author, then set the title to the book title
                title = book1.getTitle();
            }
        }
        return title;
    }
    
    /**
     * Java toString() method 
     * @return the information about the patrons
     */
    public String toString() {
        DecimalFormat number = new DecimalFormat("000000"); //Format the ID number (shows the 0s)
        return "\nPatron: " + name + "\nID Number: " + number.format(numID) + "\nBook 1: " + book[0] + "\nBook 2: " + book[1] + "\nBook 3: " + book[2] + "\n";
    }
}
