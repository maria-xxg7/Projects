/**
 * Maria Gharabaghi
 * November 30, 2022
 * This program allows the user to search through the library system (patrons, books, etc.)
 */

package gharabaghilibrary;

//Imports
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class GharabaghiLibrary {

    public static void main(String[] args) {
        Patron[] patron = readFile(); //Create an array of patrons that equal the patrons read in from the file (readFile())
        String showMenu; //String stores the user's input for the menu
        int option = 0; //Different options in the menu (1-4)

        JOptionPane.showMessageDialog(null, "Welcome to the Canadian Library of Parliament!"); //Show message to the user that welcomes them to the library system
        
        //While the options are not equal to 4 (exit), then keep prompting the user w/ the menu
        while (option != 4) {
            try { //Try to accept input from the user
                showMenu = JOptionPane.showInputDialog("Choose an option: \n1. Search by book \n2. Search by patron \n3. Search by author \n4. Exit"); //Show options
                option = Integer.parseInt(showMenu); //Parse the user input into an integer (can be used in the switch statement)
                menu(option, patron); //Call on the menu method
            } catch (NumberFormatException e) { //If the user enters an invalid option number, catch the error & then output an error message
                JOptionPane.showMessageDialog(null, "Error", "Please enter a valid option", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Method that reads the text file in
     * @return the patrons (in an array of Patrons)
     */
    public static Patron[] readFile() {
        Patron[] patron = null; //Create a new array of patrons
        Book[] B = null; //Create a new array of books
        //Patron & Book information
        String name, title, author; 
        int num, numID, pages; 

        try { //Try to scan the file
            File file = new File("src//gharabaghilibrary//patrons.txt"); //Pathway to the txt file
            Scanner scanner = new Scanner(file); //Create a scanner

            num = Integer.parseInt(scanner.nextLine()); //Scan the first line (get the number of patrons)
            patron = new Patron[num]; //Set the number of patrons to 4 (from the first line)
            B = new Book[3]; //Set the number of books to 3 (3 books per patron)

            for (int i = 0; i < patron.length; i++) { //Loop through all of the 4 patrons
                name = scanner.nextLine(); //Scan next line for the name of the patron
                numID = Integer.parseInt(scanner.nextLine()); //Scan the next line for the ID number of the patron

                for (int j = 0; j < B.length; j++) { //Loop through all of the 3 books for each of the patrons
                    title = scanner.nextLine(); //Scan the next line for the title of the book
                    author = scanner.nextLine(); //Scan the next line for the author of the book
                    pages = Integer.parseInt(scanner.nextLine()); //Scan the next line for the pages
                    B[j] = new Book(title, author, pages); //Create books
                    
                    //If any of the titles are empty, then set the book to null
                    if (title.equalsIgnoreCase("Empty")) {
                        B[j] = null;
                    } 
                }
                patron[i] = new Patron(name, numID, B[0], B[1], B[2]); //Create patrons
            }

        } catch (FileNotFoundException | NumberFormatException e) { //If there is an error with the file, catch the error and output an error message
            System.out.println("Error: " + e);
        }
        return patron; //Return the patrons
    }
    
    /**
     * Searches for the book titles & who is borrowing them
     * @param patron (used to access the books of the patrons as well as the patrons (check who is borrowing them)
     */
    public static void searchBook(Patron[] patron) {
        String searchBook; //String that stores the user's input
        String output = ""; //Output to the user
        boolean hasBorrowed = false; //Boolean for if the book has been borrowed

        searchBook = JOptionPane.showInputDialog("Please enter the title of the book you're looking for: "); //Prompt user for a title

        for (Patron patron1 : patron) { //Loop through all of the patrons to see if any of the patrons are currently borrowing the book
            if (patron1.hasBorrowed(searchBook) == true) { //If the user's inputted title and a title in the system match, then the book is being borrowed
                output += "\n" + patron1.getName(); //output the patron's name(s) who are borrowing the book
                hasBorrowed = true; //Set borrowed to true (book is being borrowed)
            }
        }

        if (hasBorrowed == true) { //If the book is being borrowed, then output the book title & who it is being borrowed by
            JOptionPane.showMessageDialog(null, "The book " + searchBook + " is borrowed by " + output);
        } else { //If there are no matches, then output that the book does not exist
            JOptionPane.showMessageDialog(null, "This book does not exist.");
        }
    }
    
    /**
     * Searches for the patron & if they exist
     * @param patron (use the patron array to access the names of the patrons)
     */
    public static void searchPatron(Patron[] patron) {
        String searchPatron; //String that stores the user's input (patron name)
        boolean exists = false; //Boolean for if the patron exists or not
        String numID; //Patron ID number

        searchPatron = JOptionPane.showInputDialog("Please enter the name of the patron you are looking for: "); //Prompt the user for the name of the patron they are searching for

        for (Patron patron1 : patron) { //Loop through all of the patrons to check if any of them match the one the user is searching for
            numID = String.valueOf(patron1.getID()); //Make the ID number into a string so it can check if the name matches or if the ID number matches (same statement)
            if (patron1.getName().equalsIgnoreCase(searchPatron) || numID.equalsIgnoreCase(searchPatron)) { //If name matches or the ID number matches, then output the patron & their information
                JOptionPane.showMessageDialog(null, patron1); //Message showing the patron's info
                exists = true; //Set exists to true (patron is found)
            }
        }

        if (exists == false) { //If the patron is not found, then output a message that the patron being searched for does not exist
            JOptionPane.showMessageDialog(null, "This patron does not exist.");
        }
    }
    
    /**
     * Searches for the author
     * @param patron (use the patron array to access the names of the authors (books borrowed by the patrons)
     */
    public static void searchAuthor(Patron[] patron) {
        String searchAuthor; //String that stores the user's input for the author's name
        String output = ""; //Output to the user
        
        boolean getAuthor = false; //Boolean for if the author was found

        searchAuthor = JOptionPane.showInputDialog("Please enter the author you're looking for: "); //Output a message to the user asking for the author's name

        for (int i = 0; i < patron.length; i++) { //Loop through the patrons (search to see if any of the authors match the user's input
            if (patron[i].getAuthor(searchAuthor) == true) { //If the user's author matches an author in the system, then output that the author exists, and a book recommendation
                output += patron[i].checkAuthor(searchAuthor);
                getAuthor = true; //Set getAuthor to true (author exists)
            }
        }

        if (getAuthor == true) { //If the author is found, then output that the author exists, and a book suggestion
            JOptionPane.showMessageDialog(null, "The author " + searchAuthor + " exists. \nIf you liked this author, then you should try '" + output + "'");

        } else { //If the author is not found, then output that the author does not exist
            JOptionPane.showMessageDialog(null, "This author does not exist.");
        }
    }
    
    /**
     * Shows the menu to the user & allows them to choose a function
     * @param option - correlates with the different options in the menu
     * @param patron - uses the patron array to call on the methods (use the patron array)
     */
    public static void menu(int option, Patron[] patron) {
        switch (option) { //Switch statement (4 different options)
            case 1: //Allows the user to search for a book & who is borrowing it
                searchBook(patron); //Call on the searchBook method
                break;
            case 2: //Alows the user to search for a patron in the system
                searchPatron(patron); //Call on the searchPatron method
                break;
            case 3: //Allows the user to search for an author in the system
                searchAuthor(patron); //Call on the searchAuthor method
                break;
            case 4: //Allows the user to exit 
                JOptionPane.showMessageDialog(null, "Goodbye!"); //Output a goodbye message to the user
                break;
        }
    }
}
