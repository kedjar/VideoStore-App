// ------------------------------------------------------------------------
// mainProgram.java
//
// Author: M'Hand KEDJAR
// Date: February 13, 2012
// Last Modified: October, 31, 2017
// Course: Data Structures and Algorithms - Winter 2012 - CCCS-315-761
// Place: At home
// Description: This program implements a video store to keep track of its
//              videos and customers.
// Known Bugs: None
// ------------------------------------------------------------------------

import java.io.*;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class mainProgram 
{    
    static Scanner input = new Scanner(System.in) ;
    static BufferedReader keyboard = new
           BufferedReader(new InputStreamReader(System.in));
    
    /*
     * Method which returns an integer and throws an exception if another
     * type of variable is entered.
     */
    public static int userchoice(int choice) throws ArithmeticException{
        return choice;
    }
    
    public static void main(String[] args) 
    {
        CustomerList custList = new CustomerList(); // List of costumers
        VideoList  videoList = new VideoList();     // List of videos
        int choice=0;   // Variable to store the choice entered by a user                      
        String title;   // Variable to store the title of a video

        try
        {
            BufferedReader infile =
                new BufferedReader(new FileReader("c:\\videoDat.txt"));
            BufferedReader infileCust =
                new BufferedReader(new FileReader("c:\\custDat.txt"));            

            createVideoList(infile, videoList);
            createCustomerList(infileCust, custList);

            displayMenu();  // Displaying the main menu
            System.out.print("Please, enter your choice: ");
            boolean continueloop1 = true;   // Variable used to loop until the 
                                            // user entered an integer
            do{
            try
                {                    
                    choice = input.nextInt();//Store up the integer "choice" 
                                             //entered by the user from the keyboard
                    choice = userchoice(choice);
                    continueloop1 = false;
                }                           
            
                catch (InputMismatchException ime)
                {
                    System.err.printf( "\nException: %s\n",ime);
                    input.nextLine(); // discard input so user can try again                                              
                    System.out.println( "You must enter an integer."
                                   + " Please try again.\n" );
                }
            } while(continueloop1); // end do---while             
                                        
            System.out.println();

            while(choice != 9)
            {
                switch(choice)
                {
                case 1: //check whether the store own a particular video title                                                
                        System.out.print("Enter the title: ");
                        //title = input.next();                       
                        title = keyboard.readLine();                       
                        
                        if(videoList.videoSearch(title)){
                            System.out.println("*********************");
                            System.out.println("**  Title found!!  **");
                            System.out.println("*********************");
                        }
                        else{
                            System.out.println("Sorry, the requested video is "
                                    + "not in the store. ");
                            System.out.println("*****************************"
                                    + "***************** ");
                        }
                        break;
                case 2: // check out a video
                        System.out.print("Enter the title: ");
                        //title = input.next();
                        title = keyboard.readLine();
                        
                        if(videoList.videoSearch(title))
                        {//case 2, video title condition
                            int id = 0;
                            System.out.print("Enter your ID: ");
                            boolean continueloop4 = true;
                            do{
                            try
                                {                    
                                id = input.nextInt();//Store up the integer "choice" 
                                             //entered by the user from the keyboard
                                id = userchoice(id);
                                continueloop4 = false;
                                }                           
            
                            catch (InputMismatchException ime)
                                {
                                System.err.printf( "\nException: %s\n",ime);
                                input.nextLine(); // discard input so user can try again                                              
                                System.out.println( "You must enter an integer."
                                   + " Please try again.\n" );
                                }
                            } while(continueloop4); // end do---while
                            
                            if(videoList.isVideoAvailable(title))
                            {// case 2, video title available condition    
                                if(custList.custSearchId(id)){//case 2, costumer ID condition
                                   // Rent the video to the costumer
                                   videoList.videoCheckOut(title);
                                   // Update the information
                                   custList.custRentVideo(id,title);
                                   System.out.println("Thank you for renting the movie "
                                                + title);
                                   System.out.println("++***************************"
                                                + "*****++");                                   
                                }//end case 2, costumer ID condition
                                else{
                                    System.out.println("Sorry, your are not a "
                                            + "registered costumer in our store");
                                    }
                            }// case 2, end video title available condition 
                            else{
                                System.out.println("Sorry, the movie " +title
                                           + " is currently not in stock.");
                                System.out.println("--*************************"
                                           + "*************************-- ");
                            }
                        }  //end case 2, title condition
                        else{
                            System.out.println("Sorry, the movie " +title
                                           + " is not from our store.");
                            System.out.println("~~*************************"
                                           + "***********************~~");
                        }
                        break;
                case 3: // check in a video
                        System.out.print("Enter the title: ");
                        title = keyboard.readLine();
                        //title = input.next();
                        
                        if(videoList.videoSearch(title))
                        {// case 3, video title condition
                            int id = 0;
                            System.out.print("Enter your ID: ");
                            boolean continueloop5 = true;
                            do{
                            try
                                {                    
                                id = input.nextInt();//Store up the integer "choice" 
                                             //entered by the user from the keyboard
                                id = userchoice(id);
                                continueloop5 = false;
                                }                           
            
                            catch (InputMismatchException ime)
                                {
                                System.err.printf( "\nException: %s\n",ime);
                                input.nextLine(); // discard input so user can try again                                              
                                System.out.println( "You must enter an integer."
                                   + " Please try again.\n" );
                                }
                            } while(continueloop5); // end do---while
                            
                            if(custList.custSearchId(id))
                            {// case 3, customer ID conditon
                                //Check in the video
                                videoList.videoCheckIn(title);
                                // Update the information
                                custList.custReturnVideo(id, title);
                                System.out.println("You have returned "+title+""
                                           + " Thank you!"); 
                                System.out.println("!!*************************"
                                           + "************!!");
                            }//case 3, end customer ID condition
                            else{
                                System.out.println("Sorry, your are not a "
                                            + "registered costumer in our store");                                
                            }
                        }// case 3, end video title condtion
                        else{
                            System.out.println("Sorry, the movie " +title
                                           + " is not from our store");
                            System.out.println("||*************************"
                                           + "***********************||");
                        }
                        break;
                case 4: // Check whether a video is currently in store, 
                        // available for rent
                        System.out.print("Enter the title: ");
                        title = keyboard.readLine();
                        //title = input.next();
                        
                        if(videoList.videoSearch(title))
                        {
                            if(videoList.isVideoAvailable(title)){
                                System.out.println(title
                                          + " is currently in stock.");
                                System.out.println("[[*****"
                                           + "***********************]]");
                            }
                            else{
                                System.out.println(title 
                                           + " is currently out of stock.");
                                System.out.println("{{*****"
                                           + "****************************}}");
                            }
                        }
                        else{
                            System.out.println("Sorry, the movie " +title
                                           + " is not from our store");
                            System.out.println("<<*************************"
                                           + "**********************>>");    
                        }
                    break;
                case 5: // print the titles of all the videos in the store
                        System.out.println("The videos we have in the store are: ");
                        videoList.videoPrintTitle();
                        break;
                case 6: // print the info of all the videos in the store
                        System.out.println("The details of the videos we have "
                                + "in the store are: ");
                        videoList.print();
                        break;
                case 7: // Print the customer list
                       System.out.println("Costumers registered in store are: ");
                       custList.printCustList();
                       break;
                case 8:// Print the rented videos by each customer
                       System.out.println("The details of the rented videos are: ");
                       custList.rentedVideosInfo();
                       break;                    
                default: System.out.println("Invalid selection");
                }//end switch

                displayMenu();
                System.out.print("Enter your choice: ");
             boolean continueloop2 = true;
              do{
            try
                {                    
                    choice = input.nextInt();//Store up the integer "choice" 
                                             //entered by the user from the keyboard
                    choice = userchoice(choice);
                    continueloop2 = false;
                }                           
            
                catch (InputMismatchException ime)
                {
                    System.err.printf( "\nException: %s\n",ime);
                    input.nextLine(); // discard input so user can try again                                              
                    System.out.println( "You must enter an integer."
                                   + " Please try again.\n" );
                }
            } while(continueloop2); // end do---while
             
                System.out.println();
            }//end while
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println(fnfe.toString());
        }
        catch(IOException ioe)
        {
            System.out.println(ioe.toString());
        }
    }// end main method

    public static void createVideoList(BufferedReader infile,
                                       VideoList videoList)
                                       throws IOException
    {        
       LinkedList<String> tempVideo = new LinkedList<>(); //Linked List to store
                             // all the lines read from the file videoDat.txt
       String line = " ";    // Variable to store each line read from the file         
      
      while(line != null){
          line = infile.readLine(); //initialize 
          if(line != null){
            tempVideo.add(line);            
          }
      }
      VideoElement newVideo = new VideoElement();
      //setVideoInfo(String title, String star1, 
      //             String star2, String producer, 
      //             String director, String productionCo,
      //             int setInStock)
      for(int i = 0; i < tempVideo.size(); i = i+7){
          newVideo.setVideoInfo(tempVideo.get(i),tempVideo.get(i + 1),
                                tempVideo.get(i + 2),tempVideo.get(i + 3),
                                tempVideo.get(i + 4),tempVideo.get(i + 5),
                                Integer.parseInt(tempVideo.get(i + 6)));  
          videoList.insertFirst(newVideo);
      }      
    }//end createVideoList
    
    public static void createCustomerList(BufferedReader infile,
                                          CustomerList custList)
                                           throws IOException
    {
        LinkedList<String> tempCustomer = new LinkedList<>();
        String line =" ";
        String[] temp = new String[3];
        //... 
        while(line != null){
          line = infile.readLine(); //initialize 
          if(line != null){
            tempCustomer.add(line);            
          }
      }// end while
        CustElement newCustomer = new CustElement();        
        for(int i = 0; i < tempCustomer.size(); i++){
          temp = tempCustomer.get(i).split(" ");
          if(temp.length == 3){
          // setCustInfo(firstName, lastName, ID);
          newCustomer.setCustInfo(temp[0], temp[1],Integer.parseInt(temp[2])); 
          custList.insertFirst(newCustomer);
          }       
    } //end for loop
    } //end createCustList

    public static void displayMenu()
    {   System.out.println();
        System.out.println("Select one of the following: ");
        System.out.println("1: Check if the store owns a particular video title");
        System.out.println("2: Check out a video");
        System.out.println("3: Check in a video");
        System.out.println("4: Check if a particular video is in the store");
        System.out.println("5: Print the titles of all videos");
        System.out.println("6: Print a list of all the videos");
        System.out.println("7: Print a list of all the customers");
        System.out.println("8: Print out which customer has rented which video");
        System.out.println("9: Exit");
    }
}
