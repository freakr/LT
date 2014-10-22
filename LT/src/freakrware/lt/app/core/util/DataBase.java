
package freakrware.lt.app.core.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import android.annotation.SuppressLint;
import android.os.Environment;
import freakrware.lt.app.resources.Interfaces;


@SuppressLint("NewApi")
public class DataBase implements Interfaces{

	private static Connection dbVerbindung = null;
	private static Statement stmt = null;
	public ResultSet rs = null;
	public String strsql = null;
	
	{
	    try {
	        Class.forName(DB_DRIVER);
	    	
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	public DataBase(){
		
		initialise_database();
//		strsql = "CREATE TABLE "+ DB_TABLE_9 +" ("+ DB_COL_1 +" INTEGER , "+ DB_COL_7 +" INTEGER, "
//				+ "FOREIGN KEY ("+ DB_COL_1 +") REFERENCES "+ DB_TABLE_1 +" ("+ DB_COL_1 +") on delete cascade, "
//				+ "FOREIGN KEY ("+ DB_COL_7 +") REFERENCES "+ DB_TABLE_3 +" ("+ DB_COL_7 +") on delete cascade)"; 
//		edit_database(strsql);
}

	public boolean add_taskstandards(int id) {
		set_strsql("INSERT INTO "+DB_TABLE_5+" ("+DB_COL_7+") VALUES ('"+ id +"')"); 
		
		return set_data();
	}
	
	public int[] get_tasks_in_range() {
		set_strsql("SELECT "+DB_COL_7+" FROM "+DB_TABLE_7+" WHERE "+DB_COL_19+" = "+true);
		
		String[] result = get_data(GETTER_TASK_EXISTS);
		int[] iresult = new int[result.length];
		for(int i=0; i< result.length;i++)
		{
			 iresult[i] = Integer.parseInt(result[i]);
		}
			 
		return iresult;
	
	}
	
	public int[] get_locations_in_range() {
		set_strsql("SELECT "+DB_COL_1+" FROM "+DB_TABLE_8+" WHERE "+DB_COL_20+" = "+true);
		
		String[] result = get_data(GETTER_LOCATION_EXISTS);
		int[] iresult = new int[result.length];
		for(int i=0; i< result.length;i++)
		{
			 iresult[i] = Integer.parseInt(result[i]);
		}
			 
		return iresult;
	
	}
	
	public boolean add_taskstate(int id) {
		set_strsql("INSERT INTO "+DB_TABLE_7+" ("+DB_COL_7+") VALUES ('"+ id +"')"); 
		
		return set_data();
	}
	
	public boolean add_Locationstate(int id) {
		set_strsql("INSERT INTO "+DB_TABLE_8+" ("+DB_COL_1+") VALUES ('"+ id +"')"); 
		
		return set_data();
	}
	
	public boolean get_taskstandards_data(int id,String column) {
		String[] data = get_taskstandards_complete_data(id);
		switch(column){
		case DB_COL_12:
			if(data[0].equals("1"))
			{
				return true;
			}
			else
			{
				return false;
			}
		case DB_COL_13:
			if(data[1].equals("1"))
			{
				return true;
			}
			else
			{
				return false;
			}
		case DB_COL_14:
			if(data[2].equals("1"))
			{
				return true;
			}
			else
			{
				return false;
			}
		case DB_COL_15:
			if(data[3].equals("1"))
			{
				return true;
			}
			else
			{
				return false;
			}
		default:
			return false;
		}
	}
	
	private String[] get_taskstandards_complete_data(int id) {
		set_strsql("SELECT "+DB_COL_12+","+DB_COL_13+","+DB_COL_14+","+DB_COL_15+" FROM "+DB_TABLE_5+" WHERE "+DB_COL_7+" = "+ id);
		
		return get_data(GETTER_TASKS_STANDARDS);
	}
	
	public boolean edit_task_state_value(int id, boolean b) {
		if(b == true)
		{
			set_strsql("UPDATE "+DB_TABLE_7+" SET "+DB_COL_19+" = "+true+" WHERE "+DB_COL_7+" = "+id);
		}
		else
		{
			set_strsql("UPDATE "+DB_TABLE_7+" SET "+DB_COL_19+" = "+false+" WHERE "+DB_COL_7+" = "+id);
		}
		return set_data();
		
	}
	public boolean edit_location_state_value(int id, boolean b) {
		if(b == true)
		{
			set_strsql("UPDATE "+DB_TABLE_8+" SET "+DB_COL_20+" = "+true+" WHERE "+DB_COL_1+" = "+id);
		}
		else
		{
			set_strsql("UPDATE "+DB_TABLE_8+" SET "+DB_COL_20+" = "+false+" WHERE "+DB_COL_1+" = "+id);
		}
		return set_data();
		
	}
	public boolean edit_taskstandards_value(int id,String column,String value){
		if(value.equals("true"))
		{
			set_strsql("UPDATE "+DB_TABLE_5+" SET "+column+" = "+true+" WHERE "+DB_COL_7+" = "+id);
		}
		else
		{
			set_strsql("UPDATE "+DB_TABLE_5+" SET "+column+" = "+false+" WHERE "+DB_COL_7+" = "+id);
		}
		return set_data();
	}
	
	public boolean add_task(String name) {
		set_strsql("INSERT INTO "+DB_TABLE_3+" ("+DB_COL_8+") VALUES ('"+ name +"')"); 
		
		return set_data();
	}
	
	public boolean add_task_to_location(int locationid,int taskid) {
		set_strsql("INSERT INTO "+DB_TABLE_9+" ("+DB_COL_1+","+DB_COL_7+") VALUES ('"+ locationid +"','"+ taskid +"')"); 
		
		return set_data();
	}
	
	public boolean remove_task(String name){
		set_strsql("DELETE FROM "+DB_TABLE_3+" WHERE "+DB_COL_8+" = '"+ name+"'"); 
				
		return set_data();
	}
	
	public String[] get_tasks() {
		set_strsql("SELECT "+DB_COL_8+" FROM "+DB_TABLE_3);
		
		return get_data(GETTER_TASKS_NAME);
	}
	
	public int get_task_from_location(int locid) {
		set_strsql("SELECT "+DB_COL_7+" FROM "+DB_TABLE_9+" WHERE "+DB_COL_1+" = "+locid);
			
		String[] result = get_data(GETTER_TASK_EXISTS);
		for(int i=0; i< result.length;)
		{
				return Integer.parseInt(result[i]);
		}
				 
		return 0;
		
	}
	
	public int[] get_tasks_from_location(int locid) {
		set_strsql("SELECT "+DB_COL_7+" FROM "+DB_TABLE_9+" WHERE "+DB_COL_1+" = "+locid);
			
		String[] result = get_data(GETTER_TASK_EXISTS);
		int[] iresult = new int[result.length];
		for(int i=0; i< result.length;i++)
		{
			 iresult[i] = Integer.parseInt(result[i]);
		}
			 
		return iresult;
		
	}
	
	public String[] get_locations_data(int id) {
		set_strsql("SELECT "+DB_COL_3+","+DB_COL_4+","+DB_COL_5+","+DB_COL_6+" FROM "+DB_TABLE_2+" WHERE "+DB_COL_1+" = "+ id);
		
		return get_data(GETTER_LOCATIONS_DATA);
	}
	
	public String[] get_locations() {
		set_strsql("SELECT "+DB_COL_2+" FROM "+DB_TABLE_1);
		
		return get_data(GETTER_LOCATIONS_NAME);
	}
	
	public int exists_task(String name){
		set_strsql("SELECT "+DB_COL_7+" FROM "+DB_TABLE_3+" WHERE "+DB_COL_8+" = '"+ name +"'");
		
		String[] result = get_data(GETTER_TASK_EXISTS);
		for(int i=0; i< result.length;i++){
			 if(result[i] != null){
			 return Integer.parseInt(result[i]);
			 	}
			 }
		return 0;
	}
	
	public int exists_location(String name){
		set_strsql("SELECT "+DB_COL_1+" FROM "+DB_TABLE_1+" WHERE "+DB_COL_2+" = '"+ name +"'");
		
		String[] result = get_data(GETTER_LOCATION_EXISTS);
		for(int i=0; i< result.length;i++){
			 if(result[i] != null){
			 return Integer.parseInt(result[i]);
			 	}
			 }
		return 0;
	}
	
	public boolean add_location_position(int locationid,double latitude, double longitude,double accurracy,String provider) {
		set_strsql("INSERT INTO "+DB_TABLE_2+" ("+DB_COL_1+","+DB_COL_3+","+DB_COL_4+","+DB_COL_5+","+DB_COL_6+") VALUES ('"+ locationid +"','"+ latitude +"','"+ longitude +"','"+ accurracy +"','"+ provider +"')"); 
		
		return set_data();
	}
	
	public int exists_parameter(String name){
		set_strsql("SELECT "+DB_COL_16+" FROM "+DB_TABLE_6+" WHERE "+DB_COL_17+" = '"+ name +"'");
		
		String[] result = get_data(GETTER_PARAMETER_EXISTS);
		for(int i=0; i< result.length;i++){
			 if(result[i] != null){
			 return Integer.parseInt(result[i]);
			 	}
			 }
		return 0;
	}
	
	public boolean add_setup_parameter(String name){
		set_strsql("INSERT INTO "+DB_TABLE_6+" ("+DB_COL_17+") VALUES ('"+ name +"')"); 
				
		return set_data();
	}
	
	public boolean edit_setup_parameter_value(int id,String value){
		set_strsql("UPDATE "+DB_TABLE_6+" SET "+DB_COL_18+" = "+ value +" WHERE "+DB_COL_16+" = "+id); 
		return set_data();
	}
	
	public boolean remove_Parameter(String name){
		set_strsql("DELETE FROM "+DB_TABLE_6+" WHERE "+DB_COL_17+" = '"+ name+"'"); 
				
		return set_data();
	}
	
	public boolean add_location(String name){
		set_strsql("INSERT INTO "+DB_TABLE_1+" ("+DB_COL_2+") VALUES ('"+ name +"')"); 
				
		return set_data();
	}
	
	public boolean remove_Location(String name){
		set_strsql("DELETE FROM "+DB_TABLE_1+" WHERE "+DB_COL_2+" = '"+ name+"'"); 
				
		return set_data();
	}
	
	private boolean set_data(){
		return execute_update();
	}
	
	@SuppressWarnings("null")
	private String[] get_data(String[] getter){
		Vector result = new Vector();
		execute_query();
		try {
			while(rs.next())
			{
				for(int y = 0;y < getter.length;y++){
					
					if(getter[y].equals(DB_COL_3)||getter[y].equals(DB_COL_4)||getter[y].equals(DB_COL_5)){
						result.add(rs.getDouble(getter[y]));
					}
					else{
						result.add(rs.getString(getter[y]));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close_result_set();
		String[] resultarray = new String[result.size()];
		for(int x = 0; x < result.size();x++){
			resultarray[x] = String.valueOf(result.get(x));
		}
		return resultarray;
		
	}
	
	private boolean execute_update(){
		try {
			stmt.executeUpdate(strsql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	private void execute_query(){
		try
	    {
	    rs = stmt.executeQuery(strsql);
	    } catch(SQLException e)
	    {
	      e.printStackTrace();
	    }
		
	}
	
	private void close_result_set(){
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void set_strsql(String strsql){
		this.strsql = strsql;
	}
	
	public void edit_database(String strsql){
		set_strsql(strsql);
		set_data();
	}
	
	
	protected void create_database(){
		strsql = "CREATE TABLE "+ DB_TABLE_1 +" ("+ DB_COL_1 +" INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1)PRIMARY KEY, "+ DB_COL_2 +" VARCHAR(255))"; 
		try {
			stmt.executeUpdate(strsql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		strsql = "CREATE TABLE "+ DB_TABLE_2 +" ("+ DB_COL_1 +" INTEGER , "+ DB_COL_3 +" DOUBLE, "+ DB_COL_4 +" DOUBLE, "+ DB_COL_5 +" DOUBLE, "+ DB_COL_6 +" VARCHAR(255), "
					+ "FOREIGN KEY ("+ DB_COL_1 +") REFERENCES "+ DB_TABLE_1 +" ("+ DB_COL_1 +") on delete cascade)"; 
		try {
			stmt.executeUpdate(strsql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		strsql = "CREATE TABLE "+ DB_TABLE_3 +" ("+ DB_COL_7 +" INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1)PRIMARY KEY, "+ DB_COL_8 +" VARCHAR(255))"; 
		try {
			stmt.executeUpdate(strsql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		strsql = "CREATE TABLE "+ DB_TABLE_4 +" ("+ DB_COL_7 +" INTEGER , "+ DB_COL_9 +" INTEGER, "+ DB_COL_10 +" INTEGER, "+ DB_COL_11 +" DOUBLE, "
					+ "FOREIGN KEY ("+ DB_COL_7 +") REFERENCES "+ DB_TABLE_3 +" ("+ DB_COL_7 +") on delete cascade)"; 
		try {
			stmt.executeUpdate(strsql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		strsql = "CREATE TABLE "+ DB_TABLE_5 +" ("+ DB_COL_7 +" INTEGER , "+ DB_COL_12 +" BIT DEFAULT FALSE NOT NULL, "+ DB_COL_13 +" BIT DEFAULT FALSE NOT NULL, "+ DB_COL_14 +" BIT DEFAULT FALSE NOT NULL, "+ DB_COL_15 +" BIT DEFAULT FALSE NOT NULL, "
					+ "FOREIGN KEY ("+ DB_COL_7 +") REFERENCES "+ DB_TABLE_3 +" ("+ DB_COL_7 +") on delete cascade)"; 
		try {
			stmt.executeUpdate(strsql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		strsql = "CREATE TABLE "+ DB_TABLE_6 +" ("+ DB_COL_16 +" GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1)PRIMARY KEY, "+ DB_COL_17 +" VARCHAR(255), "+ DB_COL_18 +" VARCHAR(255)"; 
		try {
			stmt.executeUpdate(strsql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		strsql = "CREATE TABLE "+ DB_TABLE_7 +" ("+ DB_COL_7 +" INTEGER , "+ DB_COL_19 +" BIT DEFAULT FALSE NOT NULL, "
				+ "FOREIGN KEY ("+ DB_COL_7 +") REFERENCES "+ DB_TABLE_3 +" ("+ DB_COL_7 +") on delete cascade)"; 
		try {
			stmt.executeUpdate(strsql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		strsql = "CREATE TABLE "+ DB_TABLE_8 +" ("+ DB_COL_1 +" INTEGER , "+ DB_COL_20 +" BIT DEFAULT FALSE NOT NULL, "
				+ "FOREIGN KEY ("+ DB_COL_1 +") REFERENCES "+ DB_TABLE_1 +" ("+ DB_COL_1 +") on delete cascade)"; 
		try {
			stmt.executeUpdate(strsql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		strsql = "CREATE TABLE "+ DB_TABLE_9 +" ("+ DB_COL_1 +" INTEGER , "+ DB_COL_7 +" INTEGER, "
				+ "FOREIGN KEY ("+ DB_COL_1 +") REFERENCES "+ DB_TABLE_1 +" ("+ DB_COL_1 +") on delete cascade)"
				+ "FOREIGN KEY ("+ DB_COL_7 +") REFERENCES "+ DB_TABLE_3 +" ("+ DB_COL_7 +") on delete cascade)"; 
		try {
			stmt.executeUpdate(strsql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	protected void initialise_database() {
		 String newFolder = "/"+DB_FOLDER;
		 String extStorageDirectory = "";
		 try{
			 extStorageDirectory = Environment.getExternalStorageDirectory().toString();
		 }
		 catch(Exception e){
			 //e.printStackTrace();
		 }
	     
	     File myNewFolder = new File(extStorageDirectory + newFolder);
	     myNewFolder.mkdir();
		
		File path = myNewFolder; 
	    File file = new File(path, DB_NAME);
	    String filepath = file.getAbsolutePath();
	    try {
	    	dbVerbindung = DriverManager.getConnection("jdbc:hsqldb:file:"+filepath+";ifexists=true", "SA", "");
	    	dbVerbindung.setAutoCommit(true);
	    	stmt = dbVerbindung.createStatement();
	    } catch (SQLException e) {
	    	System.out.println(e.getErrorCode());
	    	if(e.getErrorCode()==-465){
	    		try {
					dbVerbindung = DriverManager.getConnection("jdbc:hsqldb:file:"+file.getAbsolutePath()+"", "SA", "");
					dbVerbindung.setAutoCommit(true);
					stmt = dbVerbindung.createStatement();
					create_database();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		    }
	    }
	    Runtime.getRuntime().addShutdownHook(new Thread() {
	        public void run() {
	            try {
	            	if (rs != null && !rs.isClosed()){
	            		rs.close();
	            	}
	                if (!dbVerbindung.isClosed()) {
	                	dbVerbindung.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}

	public boolean remove_Task_from_Location(int locationid){
		set_strsql("DELETE FROM "+DB_TABLE_9+" WHERE "+DB_COL_1+" = "+ locationid); 
				
		return set_data();
	}

	public boolean exists_location_task(int locationid,int taskid){
		set_strsql("SELECT "+DB_COL_1+" FROM "+DB_TABLE_9+" WHERE "+DB_COL_1+" = "+ locationid+" AND "+DB_COL_7+" = "+ taskid);
		
		String[] result = get_data(GETTER_LOCATION_EXISTS);
		if(result.length > 0)
		{
		return true;
		}
		return false;
	}

	public ArrayList<String> get_tasks_arraylist() {
		set_strsql("SELECT "+DB_COL_8+" FROM "+DB_TABLE_3);
		ArrayList<String> dataarray = new ArrayList<String>();
		String[] data = get_data(GETTER_TASKS_NAME);
		for(int x = 0;x < data.length;x++){
			dataarray.add(data[x]);
		}
		return dataarray;
	}

	public String get_task(int taskid) {
		set_strsql("SELECT "+DB_COL_8+" FROM "+DB_TABLE_3+" WHERE "+DB_COL_7+" = "+ taskid);
		String[] data = get_data(GETTER_TASKS_NAME);
		for(int x = 0;x<data.length;)
		{
			return data[x];
		}
		return null;
	}


}
