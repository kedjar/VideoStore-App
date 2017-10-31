// ------------------------------------------------------------------------
// CustElement.java
//
// Author: M'Hand KEDJAR
// Date: February 13, 2012
// Last Modified: October, 31, 2017
// Course: Data Structures and Algorithms - Winter 2012 - CCCS-315-761
// Place: At home
// Description: An object CustElement can be instantiated from this class
// Known Bugs: None
// ------------------------------------------------------------------------

public class CustElement extends DataElement
{
      //Instance Variables
    private String custFirstName;  //variable to store the costumer's first name
    private String custLastName;  //variable to store the costumer's last name 
   
    private int custID;  //variable to store the ID of a costumer
    //private LinkedList videoClient;

    //default constructor       
    public CustElement()
    {
        custFirstName = ""; custLastName = "";  custID = 0; //videoClient = null;
    }
    
    //constructor with parameters
    public CustElement(String nameFirst, String nameLast,  int ID)
    //public CustElement(String nameFirst, String nameLast,  int ID, LinkedList videos)   
    {
        custFirstName = nameFirst;
        custLastName = nameLast;
        custID = ID;         
    }

    //Method to set the details of a costumer.   
    public void setCustInfo(String nameFirst, String nameLast, int ID)
    {
        custFirstName = nameFirst; custLastName = nameLast;  custID = ID;
    }    

    public void makeCopy(DataElement otherElement)
    {
        CustElement temp = (CustElement) otherElement;

        custFirstName = temp.custFirstName;
        custLastName = temp.custLastName;        
        custID = temp.custID;
    }

    public DataElement getCopy()
    {
        CustElement temp = new CustElement(custFirstName, custLastName, custID);
        return temp;
    }
    
    public int compareTo(DataElement otherElement)
    {
        CustElement temp = (CustElement) otherElement;
        return (custID - temp.custID);
    }
    
    public boolean equals(DataElement otherElement)
    {
        CustElement temp = (CustElement) otherElement;
        return (custID - temp.custID == 0);
    }
    
     public boolean checkID(int ID)
    {
        return(custID - ID == 0);
    }
     
     /*
      * Method to print the details of a costumer.      
      */
    public void printInfo()
    {
        System.out.println("First Name: " + custFirstName);
        System.out.println("Last Name: " + custLastName );
        System.out.println("ID: " + custID);  
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        //System.out.println(custFirstName+"\t"+custLastName+"\t"+custID);
    }
    /*
     * Method to return the ID of a given costumer
     */
    public int getID(){
        return custID;
    }
}

