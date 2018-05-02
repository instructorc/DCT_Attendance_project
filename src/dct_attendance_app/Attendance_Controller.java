package dct_attendance_app;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Attendance_Controller {



    @FXML
    private Button btn_display;

    @FXML
    private TableView<Attendance_Roster> five_percent_table;
    
	    @FXML
	    private TableColumn<Attendance_Roster, String> table_col_name_five;
	
	    @FXML
	    private TableColumn<Attendance_Roster, String> table_col_hoursMissed_five;
	
	    @FXML
	    private TableColumn<Attendance_Roster, Number> table_col_percentAttended_five;

    @FXML
    private TableView<Attendance_Roster> ten_percent_table;
    
	    @FXML
	    private TableColumn<Attendance_Roster, String> table_col_name_ten;
	
	    @FXML
	    private TableColumn<Attendance_Roster, String> table_col_hoursMissed_ten;
	
	    @FXML
	    private TableColumn<Attendance_Roster, Double> table_col_percentAttended_ten;

    @FXML
    private TableView<Attendance_Roster> fifteen_percent_table;
    
	    @FXML
	    private TableColumn<Attendance_Roster, String> table_col_name_fifteen;
	
	    @FXML
	    private TableColumn<Attendance_Roster, String> table_col_hoursMissed_fifteen;
	
	    @FXML
	    private TableColumn<Attendance_Roster, Double> table_col_percentAttended_fifteen;
    
    @FXML
    private TableView<Attendance_Roster> email_table;
	    @FXML
	    private TableColumn<Attendance_Roster, String> table_col_name_email;
	
	    @FXML
	    private TableColumn<Attendance_Roster, String> table_col_hoursAttended_email;
	
	    @FXML
	    private TableColumn<Attendance_Roster, String> table_col_hoursMissed_email;
	
	    @FXML
	    private TableColumn<Attendance_Roster, Number> table_col_percentAttended_email;

    @FXML
    private TableView<Attendance_Roster> all_students_table;
	    @FXML
	    private TableColumn<Attendance_Roster,String> table_col_name;
	
	    @FXML
	    private TableColumn<Attendance_Roster,String> table_col_hoursAttended;
	
	    @FXML
	    private TableColumn<Attendance_Roster,String> table_col_hoursMissed;
	
	    @FXML
	    private TableColumn<Attendance_Roster,Number> table_col_percentAttended;

    @FXML
    private ComboBox<String> dropdown_coursenames;

    @FXML
    private ComboBox<String> dropdown_section;

    @FXML
    private ComboBox<String> dropdown_year;

    @FXML
    private ComboBox<String> dropdown_term;

    @FXML
    private Label instructor_name;
    
    @FXML
    private Label label_total_student_count;

    @FXML
    private Label label_five_count;

    @FXML
    private Label label_ten_count;

    @FXML
    private Label label_fifteen_count;
    
    @FXML
    private Button send_email;
    
    @FXML
    void displayCourse(ActionEvent event) throws IOException {
    	//Declaring variables
    	String name, hoursAttended, hoursMissed,percentAttended;
    	ObservableList<Attendance_Roster> studentList = FXCollections.observableArrayList();
    	ObservableList<Attendance_Roster> five_list = FXCollections.observableArrayList();
    	

    	
    	
    	
        // Initialize the Attendance Roster table 4 columns.
    	table_col_name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    	table_col_hoursAttended.setCellValueFactory(cellData -> cellData.getValue().hoursAttendedProperty());        
    	table_col_hoursMissed.setCellValueFactory(cellData -> cellData.getValue().hoursMissedProperty());
    	table_col_percentAttended.setCellValueFactory(cellData -> cellData.getValue().percentAttendedProperty());
    	

    	String alteredCourseName = dropdown_coursenames.getValue().substring(0, 8);
    	System.out.println(alteredCourseName);
    	
    	SingleSelectionModel<String> course = dropdown_coursenames.getSelectionModel();
    	//System.out.println(course.getSelectedItem());
    	
    	Document doc = Jsoup.connect("https://staff.dunwoody.edu/x/IIS/Attendance/CourseAttendance.aspx?event_id=" +alteredCourseName+"&section_id="+ dropdown_section.getValue()+"&year="+dropdown_year.getValue()+ "&term="+ dropdown_term.getValue()).get();

		
		Elements AttendanceInfo = doc.select("tbody").first().children();
		//System.out.println(AttendanceInfo.text());

		for(Element row : AttendanceInfo.subList(3,  AttendanceInfo.size())) {
			name = row.child(0).text();
			hoursAttended = row.child(1).text();
			hoursMissed = row.child(2).text();
			percentAttended = row.child(3).text();
			String ap = percentAttended.replace("%","");
			if(ap.equalsIgnoreCase("Withdrawn") || ap.equalsIgnoreCase("Dropped")) {
				ap = "0";
			}
			double percentStripped = Double.parseDouble(ap);
			
			System.out.println(percentStripped);
			 studentList.add(new Attendance_Roster(name,hoursAttended, hoursMissed, percentStripped));
			 
			 all_students_table.setItems(studentList);
			 System.out.println(studentList.size());
			 double value = studentList.size();
			 String aValue = Double.toString(value);
			 label_total_student_count.setText(aValue);
			
		} 
		
		studentList.forEach(x -> {
			if(x.getPercentAttended() <= 72.00) {
				five_list.add(x);
				
				
			}else if(x.getPercentAttended() <= 87.00) {
				ten_percent_table.setItems(studentList);
			}else {
				five_percent_table.setItems(studentList);
			}
		});
		fifteen_percent_table.setItems(five_list);
		
		 // Initialize the Attendance Roster table 4 columns.
    	table_col_name_five.setCellValueFactory(cellData -> cellData.getValue().nameProperty());        
    	table_col_hoursMissed_five.setCellValueFactory(cellData -> cellData.getValue().hoursMissedProperty());
    	table_col_percentAttended_five.setCellValueFactory(cellData -> cellData.getValue().percentAttendedProperty());

	
    }

	public void initialize() throws IOException {
		
		// TODO Auto-generated method stub
		//Gets the instructor id and sets the faculty name
		Document faculty_overview_doc = Jsoup.connect("https://staff.dunwoody.edu/x/IIS/Attendance/FacultyAttendance.aspx").get();
		Elements facultyInfo = faculty_overview_doc.getElementsByAttributeValue("selected", "selected");
		System.out.println(facultyInfo.get(2).val());
		instructor_name.setText(facultyInfo.get(2).text());
		
		//Gets all of the course names
		Document faculty_courses = Jsoup.connect("https://staff.dunwoody.edu/x/IIS/Attendance/FacultyAttendance.aspx?people_id=" + facultyInfo.get(2).val()).get();
		Elements courseNames = faculty_courses.getElementsByAttributeValueContaining("href", "./CourseAttendance.aspx?event_id");
		courseNames.forEach(x->System.out.println(x.text()));
		dropdown_coursenames.getItems().removeAll(dropdown_coursenames.getItems());
		dropdown_coursenames.getItems().addAll(courseNames.text().split(" "));
		dropdown_coursenames.getSelectionModel().select(courseNames.text().split(" ")[0]);
		
		
		//Gets all the available terms
		Elements terms = faculty_overview_doc.getElementsByAttributeValue("id", "ContentPlaceHolder1_TermDropDownList").first().children();
		Element selectedTerm = faculty_overview_doc.getElementsByAttributeValue("selected", "selected").get(1);
		dropdown_term.getItems().removeAll(dropdown_term.getItems());
		for(Element i : terms) {
			dropdown_term.getItems().add(i.text());
		}
		dropdown_term.getSelectionModel().select(selectedTerm.text());
		
		
		//Gets all the available sections
		dropdown_section.getItems().removeAll(dropdown_section.getItems());
		dropdown_section.getItems().addAll("01", "02", "03", "04", "90", "90DL", "01DL");
		dropdown_section.getSelectionModel().select("01");
		
		//Gets all the available years
		Elements years = faculty_overview_doc.getElementsByAttributeValue("id", "ContentPlaceHolder1_YearDropDownList").first().children();
		Element selectedYear = faculty_overview_doc.getElementsByAttributeValue("selected", "selected").first();
		dropdown_year.getItems().removeAll(dropdown_year.getItems());
		for(Element i : years) {
			dropdown_year.getItems().add(i.text());
		}
		dropdown_year.getSelectionModel().select(selectedYear.text());
		
		
		
	}
 
    @FXML
    void sendEmail(ActionEvent event) {

    }
    
    
    
}





