package com.example.android.bcmliteapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.android.bcmliteapp.models.Plan;

import java.util.ArrayList;

import java.util.Iterator;

/**
 * Created by keket on 18/10/2017.
 */

public final class SQLiteContract {

    // Constructor is private to prevent someone
    // from accidentally instantiating the Contract class
    private SQLiteContract() {
    }

    /* Inner class that defines the table contents */
    public static class SQLitePlan implements BaseColumns {
        public static final String TABLE_NAME = "plan";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_INVOKED = "invoked";
        public static final String COLUMN_NAME_DEPARTMENT_NAME = "departmentName";
        public static final String COLUMN_NAME_DEPARTMENT_ID = "departmentID";

        public static void addPlans(Context context, ArrayList<Plan> planArrayList, SQLiteDatabase db) {

            // Delete all records from table
//            db.execSQL(SQLiteHelper.SQL_DELETE_TABLE);

            //Loop through planArrayList and rows to the database
            Iterator<Plan> it = planArrayList.iterator();
            Plan plan;
            while (it.hasNext()) {
                // Create a new map of values, where column names are the keys
                plan = it.next();

                ContentValues values = new ContentValues();
                values.put(COLUMN_NAME_NAME, plan.getName());
                values.put(COLUMN_NAME_DESCRIPTION, plan.getDescription());
                values.put(COLUMN_NAME_TYPE, plan.getType());
                values.put(COLUMN_NAME_INVOKED, plan.isInvoked());
                values.put(COLUMN_NAME_DEPARTMENT_NAME, plan.getDepartmentName());
                values.put(COLUMN_NAME_DEPARTMENT_ID, plan.getDepartmentID());

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(TABLE_NAME, null, values);
                Log.i("SQLitePlan", "New plan record inserted into SQLite: " + newRowId);
                Log.i("SQLitePlan", plan.toString());
            }
        }

        public static ArrayList<Plan> getPlans(SQLiteDatabase db) {

            ArrayList<Plan> plans = new ArrayList<Plan>();

            // Define columns select clause
            String[] projection = {
                    _ID,
                    COLUMN_NAME_NAME,
                    COLUMN_NAME_DESCRIPTION,
                    COLUMN_NAME_TYPE,
                    COLUMN_NAME_INVOKED,
                    COLUMN_NAME_DEPARTMENT_NAME,
                    COLUMN_NAME_DEPARTMENT_ID
            };

            Cursor cursor = db.query(
                    TABLE_NAME,                     // The table to query
                    projection,                     // The columns to return
                    null,                           // The columns for the WHERE clause
                    null,                           // The values for the WHERE clause
                    null,                           // don't group the rows
                    null,                           // don't filter by row groups
                    null                            // The sort order
            );

            //Populate Plan ArrayList

            int x = 0;
            while (cursor.moveToNext()) {
                //Get columns indexes based on names
                int idIndex = cursor.getColumnIndexOrThrow(_ID);
                int nameIndex = cursor.getColumnIndexOrThrow(COLUMN_NAME_NAME);
                int descriptionIndex = cursor.getColumnIndexOrThrow(COLUMN_NAME_DESCRIPTION);
                int typeIndex = cursor.getColumnIndexOrThrow(COLUMN_NAME_TYPE);
                int invokedIndex = cursor.getColumnIndexOrThrow(COLUMN_NAME_INVOKED);
                int departmentNameIndex = cursor.getColumnIndexOrThrow(COLUMN_NAME_DEPARTMENT_NAME);
                int departmentIdIndex = cursor.getColumnIndexOrThrow(COLUMN_NAME_DEPARTMENT_ID);

                Plan plan = new Plan(
                        cursor.getInt(idIndex),
                        cursor.getString(nameIndex),
                        cursor.getString(descriptionIndex),
                        cursor.getString(typeIndex),
                        cursor.getInt(invokedIndex),
                        cursor.getString(departmentNameIndex),
                        cursor.getInt(departmentIdIndex));
                plans.add(x++, plan);
            }
            cursor.close();
            db.close();
            return plans;

        }
    }

    //TODO: Create Schema for all other tables
}
