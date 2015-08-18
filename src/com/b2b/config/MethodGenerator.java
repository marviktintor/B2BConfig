package com.b2b.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MethodGenerator {

	/**
	 * Victor Mwenda 1400 - 1700hrs on 18-08-2015
	 */
	
	static String [] classTableNames = new String []
			{"tbl_buyers",
			"tbl_category",
			"tbl_certificate",
			"tbl_files",
			"tbl_group",
			"tbl_location",
			"tbl_messages",
			"tbl_order",
			"tbl_products",
			"tbl_quick_sale",
			"tbl_store",
			"tbl_suppliers",
			"tbl_users"};
	
	static String []tbl_buyers = {"buyer_id", "user_id", "time_added"};
	static String []tbl_category = {"cat_id", "cat_name", "time_added"};
	static String []tbl_certificate = { "certificate_id", "certificate_type", "time_added"};
	static String []tbl_files = {"file_id", "file_url_name", "time_added"};
	static String []tbl_group = {"group_id", "group_name", "cat_id", "time_added"};
	static String []tbl_location = {"location_id", "address", "location_lat", "location_long", "time_added" };
	static String []tbl_messages = {"message_id", "supplier_id", "time_added", "message", "file_id"};
	static String []tbl_order = {"order_id", "product_id", "supplier_id", "buyer_id", "time_added", "quantity", "price"};
	static String []tbl_products = { "product_id", "product_name", "time_added", "supplier_id", "store_id", "quick_sale_id", "MOQ", "group_id"};
	static String []tbl_quick_sale = {"quick_sale_id", "time_added", "product_id"};
	static String []tbl_store = {"store_id", "products_id", "quantity", "location_id", "time_added"};
	static String []tbl_suppliers = {"supplier_id", "user_id", "certificate_id", "location_id", "Supplier_type", "time_added"};
	static String []tbl_users= {"user_id", "file_id", "full_name", "user_phone_number", "user_email", "username", "user_password", "time_added"};
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i = 0; i <classTableNames.length;i++){
		String classCode = "<?php "
				+ " class "+getClassName(classTableNames[i])+" {"
			+ "  private $dbutils; "
			+ " private $action; "
			+ " private $client; "
			+ " public function __construct($action, $client) { " 
			+ " require_once 'includes/dbapi/db_actions.php';"
			+ " 	include 'includes/dbapi/db_utils.php';$this->dbutils = new db_utils();" 
			+ " }"
			+ " public function init($execute = false) {"
			+ " 	$this->client = $_POST [POST_CLIENT];"
				+ " $this->action = $_POST [POST_ACTION];"
				+ " if ($execute) {"
					+ " if ($this->get_action == ACTION_INSERT) {"
					+ " " +
					"}"
					+ " if ($this->get_action == ACTION_UPDATE) {"
					+ " }"
					+ " if ($this->get_action == ACTION_QUERY) {"
					+ " }"
					+ " if ($this->get_action == ACTION_DELETE) {"
						+ " }"
						+ " }"
						+ " }"
				+get_table_primary_key_crud(i) 
				+get_table_columns_crud(i) 
			+ " private function get_table() {"
				+ " return " + get_current_table(i) +";"
				+ " }"
			+ " private function get_action() {"
				+ " return $this->action;"
				+ " }"
			+ " private function get_client() {"
				+ " return $this->client;"
				+ " }"
			+ " public function is_exists(Array $columns, Array $records, $printSQL = false) {"
				+ " return $this->is_exists ( $this->get_table (), $columns, $records, $printSQL );"
				+ " }"
			+ " public function insert_records_to_" +classTableNames[i].replace("tbl_", "") +"(Array $columns, Array $records, $printSQL = false) {"
				+ " return $this->insert_records ( $this->get_table (), $columns, $records, $printSQL );"
				+ " }"
			+ " public function delete_record_from_" +classTableNames[i].replace("tbl_", "") +"(Array $columns, Array $records, $printSQL = false) {"
				+ " return $this->delete_record ( $this->get_table (), $columns, $records, $printSQL );"
				+ " }"
			+ " public function update_record_in_" +classTableNames[i].replace("tbl_", "") +"(Array $columns, Array $records, Array $where_columns, Array $where_records, $printSQL = false) {"
				+ " return $this->update_record ( $this->get_table (), $columns, $records, $where_columns, $where_records, $printSQL );"
				+ " }"
			+ "  public function fetch_assoc_in_" +classTableNames[i].replace("tbl_", "") +"(Array $columns, Array $records, $printSQL = false) {"
				+ " return $this->fetch_assoc ( $this->get_table (), $columns, $records, $printSQL );"
				+ " }"
			+ "  public function query_from_" +classTableNames[i].replace("tbl_", "") +"(Array $columns, Array $records, $printSQL = false) {"
				+ " return $this->query ( $this->get_table (), $columns, $records, $printSQL );"
				+ " }"
			+ "  public function search_in_" +classTableNames[i].replace("tbl_", "") +"(Array $columns, Array $records, $printSQL = false) {"
				+"return $this->search ( $this->get_table (), $columns, $records, $printSQL );"
				+ " }"
+ " private function delete_record($table,Array $columns,Array  $records,$printSQL = false){return $this->dbutils->delete_record($table, $columns,  $records,$printSQL );	}"
+ " private function insert_records($table,Array $columns,Array  $records,$printSQL = false){return $this->dbutils->insert_records($table, $columns,  $records,$printSQL );}"
+ " private function update_record($table,Array $columns, Array $records, Array $where_columns,Array $where_records,$printSQL = false){return $this->dbutils->update_record($table, $columns,  $records,  $where_columns, $where_records,$printSQL);}"
+ " private function fetch_assoc($table,Array $columns,Array  $records,$printSQL = false){return $this->dbutils->fetch_assoc($table, $columns,  $records,$printSQL);	}public function query($table,Array $columns,Array $records,$printSQL = false){$this->dbutils->query($table,$columns, $records,$printSQL);}"
+ " private function search($table,Array $columns,Array $records,$printSQL = false){return $this->dbutils->search($table,$columns,$records,$printSQL);}"
				+ " } "+ " ?>";
		
	
			writeToDisk(i,classCode);
		}
	}

	private static String get_current_table(int i) {
		// TODO Auto-generated method stub
		return "'"+classTableNames[i] +"'";
	}

	private static void writeToDisk(int i, String classCode) {
		// TODO Auto-generated method stub
		
		File file = new File("C:\\xampp\\htdocs\\B2B\\includes\\php\\" +classTableNames[i].replace("tbl_", "") +".class.php");
		try {
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			osw.write(classCode);
			osw.flush();
			osw.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String get_table_columns_crud(int i) {
		// TODO Auto-generated method stub
		String [] table = null;
		if(i == 0){ table = tbl_buyers;}
		if(i == 1){table = tbl_category;}
		if(i == 2){table = tbl_certificate;}
		if(i == 3){table = tbl_files;}
		if(i == 4){table =tbl_group ;}
		if(i == 5){table = tbl_location;}
		if(i == 6){table =tbl_messages ;}
		if(i == 7){table = tbl_order;}
		if(i == 8){table = tbl_products;}
		if(i == 9){table = tbl_quick_sale;}
		if(i == 10){table = tbl_store;}
		if(i == 11){table = tbl_suppliers;}
		if(i == 12){table = tbl_users;}
		
		String columns_crud = "";
		
		
		for(int j = 0;j<table.length; j++){
			if(j>0){
				columns_crud += generate_table_getters(classTableNames[i], table[j], table[0]);
			}
		}
		
		return columns_crud;
	}

	private static String get_table_primary_key_crud(int i) {
		// TODO Auto-generated method stub
		String [] table = null;
		if(i == 0){ table = tbl_buyers;}
		if(i == 1){table = tbl_category;}
		if(i == 2){table = tbl_certificate;}
		if(i == 3){table = tbl_files;}
		if(i == 4){table =tbl_group ;}
		if(i == 5){table = tbl_location;}
		if(i == 6){table =tbl_messages ;}
		if(i == 7){table = tbl_order;}
		if(i == 8){table = tbl_products;}
		if(i == 9){table = tbl_quick_sale;}
		if(i == 10){table = tbl_store;}
		if(i == 11){table = tbl_suppliers;}
		if(i == 12){table = tbl_users;}
		
		return get_primary_key(classTableNames[i], table[0], table);
	}

	private static String get_table_name(int i) {
		// TODO Auto-generated method stub
		String tableName = classTableNames[i].replace("tbl_", "");
		return tableName.substring(0,1).toUpperCase()+tableName.substring(1,tableName.length());
	}

	private static String generate_table_getters(String table_name, String target_column, String primaryKey) {
		// TODO Auto-generated method stub
			return "public function get_"+target_column+"($" +primaryKey
					+"){$columns = array('" +primaryKey+"'"
					+");$records = array($"+primaryKey+");" +
					"$"+target_column +"s = $this->query_from_"+table_name
					+"($columns, $records);return "+ "$" +primaryKey.replace("_id", "s") +"[0]['" +target_column + "'];}";
	}

	private static String get_primary_key(String tablename ,String primaryKey,String [] columns){
		return "public function get_"+primaryKey+"(" +return_comma_separated_columns(columns) +"){$columns = array(" +return_table_columns_for_crud_use(columns) +");$records = array("+return_comma_separated_columns(columns)+");" +
				"$" +primaryKey.replace("_id", "s") +" = $this->query_from_"+tablename+"($columns, $records);return "+"$" +primaryKey.replace("_id", "s") +"[0]['" +primaryKey + "'];}";
	}
	private static String return_table_columns_for_crud_use(String[] columns) {
		// TODO Auto-generated method stub
		String cols = null;
		for(int i = 0;i< columns.length; i++){
			
			if(i == 0){
				cols = "";
			}
			cols += "'" +columns[i] +"'";
			
			if(i < columns.length -1){
				cols += ",";
			}
		}
		return cols;
	}

	private static String return_comma_separated_columns(String[] columns) {
		// TODO Auto-generated method stub
		String cols = null;
		for(int i = 0;i< columns.length; i++){
			
			if(i == 0){
				cols = "";
			}
			cols += "$" +columns[i] ;
			
			if(i < columns.length -1){
				cols += ",";
			}
		}
		return cols;
	}

	private static void generate_all_tabled_crud() {
		// TODO Auto-generated method stub
		
		
		
		for(String table : classTableNames ){
			String tbl_name = table.replace("tbl", "_");
			String crud = "public function is_exists(Array $columns,Array  $records,$printSQL = false){return $this->is_exists($this->get_table(),$columns,$records,$printSQL);}public function insert_records_to"+tbl_name+"(Array $columns,Array  $records,$printSQL = false){return $this->insert_records($this->get_table(),$columns,$records,$printSQL);}public function delete_record_from"+tbl_name+"(Array $columns,Array  $records,$printSQL = false){return $this->delete_record($this->get_table(),$columns,$records,$printSQL);}public function update_record_in"+tbl_name+"(Array $columns, Array $records, Array $where_columns,Array $where_records,$printSQL = false){return $this->update_record($this->get_table(),$columns,$records,$where_columns,$where_records,$printSQL);}\npublic function fetch_assoc_in"+tbl_name+"(Array $columns,Array  $records,$printSQL = false){return $this->fetch_assoc($this->get_table(),$columns,$records,$printSQL);}\npublic function query_from"+tbl_name+"(Array $columns,Array $records,$printSQL = false){return $this->query($this->get_table(),$columns,$records,$printSQL);}\npublic function search_in"+tbl_name+"(Array $columns,Array $records,$printSQL = false){return $this->search($this->get_table(),$columns,$records,$printSQL);}\n";
			
			System.out.println("\\"+table.toUpperCase().replace("tbl_", ""));
		System.out.println(crud);
		System.out.println();
		System.out.println();
			}
		}
private static String getClassName(String classname) {
	classname = classname.replace("tbl_", "");
	return classname.substring(0, 1).toUpperCase() +classname.substring(1, classname.length());
}
}
