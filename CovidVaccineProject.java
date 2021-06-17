
package csci1011.assign6;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;

/**CSCI 1010 Assign 6
 * @author {Benjamin Howard}
 * Program that lets you sign up for covid vaccines and logs user input
 */

public class CovidVaccineProject {
   
    //Asks user for date and createsLog for appointments on that date
    private static void createLogFile(String fileName)
    {
        PrintWriter outputStream = null;
        try
        {
            Scanner keyboard = new Scanner(System.in);
            outputStream = new PrintWriter(fileName);
            System.out.println("Please enter a date for Vaccine Scheduling");
            String date = keyboard.nextLine();
            outputStream.println("Appointments for " +date);
            outputStream.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error: File not found");
            System.exit(0);
        }
    }
    //Displays the Menu Options
    public static void displayMenu()
    {
        System.out.println();
        System.out.println("Please choose one of the following:");
        System.out.println("1. Make an appointment");
        System.out.println("2. Display appointment log");
        System.out.println("3. Exit the program");    
    }
    /* Displays the available appointments and timeslots for the user,
       If user tries to pick an appointment that is not available or out of timeslot bounds, error message prints
       and they have to pick another slot. Gets user input for name and email and writes the appointment information
       to the log.
    */
    private static void makeAppointment(VaccineSchedule schedule, String fileName )
    {
      Scanner keyboard = new Scanner(System.in);
      System.out.println();
      schedule.displayAvailability();
      VaccineAppointment vaccine = null;
      boolean correctUserChoice = true;
      do
      {   correctUserChoice = true;
          System.out.println("Please choose a time slot");
          int timeslot = keyboard.nextInt();
          if((timeslot > 8) || timeslot < 1)
          {
              System.out.println("Incorrect Choice Try again");
              correctUserChoice = false;
              continue;
          }
          vaccine = schedule.getAppointment(timeslot);
          boolean isAvailable = vaccine.getIsAvailable();
          keyboard.nextLine();
          if(isAvailable == false)
          {
              System.out.println("Sorry Appointment is not available. Pick a different time");
              correctUserChoice = false;
          }
      }while(correctUserChoice == false);
      System.out.println("Please Enter your first and last name");
      String patientName = keyboard.nextLine();
      System.out.println();
      System.out.println("Please enter your email address");
      String email = keyboard.nextLine();
      vaccine.signUp(patientName, email);
      logAppointment(vaccine, fileName);
    }
      //Opens the log file for appending and adds the vaccine appointment information to log
      private static void logAppointment(VaccineAppointment vaccine, String fileName)
      {   PrintWriter outputStream = null;
          try
          {
              outputStream = new PrintWriter(new FileOutputStream(fileName, true));
              outputStream.println(vaccine.toString());
              outputStream.close();
          }
          catch(FileNotFoundException e)
          {
              System.out.println("Error File not found");
              System.exit(0);
          }
     }
     //Displays the log Based on the chronological order that appointments were put in
    private static void displayLogFile(String fileName)
    {
        Scanner inputStream = null;
        try
        {
           inputStream = new Scanner(new File(fileName));
           while(inputStream.hasNextLine())
           {
               String line = inputStream.nextLine();
               if(line.isBlank())
               {
                   continue;
               }
               System.out.println(line);
           }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error File not found");
            System.exit(0);
        }
        
    }
    //Test program
    public static void main(String[] args) 
    {
      Scanner keyboard = new Scanner(System.in);
      boolean keepGoing = true;
      VaccineSchedule schedule = new VaccineSchedule(8,16);
      String fileName = "appointments.txt";
      createLogFile(fileName);
      System.out.println("Welcome to Benjamin Howard's Covid-19 Vaccine appointment Scheduler");
      do{
          displayMenu();
          int userChoice = keyboard.nextInt();
          switch (userChoice)
          {
              case 1: 
                        makeAppointment(schedule, fileName);
              break;
              case 2: 
                        displayLogFile(fileName);
              break;
              case 3:   
                        System.out.println("Thank you for using Benjamin Howard's Covid Vaccine scheduler.");
                        System.out.println("We hope to see you soon");
                        keepGoing = false;
                        System.exit(0);
              break;
              default: 
                        System.out.println("Invalid Choice");
              break;
          }
          
      }while(keepGoing == true);
    }
}



