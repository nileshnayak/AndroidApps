package com.pesit.grocerykart;


import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "GroceryList";

	// Contacts table name
	private static final String TABLE_GROCERY = "Grocery";

	// Contacts Table Columns names
	//private static final String KEY_ID = "id";
	private static final String KEY_NAME = "ItemName";
	private static final String KEY_QUANTITY = "Quantity";
	private static final String KEY_PRICE = "Price";
	private static final String KEY_DISTRIBUTOR = "Distributor";
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE = "CREATE TABLE " + TABLE_GROCERY + "("
				/*+ KEY_ID + " INTEGER PRIMARY KEY,"*/ + KEY_NAME + " TEXT PRIMARY KEY,"
				+ KEY_QUANTITY + " INTEGER," + KEY_PRICE + " INTEGER," +  KEY_DISTRIBUTOR + " TEXT"+")";
		db.execSQL(CREATE_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new medicine
	void addGrocery(GroceryList groclist) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		//values.put(KEY_ID, medicine.getID());
		values.put(KEY_NAME, groclist.getName()); // Contact Name
		values.put(KEY_QUANTITY, groclist.getQuantity()); // Contact Phone
		values.put(KEY_PRICE, groclist.getPrice());
		values.put(KEY_DISTRIBUTOR, groclist.getDistributor());
		// Inserting Row
		db.insert(TABLE_GROCERY, null, values);
		db.close(); // Closing database connection
	}
	
	// 
	GroceryList getGrocery(String name) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_GROCERY, new String[] {
				KEY_NAME, KEY_QUANTITY, KEY_PRICE, KEY_DISTRIBUTOR, }, KEY_NAME + "=?",
				new String[] {name }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		GroceryList grocery = new GroceryList(cursor.getString(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3));
		// return contact
		return grocery;
	}
	//get all names
	public List<String> getAllNames()
	{
		List<String> nameList=new ArrayList<String>();
		String selectQuery="SELECT * FROM "+TABLE_GROCERY;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				// Adding grocery to list
				nameList.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}

		 
		return nameList;
		
	}
	
	// Getting All items
	public List<GroceryList> getAllItems() {
		List<GroceryList> GrocList = new ArrayList<GroceryList>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_GROCERY;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				GroceryList grocery = new GroceryList();
				
				grocery .setName(cursor.getString(0));
				grocery .setQuantity(cursor.getInt(1));
				grocery .setPrice(cursor.getInt(2));
				grocery .setDistributor(cursor.getString(3));
				// Adding items to list
				GrocList.add(grocery);
			} while (cursor.moveToNext());
		}

		// return contact list
		return GrocList;
	}

	
		public int orderGrocery(GroceryList groclist) {
			
			GroceryList obj=getGrocery(groclist.getName());
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			
			values.put(KEY_NAME, groclist.getName()); // Contact Name
			values.put(KEY_QUANTITY,obj.getQuantity()+ groclist.getQuantity()); // Contact Phone
			values.put(KEY_PRICE, groclist.getPrice());
			values.put(KEY_DISTRIBUTOR, groclist.getDistributor());
	
			return db.update(TABLE_GROCERY, values, KEY_NAME + " = ?",
					new String[] { groclist.getName() });
			}
			
			
	
	
	public int updateGrocery(GroceryList groclist) {
		
		GroceryList obj=getGrocery(groclist.getName());
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		
		values.put(KEY_NAME, groclist.getName()); // Contact Name
		values.put(KEY_QUANTITY,obj.getQuantity()- groclist.getQuantity()); // Contact Phone
		values.put(KEY_PRICE, obj.getPrice());
		values.put(KEY_DISTRIBUTOR, obj.getDistributor());
		// updating row
		if(obj.getQuantity()>= groclist.getQuantity())
		{
		return db.update(TABLE_GROCERY, values, KEY_NAME + " = ?",
				new String[] { groclist.getName() });
		}
		else
		{
			
			return -1;
		}
		}
		

	// Deleting single contact
	public void deleteGrocery(String name) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_GROCERY, KEY_NAME + " = ?",
				new String[] { name });
		db.close();
	}
	
	public List<GroceryList> getAllItemsWithZeroQuantity() {
		List<GroceryList> GrocList = new ArrayList<GroceryList>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_GROCERY + " WHERE " + KEY_QUANTITY + "='0'" ;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				GroceryList groclist = new GroceryList();
				//medicine.setID(cursor.getInt(0));
				groclist.setName(cursor.getString(0));
				groclist.setQuantity(cursor.getInt(1));
				groclist.setPrice(cursor.getInt(2));
				groclist.setDistributor(cursor.getString(3));
				// Adding contact to list
				GrocList.add(groclist);
			} while (cursor.moveToNext());
		}
		
		return GrocList;
	}
	public Cursor getColumns()
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String[] queryCols=new String[]{"name"};

		Cursor cursor=db.query(true,TABLE_GROCERY, queryCols,null,null,null,null,null,null);
		return cursor;
	}



}

