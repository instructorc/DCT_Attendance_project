package dct_attendance_app;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Attendance_Roster {
    
    private final SimpleStringProperty name;
    private final SimpleStringProperty hoursAttended;
    private final SimpleStringProperty hoursMissed;
    private final SimpleStringProperty percentAttended;
    //private final SimpleBooleanProperty isSent;
    
    Attendance_Roster(String name, String hoursAttended, String hoursMissed, String percentAttended) {
        this.name = new SimpleStringProperty(name); 
        this.hoursAttended = new SimpleStringProperty(hoursAttended);
        this.hoursMissed = new SimpleStringProperty(hoursMissed);
        this.percentAttended = new SimpleStringProperty(percentAttended);
        

        
    }
    
    public void setName(String studentName) {
        name.set(studentName);
    }

    public String getName() {
        return name.get();
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
    
    
    
    
    
    public void setHoursAttended(String hAttended) {
        hoursAttended.set(hAttended);
    }

    public String getHoursAttended() {
        return hoursAttended.get();
    }
    
    public StringProperty hoursAttendedProperty() {
        return hoursAttended;
    }
    
    
    
    
    public void setHoursMissed(String hMissed) {
        hoursMissed.set(hMissed);
    }

    public String getHoursMissed() {
        return hoursMissed.get();
    }
    
    public StringProperty hoursMissedProperty() {
        return hoursMissed;
    }

    
    
    public void setPercentAttended(String pAttended) {
        percentAttended.set(pAttended);
    }

    public String getPercentAttended() {
        return percentAttended.get();
    }
    
    public StringProperty percentAttendedProperty() {
        return percentAttended;
    }
    
    
    
    
   /* public void setIsSent(boolean isEmailSent) {
        isSent.set(isEmailSent);
    }

    public Boolean getIsSent() {
        return isSent.get();
    }
    
    public BooleanProperty isSentProperty() {
        return isSent;
    }*/
    
    

    
    

}
