

package csci1011.assign6;

/**CSCI 1010 Assign 6
 * @author Benjamin Howard
 * Class to create VaccineAppointment objects with constructor, accessor, and toString methods
 */

public class VaccineAppointment {
    private String patientName;
    private String email;
    private String timeOfAppt;
    private boolean isAvailable;
    /* Contructs appointment based on appointment time,
       email and same set to default and are later gathered
       by the signUp() method
    */
     public VaccineAppointment(String newApptTime)
     {
         this.patientName = "no name";
         this.email = "no email";
         this.timeOfAppt = newApptTime;
         this.isAvailable = true;
     }
     /* Constructor to set new email and name for an appointment,
        Finalizes and makes the appointment not available for anyone else
     */
     public void signUp(String newPatientName, String newEmail)
     {
         this.patientName = newPatientName;
         this.email = newEmail;
         this.isAvailable = false;
     }
     // Returns time of Appt
     public String getTime()
     {
         return(this.timeOfAppt);
     }
     //Returns availability (Either true or false)
     public boolean getIsAvailable()
     {
         if(this.isAvailable == true)
         {
             return(true);
         }
         else if(this.isAvailable == false)
         {
             return(false);
         }
     return(this.isAvailable);   
    }
     /*Converts Appointment to a string for 
       so it can be written to log in a organized way
     */
     public String toString()
     { String time = this.timeOfAppt;
       String name = this.patientName;
       String email = this.email;
       String output = time + " - " + name +" (" +email +")";
       System.out.println(time + " - " + name +" (" +email +")");
       return(output);
     }


}
