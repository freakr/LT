package freakrware.lt.app.resources;

public interface DataBase_interface {

	// DataBase Basic Parameter 
		static final String DB_DRIVER = "org.hsqldb.jdbcDriver";
		static final String DB_FOLDER = "LTData/";
		static final String DB_NAME = "DBLT";
		// DataBase Table-Names
		final static String DB_TABLE_1 = "Locations";
		final static String DB_TABLE_2 = "Locationdata";
		final static String DB_TABLE_3 = "Tasks";
		final static String DB_TABLE_4 = "TaskProgramms";
		final static String DB_TABLE_5 = "Taskstandards";
		final static String DB_TABLE_6 = "Setup";
		final static String DB_TABLE_7 = "TaskState";
		final static String DB_TABLE_8 = "LocationState";
		final static String DB_TABLE_9 = "LocationTasks";
		// DataBase Columns
		final static String DB_COL_1 = "LocationId";
		final static String DB_COL_2 = "LocationName";
		final static String DB_COL_3 = "Latitude";
		final static String DB_COL_4 = "Longitude";
		final static String DB_COL_5 = "Accurracy";
		final static String DB_COL_6 = "Provider";
		final static String DB_COL_7 = "TaskId";
		final static String DB_COL_8 = "TaskName";
		final static String DB_COL_9 = "Programms";
		final static String DB_COL_10 = "Checked";
		final static String DB_COL_12 = "WiRange";	//Wifi in Range
		final static String DB_COL_13 = "WooRange";	//Wifi out of Range
		final static String DB_COL_14 = "SiRange";	//Sound in Range
		final static String DB_COL_15 = "SooRange";	//Sound out of Range
		final static String DB_COL_16 = "SetupId";
		final static String DB_COL_17 = "SetupParameterName";
		final static String DB_COL_18 = "SetupParameterValue";
		final static String DB_COL_19 = "TaskStatus";
		final static String DB_COL_20 = "LocationStatus";
		// DataBase Getter
		final static String[] GETTER_LOCATION_EXISTS = {DB_COL_1};
		final static String[] GETTER_TASK_EXISTS = {DB_COL_7};
		final static String[] GETTER_TASKPROGRAMMS_EXISTS = {DB_COL_7};
		final static String[] GETTER_TASKPROGRAMMS_STATE = {DB_COL_10};
		final static String[] GETTER_PARAMETER_EXISTS = {DB_COL_16};
		final static String[] GETTER_PARAMETER_VALUE = {DB_COL_18};
		final static String[] GETTER_LOCATIONS_NAME = {DB_COL_2};
		final static String[] GETTER_TASKS_NAME = {DB_COL_8};
		final static String[] GETTER_LOCATIONS_DATA = {DB_COL_3,DB_COL_4,DB_COL_5,DB_COL_6};
		final static String[] GETTER_TASKS_DATA = {DB_COL_9};
		final static String[] GETTER_TASKS_STANDARDS = {DB_COL_12,DB_COL_13,DB_COL_14,DB_COL_15};
		final static String[] GETTER_SETUP = {DB_COL_18};
		
	
}
