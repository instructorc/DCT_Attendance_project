package dct_attendance_app;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class Attendance_Controller {
	String alteredCourseName;
	private static final String USERNAME = "profifty_admin20";
	private static final String PASSWORD = "########";
	private static final String CONN_STRING = "jdbc:mysql://184.154.73.76/profifty_dctattendance";

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
	    private TableColumn<Attendance_Roster, Number> table_col_percentAttended_ten;

    @FXML
    private TableView<Attendance_Roster> fifteen_percent_table;
    
	    @FXML
	    private TableColumn<Attendance_Roster, String> table_col_name_fifteen;
	
	    @FXML
	    private TableColumn<Attendance_Roster, String> table_col_hoursMissed_fifteen;
	
	    @FXML
	    private TableColumn<Attendance_Roster, Number> table_col_percentAttended_fifteen;
    
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
	    private TableColumn<Attendance_Roster, String> table_col_level_email;

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
    void displayCourse(ActionEvent event) throws IOException, SQLException {
    	//Declaring variables
    	String name, hoursAttended, hoursMissed,percentAttended;
    	
    	ObservableList<Attendance_Roster> studentList = FXCollections.observableArrayList();
    	ObservableList<Attendance_Roster> five_list = FXCollections.observableArrayList();
    	ObservableList<Attendance_Roster> ten_list = FXCollections.observableArrayList();
    	ObservableList<Attendance_Roster> fifteen_list = FXCollections.observableArrayList();
    	ObservableList<Attendance_Roster> instance = FXCollections.observableArrayList();
    	
        // Initialize the Attendance Roster table 4 columns.
    	table_col_name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    	table_col_hoursAttended.setCellValueFactory(cellData -> cellData.getValue().hoursAttendedProperty());        
    	table_col_hoursMissed.setCellValueFactory(cellData -> cellData.getValue().hoursMissedProperty());
    	table_col_percentAttended.setCellValueFactory(cellData -> cellData.getValue().percentAttendedProperty());
    	
    	// Initialize the Attendance Roster email table 4 columns.
    	table_col_name_email.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    	table_col_hoursAttended_email.setCellValueFactory(cellData -> cellData.getValue().hoursAttendedProperty());        
    	table_col_hoursMissed_email.setCellValueFactory(cellData -> cellData.getValue().hoursMissedProperty());
    	table_col_percentAttended_email.setCellValueFactory(cellData -> cellData.getValue().percentAttendedProperty());
    	table_col_level_email.setCellValueFactory(cellData -> cellData.getValue().sentProperty());
    	
		 // Initialize the five percent roster table 3 columns.
    	table_col_name_five.setCellValueFactory(cellData -> cellData.getValue().nameProperty());        
    	table_col_hoursMissed_five.setCellValueFactory(cellData -> cellData.getValue().hoursMissedProperty());
    	table_col_percentAttended_five.setCellValueFactory(cellData -> cellData.getValue().percentAttendedProperty());
    	
		 // Initialize the ten percent roster table 3 columns.
    	table_col_name_ten.setCellValueFactory(cellData -> cellData.getValue().nameProperty());        
    	table_col_hoursMissed_ten.setCellValueFactory(cellData -> cellData.getValue().hoursMissedProperty());
    	table_col_percentAttended_ten.setCellValueFactory(cellData -> cellData.getValue().percentAttendedProperty());
    	
		 // Initialize the five percent roster table 3 columns.
    	table_col_name_fifteen.setCellValueFactory(cellData -> cellData.getValue().nameProperty());        
    	table_col_hoursMissed_fifteen.setCellValueFactory(cellData -> cellData.getValue().hoursMissedProperty());
    	table_col_percentAttended_fifteen.setCellValueFactory(cellData -> cellData.getValue().percentAttendedProperty());
    	
    	
        
    	
    	alteredCourseName = dropdown_coursenames.getValue().substring(0, 8);
    	System.out.println(alteredCourseName);
    	
    	SingleSelectionModel<String> course = dropdown_coursenames.getSelectionModel();
    	//System.out.println(course.getSelectedItem());
    	
    	Document doc = Jsoup.connect("https://staff.dunwoody.edu/x/IIS/Attendance/CourseAttendance.aspx?event_id=" +alteredCourseName+"&section_id="+ dropdown_section.getValue()+"&year="+dropdown_year.getValue()+ "&term="+ dropdown_term.getValue()).get();

		//Target the parent table and scrap children element data
		Elements AttendanceInfo = doc.select("tbody").first().children();

		for(Element row : AttendanceInfo.subList(3,  AttendanceInfo.size())) {
			name = row.child(0).text();
			hoursAttended = row.child(1).text();
			hoursMissed = row.child(2).text();
			percentAttended = row.child(3).text();
			String ap = percentAttended.replace("%","");
			if(ap.equalsIgnoreCase("Withdrawn") || ap.equalsIgnoreCase("Dropped")) {
				ap = "0";
			}else {
				double percentStripped = Double.parseDouble(ap);
	
				if(percentStripped <= 84.00 ) {
					fifteen_list.add(new Fifteen_Percent_Student(name,hoursAttended, hoursMissed, percentStripped, "fifteen_level"));
			
				}else if(percentStripped <= 89.00) {
					ten_list.add(new Ten_Percent_Student(name,hoursAttended, hoursMissed, percentStripped, "ten_level" ));
				}else if (percentStripped <= 94.00){
					five_list.add(new Five_Percent_Student(name,hoursAttended, hoursMissed, percentStripped, "five_level"));
				}else {
					studentList.add( new Attendance_Roster(name,hoursAttended, hoursMissed, percentStripped, "good_standing"));
				}
			}

		} 
		
		 
		//Set the value to display count for each table
		 label_total_student_count.setText(Integer.toString(studentList.size()));
		 label_five_count.setText(Integer.toString(five_list.size()));
		 label_ten_count.setText(Integer.toString(ten_list.size()));
		 label_fifteen_count.setText(Integer.toString(fifteen_list.size()));
			//Set count labels to display when program starts
		 label_total_student_count.setVisible(true);
		 label_five_count.setVisible(true);
		 label_ten_count.setVisible(true);
		 label_fifteen_count.setVisible(true);
		 
		 //Set the list to display in for various table
		 all_students_table.setItems(studentList);
		 fifteen_percent_table.setItems(fifteen_list);
		 ten_percent_table.setItems(ten_list);
		 five_percent_table.setItems(five_list);

		


	    	five_percent_table.setOnMouseClicked((MouseEvent event1) -> {
	            if(event1.getButton().equals(MouseButton.PRIMARY)){
	            	ten_percent_table.getSelectionModel().clearSelection();
		    		fifteen_percent_table.getSelectionModel().clearSelection();
	                System.out.println(five_percent_table.getSelectionModel().getSelectedItem());
	            }
	        });
	    	ten_percent_table.setOnMouseClicked((MouseEvent event2) -> {
	            if(event2.getButton().equals(MouseButton.PRIMARY)){
	            	five_percent_table.getSelectionModel().clearSelection();
		    		fifteen_percent_table.getSelectionModel().clearSelection();
	                System.out.println(ten_percent_table.getSelectionModel().getSelectedItem());
	            }
	    	});
	    	fifteen_percent_table.setOnMouseClicked((MouseEvent event3) -> {
	            if(event3.getButton().equals(MouseButton.PRIMARY)){
	            	ten_percent_table.getSelectionModel().clearSelection();
		    		five_percent_table.getSelectionModel().clearSelection();
	                System.out.println(fifteen_percent_table.getSelectionModel().getSelectedItem());
	            }
	        });
	    	
			String name2, hAttended, mHours, sLevel;
			Double pAttended;
	    	Connection conn1 = null;
			ResultSet results1 = null;
			Statement stmt2 = null;
			
			//PreparedStatement stmt2;
			try {
				conn1 = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
				System.out.println("Connected!");
				
				String sql_getAttendance_query = "SELECT studentName, hoursAttended, missedHours, percentAttended, sentLevel FROM ATTENDANCE_EMAILS WHERE courseName = ? AND courseSection = ? AND courseTerm = ? AND courseYear = ? ";
				//String sql_getAttendance_query = "SELECT studentName, hoursAttended, missedHours, percentAttended, sentLevel FROM ATTENDANCE_EMAILS WHERE courseName = ?";		
				
				ResultSet rs = null;
				PreparedStatement attendance_stmt = conn1.prepareStatement(sql_getAttendance_query);

				attendance_stmt.setString(1,alteredCourseName);
				attendance_stmt.setString(2, dropdown_section.getValue());
				attendance_stmt.setString(3, dropdown_term.getValue());
				attendance_stmt.setString(4, dropdown_year.getValue());
				rs = attendance_stmt.executeQuery();
		
				while(rs.next()){
					
				    name = rs.getString(1); 
					hAttended = rs.getString(2); 
					mHours =rs.getString(3); 
					pAttended = rs.getDouble(4); 
					sLevel = rs.getString(5);
					instance.add(new Attendance_Roster(name, hAttended, mHours, pAttended, sLevel));
					System.out.println(name + hAttended + mHours + pAttended + sLevel);
					
				}
				email_table.setItems(instance);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println(e);
			}finally{
				if(results1 != null){
					results1.close();
				}
				if(stmt2 != null){
					stmt2.close();
				}
				if(conn1 != null){
					conn1.close();
				}
			}
	        
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
		
		//Set count labels to not display when program starts
		 label_total_student_count.setVisible(false);
		 label_five_count.setVisible(false);
		 label_ten_count.setVisible(false);
		 label_fifteen_count.setVisible(false);
		
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
	/**
	 * 
	 * 
	 * Send email and commit user to database
	 * @param event
	 * @throws SQLException 
	 */
    @FXML
   void sendEmail(ActionEvent event) throws SQLException {
    	
    	String level;
    	Connection conn = null;
		
		Statement stmt = null;
		ResultSet results = null;
    	Attendance_Roster selectedStudent;
    	if(five_percent_table.getSelectionModel().getSelectedItem() != null) {
    		selectedStudent = five_percent_table.getSelectionModel().getSelectedItem();
    		level = "five level";
    		System.out.println(selectedStudent.getName());
    	}else if(ten_percent_table.getSelectionModel().getSelectedItem() != null) {
    		selectedStudent = ten_percent_table.getSelectionModel().getSelectedItem();
    		level = "ten level";
    		System.out.println(selectedStudent.getName());
    	}else {
    		selectedStudent = fifteen_percent_table.getSelectionModel().getSelectedItem();
    		System.out.println(selectedStudent.getName());
    		level = "fifteen level";
    	}

		try {
			conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			System.out.println("Connected!");
			stmt = conn.createStatement();

			String sql = "INSERT INTO ATTENDANCE_EMAILS (studentName, hoursAttended, missedHours, percentAttended, sentLevel, courseName, courseSection, courseTerm, courseYear, date)" +
			        "VALUES (?, ?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setString(1, selectedStudent.getName());
			preparedStatement.setString(2, selectedStudent.getHoursAttended());
			preparedStatement.setString(3, selectedStudent.getHoursMissed());
			preparedStatement.setDouble(4, selectedStudent.getPercentAttended());
			preparedStatement.setString(5, level);
			preparedStatement.setString(6, alteredCourseName);  
			preparedStatement.setString(7, dropdown_section.getValue());
			preparedStatement.setString(8, dropdown_term.getValue());
			preparedStatement.setString(9, dropdown_year.getValue());
			preparedStatement.setDate(10, new Date(new java.util.Date().getTime()));	
			preparedStatement.executeUpdate(); 
			
			System.out.println("Records Inserted");
			send_email.setText("EMAIL SENT");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}finally{
			if(results != null){
				results.close();
			}
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}
    	
    	
    }
    
    
    
}





