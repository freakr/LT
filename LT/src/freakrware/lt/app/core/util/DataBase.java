
package freakrware.lt.app.core.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import freakrware.lt.app.resources.Interfaces;
import android.annotation.SuppressLint;
import android.os.Environment;


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
		
	}

	@SuppressWarnings("null")
	public Vector<String> get_messageboard() {
		Vector result = new Vector();
		
		set_strsql("SELECT * FROM "+DB_TABLE_3+" WHERE "+DB_COL_10+" = FALSE");
		String[] middleresult = get_data(GETTER_MESSAGEBOARD);
		for(int x=0;x<middleresult.length;x=x+6){
			Vector dataindex = new Vector();
			dataindex.add(middleresult[x]);
			String[] username = get_username(middleresult[x+1]);
			dataindex.add(username[0]);
			String[] username2 = get_username(middleresult[x+2]);
			dataindex.add(username2[0]);
			if(middleresult[x+3].equals("1")){
				dataindex.add(true);
			}
			else{
				dataindex.add(false);
			}
			String[] messagetext = get_messagetext(middleresult[x+4]);
			dataindex.add(messagetext[0]);
			dataindex.add(middleresult[x+5]);
			result.add(dataindex);
		}
		
		return result;
	}
	public String[] get_locations_data(int x) {
		set_strsql("SELECT "+DB_COL_3+","+DB_COL_4+","+DB_COL_5+","+DB_COL_6+" FROM "+DB_TABLE_2+" WHERE "+DB_COL_1+" = "+ x);
		
		return get_data(GETTER_LOCATIONS_DATA);
	}
	public String[] get_locations() {
		set_strsql("SELECT "+DB_COL_2+" FROM "+DB_TABLE_1);
		
		return get_data(GETTER_LOCATIONS_NAME);
	}
	public String[] get_username(String userid) {
		set_strsql("SELECT "+DB_COL_1+" FROM "+DB_TABLE_1+" WHERE "+DB_COL_2+" = '"+ userid +"'");
		
		return get_data(GETTER_USER);
	}
	public String[] get_messagetext(String messageid) {
		set_strsql("SELECT "+DB_COL_6+" FROM "+DB_TABLE_2+" WHERE "+DB_COL_5+" = '"+ messageid +"'");
		
		return get_data(GETTER_MESSAGE);
	}
	public String[] checkuseronline() {
		set_strsql("SELECT "+DB_COL_1+" FROM "+DB_TABLE_1+" WHERE "+DB_COL_3+" = TRUE");
		
		return get_data(GETTER_USER_ONLINE);
	}
	public String lastip(String username) {
		set_strsql("SELECT "+DB_COL_4+" FROM "+DB_TABLE_1+" WHERE "+DB_COL_1+" = '"+ username +"'");
		String[] ip = get_data(GETTER_LASTIP);
		return ip[0];
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
	@SuppressWarnings("null")
	public String[][] get_new_messages(int user) {
		set_strsql("SELECT "+DB_COL_7+" FROM "+DB_TABLE_3+" WHERE "+DB_COL_9+" = '"+ user +"' AND "+DB_COL_10+" = FALSE");
		String[] resultids = get_data(GETTER_NEW_MESSAGES_EXISTS);
		String[][] result = new String[resultids.length][GETTER_NEW_MESSAGE.length+1];
		for(int x=0;x<resultids.length;x++){
			result[x][3] = resultids[x];
			set_strsql("SELECT DISTINCT "+DB_COL_1+" FROM "+DB_TABLE_1+","+DB_TABLE_3+" WHERE "+DB_COL_8+" = "+DB_COL_2+" AND "+DB_COL_7+" = '"+resultids[x]+"'");
			String [] resultone = get_data(GETTER_USER_NAME);
			result[x][0] = resultone[0];
			set_strsql("SELECT DISTINCT "+DB_COL_6+" FROM "+DB_TABLE_2+","+DB_TABLE_3+" WHERE "+DB_COL_7+" = '"+ resultids[x] +"' AND "+DB_TABLE_3+"."+DB_COL_5+" = "+DB_TABLE_2+"."+DB_COL_5);
			String [] resulttwo = get_data(GETTER_MESSAGE);
			result[x][1] = resulttwo[0];
			set_strsql("SELECT DISTINCT "+DB_COL_11+" FROM "+DB_TABLE_2+","+DB_TABLE_3+" WHERE "+DB_COL_7+" = '"+ resultids[x] +"'");
			String [] resultthree = get_data(GETTER_TIME);
			result[x][2] = resultthree[0];
		}
		
		return result;
	}
	public boolean add_location_position(int locationid,double latitude, double longitude,double accurracy,String provider) {
		set_strsql("INSERT INTO "+DB_TABLE_2+" ("+DB_COL_1+","+DB_COL_3+","+DB_COL_4+","+DB_COL_5+","+DB_COL_6+") VALUES ('"+ locationid +"','"+ latitude +"','"+ longitude +"','"+ accurracy +"','"+ provider +"')"); 
		
		return set_data();
	}
	public boolean messages_senttrue(String actionid) {
		set_strsql("UPDATE "+DB_TABLE_3+" SET "+DB_COL_10+" = TRUE WHERE "+DB_COL_7+" = " + actionid); 
		
		return set_data();
	}
	
	public int message_exists(String message){
		set_strsql("SELECT "+DB_COL_5+" FROM "+DB_TABLE_2+" WHERE "+DB_COL_6+" = '"+ message +"'");
		
		String[] result = get_data(GETTER_MESSAGE_EXISTS);
		for(int i=0; i< result.length;i++){
			 if(result[i] != null){
			 return Integer.parseInt(result[i]);
			 	}
			 }
		return 0;
		
	}
	public boolean message_add(String message) {
		set_strsql("INSERT INTO "+DB_TABLE_2+" ("+DB_COL_6+") VALUES ('"+ message +"')"); 
		
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
	
	public boolean set_data(){
		return execute_update();
	}
	@SuppressWarnings("null")
	public String[] get_data(String[] getter){
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
	public void set_strsql(String strsql){
		this.strsql = strsql;
	}
	public String get_strsql(){
		return this.strsql ;
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

	

	

	

	
	
}
