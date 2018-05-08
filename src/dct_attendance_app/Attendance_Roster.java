package dct_attendance_app;

import org.jsoup.nodes.Node;

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
    private final SimpleDoubleProperty percentAttended;
    private final SimpleStringProperty sentLevel;
    //private final SimpleBooleanProperty isSent;

    public Attendance_Roster(String name, String hoursAttended, String hoursMissed, Double percentAttended, String sentLevel) {
        this.name = new SimpleStringProperty(name); 
        this.hoursAttended = new SimpleStringProperty(hoursAttended);
        this.hoursMissed = new SimpleStringProperty(hoursMissed);
        this.percentAttended = new SimpleDoubleProperty(percentAttended);
        this.sentLevel = new SimpleStringProperty(sentLevel);
    }

    
    
    public void setSentLevel(String sentL) {
        sentLevel.set(sentL);
    }

    public String getSentLevel() {
        return sentLevel.get();
    }
    
    public StringProperty sentProperty() {
        return sentLevel;
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

    
    
    public void setPercentAttended(double pAttended) {
        percentAttended.set(pAttended);
    }

    public Double getPercentAttended() {
        return percentAttended.get();
    }
    
    public DoubleProperty percentAttendedProperty() {
        return percentAttended;
    }



}
