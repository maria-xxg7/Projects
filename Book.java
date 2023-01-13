/**
 * Maria Gharabaghi
 * December 1, 2021
 * This class creates books, and allows the user to access information about the books (title, author, number of pages)
 */

package gharabaghilibrary;

public class Book {
    //Attributes
    private String title; //Title of the book
    private String author; //Author of the book
    private int pages; //Number of pages in the book
    private Patron patron1; //Create the patron (borrowing the book)
    private boolean hasBorrowed; //Boolean for if the book has been borrowed
    
    /**
     * Primary Constructor
     * @param title - title of the book
     * @param author - author of the book
     * @param pages - number of pages in the book
     */
    public Book(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }
    
    /**
     * Secondary Constructor
     * @param title - title of the book
     * @param author - author of the book
     * @param pages - number of pages in the book
     * @param patron1 - the patron borrowing the book
     * @param hasBorrowed - if the book has been borrowed or not
     */
    public Book (String title, String author, int pages, Patron patron1, boolean hasBorrowed) {
        this(title, author, pages); //Call on the primary constructor
        this.patron1 = patron1;
        this.hasBorrowed = hasBorrowed;
    }
    
    /**
     * Accessor: Getting the title of the book
     * @return title of the book
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Accessor: Getting the author of the book
     * @return author of the book
     */
    public String getAuthor() {
        return author;
    }
    
    /**
     * Accessor: Getting the number of pages in the book
     * @return number of pages
     */
    public int getPages() {
        return pages;
    }
    
    //No Mutators for any of the accessors (book already exists and cannot be altered)
    
    /**
     * Java toString() method 
     * @return the information about the books
     */
    public String toString() {
        return "\nTitle: " + title + "\nAuthor: " + author + "\nPages: " + pages;
    }
}

