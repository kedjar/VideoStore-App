// ------------------------------------------------------------------------
// CustomerList.java
//
// Author: M'Hand KEDJAR
// Date: February 13, 2012
// Last Modified: October, 31, 2017
// Course: Data Structures and Algorithms - Winter 2012 - CCCS-315-761
// Place: At home
// Description: This class maintains a list of all costumers of the store.
// Known Bugs: None
// ------------------------------------------------------------------------

import java.util.LinkedList;

public class CustomerList
{
    protected class LinkedListNode
    {
        DataElement info;
        LinkedListNode link;
    }
    
    protected LinkedListNode first; //Address of the first node of the list
    protected LinkedListNode last;  //Address of the last node of the list
    protected int count;            //Number of nodes in the list
   
    //To store the rented videos
    protected LinkedList<String> rentedVideoList = new LinkedList<>();
    //To store customer who rented a video
    protected LinkedList<Integer> custRentVideoList = new LinkedList<>();
    //To store the returned videos
    protected LinkedList<String> returnedVideoList = new LinkedList<>();
    //To store customer who returned a video
    protected LinkedList<Integer> custReturnVideoList = new LinkedList<>();
    
    //default constructor       
    public CustomerList()
    {
        first = null;   // Initilization of the list
        last = null;
        count = 0;       
    }    
    /*
     *Method to check whether the given id is one of the current costumers.
     *Returns true if found, false otherwise. 
     */
    public boolean custSearchId(int id)
    {
          LinkedListNode location;
          location = searchCustomerList(id);
          return (location != null);
    } 
    /*
     * Method to update the information when a costumer returns a video 
     */
    public void custReturnVideo(int id, String title){
        if(custSearchId(id)){
            returnedVideoList.add(title);
            custReturnVideoList.add(id); 
            for(int i = 0; i < custRentVideoList.size() ; i++){
                if(custRentVideoList.get(i) == id && 
                        rentedVideoList.get(i).equalsIgnoreCase(title)){
                    custRentVideoList.remove(i);
                    rentedVideoList.remove(i);
                    continue;
                }
            }
        }
        else{
            System.out.println("Sorry, you're not a costumer from our store");
        }
             
    }
    
    /*
     * Method to update the information when a costumer rents a video     
     */
    public void custRentVideo(int id, String title){
        if(custSearchId(id)){
            rentedVideoList.add(title);
            custRentVideoList.add(id);   
            
        }
        else{
            System.out.println("Sorry, you're not a costumer from our store");
        }
            
    }
  
    /*
     * Method to determin the number of videos currently rented by the costumer     
     */
    public int custGetNoOfrentals(int id){
        int count = 0;
        for(int i = 0; i < custRentVideoList.size(); i++){
            if(custRentVideoList.get(i) != null 
                    && custRentVideoList.get(i) == id ){
                count++;
            }
        }
        return count;
    }
    
    /*
     * Method to printl all the videos rented by each costumer     
     */
    public void rentedVideosInfo(){
        LinkedListNode current; //pointer to traverse the list
        CustElement temp;

        current = first;    //set current so that it points to
                            //the first node
        while(current != null) //while more data to print
        {
            
            temp = (CustElement) current.info;
            int id = temp.getID();
            if(custGetNoOfrentals(id) > 0){
               System.out.println("Customer ID: "+id+", Videos rented are: ");
               printVideosRented(id);
            }
            current = current.link;        
        }        
    }
    /*
     * Method to printl all the videos rented by a given costumer     
     */    
    public void printVideosRented(int id){
        
        for(int i = 0; i < custRentVideoList.size(); i++){
            if(custRentVideoList.get(i) != null 
                    && custRentVideoList.get(i) == id ){
                System.out.println(rentedVideoList.get(i));
            }
        }        
    }
    
