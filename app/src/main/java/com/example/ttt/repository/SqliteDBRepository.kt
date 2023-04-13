package com.example.ttt.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.ttt.classs.Student



private const val DB_NAME = "_db_chetu"
private const val DB_VERSION = 1
private const val TABLE_NAME = "student"
private const val FNAME = "FirstName"
private const val LNAME = "LastName"
private const val PHONE = "Phone"
private const val SRNO = "Sr"

class SqliteDBRepository(private val context: Context) {

    val query =
        "CREATE TABLE $TABLE_NAME($SRNO INTEGER PRIMARY KEY AUTOINCREMENT, $FNAME TEXT, $LNAME TEXT, $PHONE TEXT)"
    val dbHelper = MyDbHelper(context)
    val sqliteDb = dbHelper.writableDatabase

    fun createData(fName: String, lName: String, phone: String) {
        val contentValue = ContentValues()
        contentValue.put(FNAME, fName)
        contentValue.put(LNAME, lName)
        contentValue.put(PHONE, phone)
        val id: Long = sqliteDb.insert(TABLE_NAME, null, contentValue)
        if (id > 0) {
            Toast.makeText(context, "Data Saved Successfully.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
        }
    }

    fun getAllData() : ArrayList<Student>{
        var listOfStudent = ArrayList<Student>()
        val columns = arrayOf(SRNO, FNAME, LNAME, PHONE)
        val cursor = sqliteDb.query(TABLE_NAME, columns, null, null, null, null, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                val srNo = cursor.getString(0)
                val fName = cursor.getString(1)
                val lName = cursor.getString(2)
                val phone = cursor.getString(3)
                val student = Student(srNo, fName, lName, phone)
                listOfStudent.add(student)

            } while (cursor.moveToNext())
        } else {
            Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
        }

        return listOfStudent
    }

    fun deleteSingleRecord(rowId: Int){
        val id = sqliteDb.delete(TABLE_NAME, "$SRNO=$rowId", null)
        if (id > 0){
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
        }

    }


    inner class MyDbHelper(private val context: Context) :
        SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
        override fun onCreate(sqliteDb: SQLiteDatabase?) {
            sqliteDb?.execSQL(query)
        }

        override fun onUpgrade(sqliteDb: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            // TODO("Not yet implemented")
        }

    }
}