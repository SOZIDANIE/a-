package com.bignerdranch.android.pract11

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

object Books : BaseColumns {
    const val TABLE_NAME = "books"
    const val COLUMN_NAME = "name"
    const val COLUMN_AUTHOR = "author"
    const val COLUMN_NUM_PAGES = "numPages"
}

private const val SQL_CREATE = """
    create table if not exists ${Books.TABLE_NAME}
    (${BaseColumns._ID} integer primary key autoincrement,
    ${Books.COLUMN_NAME} text,
    ${Books.COLUMN_AUTHOR} text,
    ${Books.COLUMN_NUM_PAGES} text);"""

private const val SQL_DELETE = "drop table ${Books.TABLE_NAME}"

class BookDbHelper (context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION, null){

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE)
        onCreate(db)
    }

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "books_database.db"
    }
}