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
		final static String DB_TABLE_4 = "Taskdata";
		// DataBase Columns
		final static String DB_COL_1 = "LocationId";
		final static String DB_COL_2 = "LocationName";
		final static String DB_COL_3 = "Latitude";
		final static String DB_COL_4 = "Longitude";
		final static String DB_COL_5 = "Accurracy";
		final static String DB_COL_6 = "Provider";
		final static String DB_COL_7 = "TaskId";
		final static String DB_COL_8 = "TaskName";
		final static String DB_COL_9 = "Utility";
		final static String DB_COL_10 = "TimeProfil";
		final static String DB_COL_11 = "Range";
		// DataBase Getter
		final static String[] GETTER_USER_EXISTS = {DB_COL_2};
		final static String[] GETTER_USER = {DB_COL_1};
		final static String[] GETTER_MESSAGEBOARD = {DB_COL_7,DB_COL_8,DB_COL_9,DB_COL_10,DB_COL_5,DB_COL_11};
		final static String[] GETTER_LASTIP = {DB_COL_4};
		final static String[] GETTER_USER_ONLINE = {DB_COL_1};
		final static String[] GETTER_USER_NAME = {DB_COL_1};
		final static String[] GETTER_MESSAGE_EXISTS = {DB_COL_5};
		final static String[] GETTER_MESSAGE = {DB_COL_6};
		final static String[] GETTER_TIME = {DB_COL_11};
		final static String[] GETTER_NEW_MESSAGE = {DB_COL_8,DB_COL_5,DB_COL_11};
		final static String[] GETTER_NEW_MESSAGES_EXISTS = {DB_COL_7};
		
	
}
