

package csci1011.assign6;

/**CSCI 1010 Assign 6
 * @author Benjamin Howard
 * Class to handle VaccineAppointment objects and give methods for scheduling Appointments
 */
public class VaccineSchedule {
    private int firstHour;
    private VaccineAppointment[] allAppointments;
    
    /*Creates a schedule object with slots an hour apart based on the difference between endHour and startHour
    To use enter hour in 24 hour format EX: 2:00PM = 14
    */
    public VaccineSchedule(int startHour, int endHour)
    {
        if((startHour >= endHour) || (startHour < 8) || (endHour > 20))
        {
            System.out.println("Error times are not in correct format");
            System.exit(0);
        }
        this.firstHour = startHour;
        this.allAppointments = new VaccineAppointment[endHour - startHour];
        this.fillSlots();
    }
    
    /*Used to fill appointment slots an hour apart from the start hour to end hour. Converts 
    24Hr time format to 12 hour format and decides if its AM/PM*/
    private void fillSlots()
    {  int startHour = this.firstHour;
       int amPmHelp = 1;
       for(int i = 0; i < allAppointments.length; i++, startHour++)
        {
            if(startHour < 12)
            {
                 String hr = String.valueOf(startHour) +":00" + " AM";
                 this.allAppointments[i] = new VaccineAppointment(hr);
            }
            else if(startHour == 12)
            {
                String hr = String.valueOf(startHour) +":00" + " PM";
                this.allAppointments[i] = new VaccineAppointment(hr);
            }
            else if (startHour > 12)
            {
              String hr = String.valueOf(amPmHelp)+":00" + " PM";
              this.allAppointments[i] = new VaccineAppointment(hr);
              amPmHelp++;
            }    
        }
       
    }
    
    /* Returns the appointment an assigned time slot. 
     Time slot has to be greater than 0 and less than the maximum number of appointments
    */
    public VaccineAppointment getAppointment(int timeSlot)
    {
        if((timeSlot < 1) || (timeSlot > allAppointments.length))
        {
            return(null);
        }else
        {
           return(this.allAppointments[timeSlot-1]);
        }
    }
    /* Displays the current availability, if an appointment has already been
    taken for the given timeslot it will return "Not Available"
    */
    public void displayAvailability()
    {
       for(int i = 0, index = 1; i < this.allAppointments.length; i++, index++ ) 
       {
           VaccineAppointment appointment = this.allAppointments[i];
           boolean isAvailable = appointment.getIsAvailable();
           if(isAvailable == true)
           {
               System.out.println(index + "." + " " + appointment.getTime() + " - Available");
           }else
           {
               System.out.println(index + ". " + appointment.getTime() + " - Not Available");
           }
            
       }
    }
}