    /*
     * Method to print the information of all costumers    
     */
    public void printCustList(){
        LinkedListNode current; //pointer to traverse the list
        CustElement temp;
        //System.out.println("First Name \t Last Name \t ID");
        current = first;    //set current so that it points to the first node
        while(current != null) //while more data to print
        {
            temp = (CustElement) current.info;
            temp.printInfo();
            current = current.link;        
        }
    }    
  
    //************************************************************************//
    //****************Implementing methods for LinkedList*********************//
    //************************************************************************//
    /*
     * Method to determine whether the list is empty.
     */
    public boolean isEmptyList()
    {
        if(first == null){return true;}
        else{return false;}       
    }

    /*
     * Method to initialize the list to an empty state.
     */
    public void initializeList()
    {
        first = null;
        last = null;
        count = 0;
    } 
    /*
     * Method to search for a particular ID in the Customer list.
     * It returns the reference to the node containing the Customer, 
     * or the null reference otherwise.
     */
    private LinkedListNode searchCustomerList(int ID)
    {
        boolean found = false;   //set found to false
        LinkedListNode current = null;
        CustElement temp = new CustElement();

        if(first == null)  //the list is empty
            System.out.println("Cannot search an empty list. ");
        else
        {
            current = first;  //set current to point to first node in the list
            found = false;    //set found to false

            while(current != null && !found)       //search the list
            {

                temp = (CustElement) current.info;

                if(temp.checkID(ID)) //item is found
                    found = true;
                else
                     current = current.link; //advance current to the next node
            }
        }//end else

        return current;
    }//end searchCustomerList
    
  /*
   * Method to insert new item at the beginning of the list.
   */
    public void insertFirst(DataElement newItem)
    {
        LinkedListNode newNode;     //variable to create the new node

        newNode = new LinkedListNode();     //create the new node
        newNode.info = newItem.getCopy();   //assign a copy of
                                            //newItem to the node
        newNode.link = first;   //insert newNode before first
        first = newNode;        //make first point to the
                                //actual first node

        if(last == null)        //if the list was empty, newNode is
                                //also the last node in the list
            last = newNode;

        count++;
    }

   /*
   * Method to insert new item at the beginning of the list.
   */       
    public void insertLast(DataElement newItem)
    {
        LinkedListNode newNode; //variable to create the new node

        newNode = new LinkedListNode();     //create the new node
        newNode.info = newItem.getCopy();   //assign a copy of
                                            //newItem to the node
        newNode.link = null;         //set the link field of
                                    //newNode to null

        if(first == null)   //if the list is empty, newNode is
                            //both the first and last node
        {
            first = newNode;
            last = newNode;
        }
        else     //if the list is not empty, insert newNode after last
        {
            last.link = newNode; //insert newNode after last
            last = newNode;     //set last to point to the actual last node
        }

        count++;
    }//end insertLast
    
    /*
     * Method to delete an item from the list
     */
    
    public void deleteNode(DataElement deleteItem){
         LinkedListNode current; //variable to traverse the list
        LinkedListNode trailCurrent; //variable just before current
        boolean found;

        if(first == null)    //Case 1; the list is empty
           System.err.println("Cannot delete from an empty list.");
        else
        {
           if(first.info.equals(deleteItem)) //Case 2
           {
              first = first.link;

              if(first == null)    //the list had only one node
                 last = null;
              count--;
           }
           else  //search the list for the node with the given info
           {
              found = false;
              trailCurrent = first;   //set trailCurrent to point to
                                      //the first node
              current = first.link;   //set current to point to the
                                      //second node

              while(current != null && !found)
              {
                  if(current.info.equals(deleteItem))
                     found = true;
                  else
                  {
                      trailCurrent = current;
                      current = current.link;
                  }
              }//end while

              if(found) //Case 3; if found, delete the node
              {
                  count--;
                  trailCurrent.link = current.link;

                  if(last == current)   //node to be deleted was the last node
                     last = trailCurrent;  //update the value of last
              }
              else
                 System.out.println("Item to be deleted is "
                                  + "not in the list.");
           }//end else
        }//end else
    }
}
