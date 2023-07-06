package com.example.influxapp.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Switch
import com.example.influxapp.database.models.UserModel

class DataBaseHelper(context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME="infux.db"
        private const val DATABASE_VERSION = 1
        private const val TBL_USER="user_table"
        private const val USERNAME = "username"
        private const val PASSWORD_ONE ="password_one"
        private const val PASSWORD_TWO = "password_two"
    }

    override fun onCreate(db:SQLiteDatabase?) {
//        val createUserTable = ("CREATE TABLE "+TBL_USER+"("
//                +USERNAME+"TEXT PRIMARY KEY,"+PASSWORD_ONE+"TEXT,"+PASSWORD_TWO+"TEXT"+")")
        db?.execSQL("CREATE TABLE $TBL_USER (username TEXT PRIMARY KEY, password_one TEXT," +
                "password_two TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_USER")
        onCreate(db)
    }

//    fun insertUser(user:UserModel): Long{
//        val db = this.writableDatabase
//
//        val contentValues = ContentValues()
//        contentValues.put(USERNAME,user.username)
//        contentValues.put(PASSWORD_ONE,user.password_one)
//        contentValues.put(PASSWORD_TWO,user.password_two)
//
//        val success = db.insert(TBL_USER,null,contentValues)
//        db.close()
//        return success
//    }

    @SuppressLint("Range")
    fun getAllUsers():ArrayList<UserModel>{
        val userArray : ArrayList<UserModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_USER"

        val db = this.readableDatabase
        val cursor:Cursor?

        try {
            cursor = db.rawQuery(selectQuery,null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var username:String
        var password_one:String
        var password_two:String

        if(cursor.moveToFirst()){
            do {
                username = cursor.getString(cursor.getColumnIndex("username"))
                password_one = cursor.getString(cursor.getColumnIndex("password_one"))
                password_two=cursor.getString(cursor.getColumnIndex("password_two"))

                val user = UserModel(username=username,password_one=password_one,password_two=password_two)
                userArray.add(user)
            }while(cursor.moveToNext())
        }
        return userArray
    }

    fun insertUserFunction(username:String,password:String,comPassword:String):Boolean{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("username",username)
        cv.put(PASSWORD_ONE,password)
        cv.put(PASSWORD_TWO,comPassword)
        val result = db.insert(TBL_USER,null,cv)

        if (result == -1.toLong()){
            return false
        }
        return true
    }

    fun userValidationFunction(username:String,password:String):Boolean{
        var isTrue :Boolean
        val db = this.writableDatabase
        val query = "SELECT * FROM $TBL_USER WHERE username= '$username' AND password_one= '$password'"
        val cursor = db.rawQuery(query,null)
        if(cursor.count<=0){
            cursor.close()
            isTrue = false
        }else{
            cursor.close()
            isTrue = true
        }

        return isTrue
    }
}