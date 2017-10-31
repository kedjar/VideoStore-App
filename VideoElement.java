// ------------------------------------------------------------------------
// VideoElement.java
//
// Author: M'Hand KEDJAR
// Date: February 13, 2012
// Last Modified: October, 31, 2017
// Course: Data Structures and Algorithms - Winter 2012 - CCCS-315-761
// Place: At home
// Description: An object VideoElement can be instantiated from this class
// Known Bugs: None
// ------------------------------------------------------------------------

public class VideoElement extends DataElement
{
      //Instance Variables
    private String videoTitle;  //variable to store the name of the movie
    private String movieStar1;  //variable to store the name of the star
    private String movieStar2;  //variable to store the name of the star
    private String movieProducer;   //variable to store the name of the producer
    private String movieDirector;   //variable to store the name of the director
    private String movieProductionCo;   //variable to store the name
                                        //of the production company
    private int copiesInStock;  //variable to store the number of copies in stock

    //default constructor       
    public VideoElement()
    {
        videoTitle = ""; movieStar1 = ""; movieStar2 = ""; movieProducer = "";
        movieDirector = ""; movieProductionCo = ""; copiesInStock = 0;
    }
    
    //constructor with parameters
    public VideoElement(String title, String star1, String star2, String producer,
                        String director, String productionCo, int setInStock)
    {
        videoTitle = title;
        movieStar1 = star1;
        movieStar2 = star2;
        movieProducer = producer;
        movieDirector = director;
        movieProductionCo = productionCo;
        copiesInStock = setInStock;
    }

    //Method to set the details of a video.   
    public void setVideoInfo(String title, String star1, String star2, 
                             String producer, String director, String productionCo,
                             int setInStock)
    {
        videoTitle = title; movieStar1 = star1;  movieStar2 = star2;
        movieProducer = producer; movieDirector = director; 
        movieProductionCo = productionCo; copiesInStock = setInStock;
    }

    //Method to check the number of copies in stock.    
    public int getNoOfCopiesInStock()
    {
        return copiesInStock;
    }

    //Method to check in a video.
    //Postcondition: The number of copies in stock is incremented by one.
    public void checkIn()
    {
        copiesInStock++;
    }

    //Method to rent a video.
    //Postcondition: If there is a video in stock, its number of
    //               copies in stock is decremented by one;
    //               otherwise, an appropriate message is printed.
    public void checkOut()
    {
        if(getNoOfCopiesInStock() > 0)
            copiesInStock--;
        else
            System.out.println("Currently out of stock.");
    }

    //Method to print the title of a movie.
    public void printTitle()
    {
        System.out.println("Video Title: " + videoTitle);
    }

    //Method to print the details of a video.
    public void printInfo()
    {
        System.out.println("Video Title: " + videoTitle);
        System.out.println("Stars: " + movieStar1 + " and "
                           + movieStar2);
        System.out.println("Producer: " + movieProducer);
        System.out.println("Director: " + movieDirector);
        System.out.println("Production Company: " + movieProductionCo);
        System.out.println("Copies in stock: " + copiesInStock);
        System.out.println("----------------------------------");
    }

   //Method to determine whether title is the same as the title of the video.
   //Postcondition: Returns the value true if title is the same as the title
   //               of the video, false otherwise.
   // We ignore case when comparing
    public boolean checkTitle(String title)
    {
        //return(videoTitle.compareTo(title) == 0);
        return(videoTitle.compareToIgnoreCase(title) == 0);
    }

    //Method to increment the number of copies in stock 
    public void updateInStock(int num)
    {
        copiesInStock += num;
    }

    //Method to set the number of copies in stock.            
    public void setCopiesInStock(int num)
    {
        copiesInStock = num;
    }

    //Method to return the title of the video
    public String getTitle()
    {
        return videoTitle;
    }   

    public boolean equals(DataElement otherElement)
    {
        VideoElement temp = (VideoElement) otherElement;
        return (videoTitle.compareTo(temp.videoTitle) == 0);
    }

    public int compareTo(DataElement otherElement)
    {
        VideoElement temp = (VideoElement) otherElement;
        //return (videoTitle.compareTo(temp.videoTitle));
        return (videoTitle.compareToIgnoreCase(temp.videoTitle));
        
    }

    public void makeCopy(DataElement otherElement)
    {
        VideoElement temp = (VideoElement) otherElement;

        videoTitle = temp.videoTitle;
        movieStar1 = temp.movieStar1;
        movieStar2 = temp.movieStar2;
        movieProducer = temp.movieProducer;
        movieDirector = temp.movieDirector;
        movieProductionCo = temp.movieProductionCo;
        copiesInStock = temp.copiesInStock;
    }

    public DataElement getCopy()
    {
        VideoElement temp = new VideoElement(videoTitle, movieStar1,
                                movieStar2, movieProducer, movieDirector,
                                movieProductionCo, copiesInStock);
        return temp;
    }
}

