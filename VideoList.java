// ------------------------------------------------------------------------
// VideoList.java
//
// Author: M'Hand KEDJAR
// Date: February 13, 2012
// Last Modified: October, 31, 2017
// Course: Data Structures and Algorithms - Winter 2012 - CCCS-315-761
// Place: At home
// Description: This class maintains a list of all the videos in the store.
// Known Bugs: None
// ------------------------------------------------------------------------
public class VideoList
{
    protected class LinkedListNode
    {
        DataElement info;
        LinkedListNode link;
    }
    protected LinkedListNode first; //Address of the first node of the list
    protected LinkedListNode last;  //Address of the last node of the list
    protected int count;            //Number of nodes in the list    
    
    //default constructor       
    public VideoList()
    {
        first = null;   // Initilization of the list
        last = null;
        count = 0;        
    }
    
     /*
    * Method to search the list to see whether a particular video, 
    * specified by the parameter title, is in the store.
    * This method returns true if the title is found; false otherwise.
    */
    public boolean videoSearch(String title)
    {
          LinkedListNode location;
          location = searchVideoList(title);
          return (location != null);
    }
    
    /*Method to determine whether the video specified by the parameter title 
    * is available. This method returns true if at least one copy of the video 
    * is available, false otherwise.  
    */  
    public boolean isVideoAvailable(String title)
    {
        LinkedListNode location;
        VideoElement temp;

        location = searchVideoList(title);

          if(location != null)
        {
            temp = (VideoElement) location.info;
              return (temp.getNoOfCopiesInStock() > 0);
        }
          else
              return false;
    }

    /*
     * Method to check in a video returned by a customer. If the video 
     * returned is from the store, then update the internal data of the 
     * video; otherwise, an appropriate message is printed.
     */
    public void videoCheckIn(String title)
    {
        LinkedListNode location;
        VideoElement temp;

        location = searchVideoList(title);  //search the list

        if(location != null)
        {
            temp = (VideoElement) location.info;
            temp.checkIn();
         }
        else
            System.out.println("The store does not carry "
                             + "this video.");
    }
    
    /*
     * Method to check out a video, that is, rent a video. If the video 
     * is available, then update the internal data of the video; otherwise,
     * an appropriate message is printed
     */        
    public void videoCheckOut(String title)
    {
        LinkedListNode location;
        VideoElement temp;

        location = searchVideoList(title);  //search the list

        if(location != null)
        {
            temp = (VideoElement) location.info;
            temp.checkOut();
        }
        else
            System.out.println("The store does not carry "
                             + "this video.");
    }

    /*
     * Method to determine whether the video specified by the  parameter 
     * title is in the store. Returns true if the video is in the store, 
     * false otherwise.
     */
    public boolean videoCheckTitle(String title)
    {
        LinkedListNode location;
        location = searchVideoList(title); //search the list
        return (location != null);
    }

    /*
     * Method to update the number of copies of a video.
     */        
    public void videoUpdateInStock(String title, int num)
    {
        LinkedListNode location;
        VideoElement temp;

        location = searchVideoList(title); //search the list

        if(location != null)
        {
            temp = (VideoElement) location.info;
            temp.updateInStock(num);
        }
        else
            System.out.println("The store does not carry this video.");
    }
        
    /*
     * Method to print the titles of all the videos in the store.
     */
    public void videoPrintTitle()
    {
        LinkedListNode current;
        VideoElement temp;

        current = first;
        while(current != null)
        {
            temp = (VideoElement) current.info;
            temp.printTitle();
            current = current.link;
        }
    }

    /*
     * Method to print the info of all the videos in the store.
     */
    public void print()
    {
        LinkedListNode current; //pointer to traverse the list
        VideoElement temp;

        current = first;    //set current so that it points to the first node
        while(current != null) //while more data to print
        {
            temp = (VideoElement) current.info;
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
     * Method to search for a particular title in the video list.
     * It returns the reference to the node containing the video, 
     * or the null reference otherwise.
     */
    private LinkedListNode searchVideoList(String title)
    {
        boolean found = false;   //set found to false
        LinkedListNode current = null;
        VideoElement temp = new VideoElement();

        if(first == null)  //the list is empty
            System.out.println("Cannot search an empty list. ");
        else
        {
            current = first;  //set current to point to first node in the list
            found = false;    //set found to false

            while(current != null && !found)       //search the list
            {

                temp = (VideoElement) current.info;

                if(temp.checkTitle(title)) //item is found
                    found = true;
                else
                     current = current.link; //advance current to the next node
            }
        }//end else

        return current;
    }//end searchVideoList
    
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
