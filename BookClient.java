package book;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class BookClient {
   BookRecord[] listOfBooks;
       int numOfRecords;
       int maxSize = 5;
      
       public BookClient() {
           listOfBooks = new BookRecord[5];
           this.numOfRecords = 0;
       }
      
       public void expandSize(int size) {
           BookRecord[] rec = new BookRecord[numOfRecords];
           rec = listOfBooks.clone();
           listOfBooks = new BookRecord[numOfRecords+size];
           for(int i=0;i<rec.length;i++) {
               listOfBooks[i] = rec[i];
           }
       }
      
       public static boolean isDuplicateRecord(BookRecord bookObj, BookRecord[] list) {
           for(BookRecord record : list) {
               if(record.equals(bookObj))
                   return true;
           }
           return false;
       }
      
	public static void main(String[] args) throws FileNotFoundException{
           Scanner sc = new Scanner(System.in);
          
           System.out.println("Enter Name of the File;");
           String fileName = sc.nextLine();
           
           System.out.println("Enter Expansion fctor;");
           int expSize = sc.nextInt();
          
           BookClient c1 = new BookClient();
          
           FileInputStream fis = new FileInputStream(fileName);
           boolean cont = true;
           while (cont) {
        	   try (ObjectInputStream input = new ObjectInputStream(fis)) {
        		   BookRecord obj = (BookRecord) input.readObject();
        		   if (obj != null) {
        			   if(!isDuplicateRecord(obj, c1.listOfBooks)) {
        				   c1.listOfBooks[c1.numOfRecords] = obj;
        				   c1.numOfRecords++;
        				   if(c1.numOfRecords == c1.maxSize) {
        					   System.out.println("Resized the array from " + c1.numOfRecords+" to " + (expSize + c1.numOfRecords));
        				   }
        			   }
        			   else {
        				   System.out.println("This object got skipped because it contains a duplicate entry");
        			   }
        		   }
        		   else {
        			   cont = false;
        		   }
        	   }
        	   catch(IOException | ClassNotFoundException ioe){
        		   System.out.println("The file can not be read");
        	   }
           }
          
           //User Interactive Part
          
           System.out.println("Select an option:");
           System.out.println("Type \"S\" to list books of genre");
           System.out.println("Type \"P\" to print out all the book records");
           System.out.println("Type \"Q\" to Quit");
          
           String option1 = sc.nextLine();
          
           switch (option1) {
           case "S":
               System.out.println("Type a genre. The genres are:");
               for(BookGenre g : BookGenre.values()) {
                   System.out.println(g);
               }
              
               String genOptions = sc.nextLine();
               //Print values as per genre
               for(BookRecord rec : c1.listOfBooks) {
                   if(rec.genre.equals(genOptions)) {
                   System.out.println(rec.toString());
                   }
               }
              
               break;
               
           case "P":
               System.out.println("All the records ::");
               for(BookRecord rec : c1.listOfBooks) {
                   System.out.println(rec.toString());
               }
               break;
              
           case "Q":
               System.exit(0);
               
           default:
               System.out.println("Wrong Option! Try Again");
               break;
           }   
          
       
           }
}