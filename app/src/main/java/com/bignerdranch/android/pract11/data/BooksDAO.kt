package com.bignerdranch.android.pract11.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bignerdranch.android.pract11.data.models.KnigaTypes
import com.bignerdranch.android.pract11.data.models.KnigaZhanr

@Dao
interface BooksDAO{

    /* Таблица Книг*/
    @Query("SELECT * FROM $BOOKS_TABLE")
    fun getAllBooks(): LiveData<List<KnigaTypes>>

    @Insert
    fun addBook(knigaTypes: KnigaTypes)

    @Update
    fun saveBook(knigaTypes: KnigaTypes)

    //@Query("DELETE FROM $BOOKS_TABLE WHERE _id")
    //fun killBook()
    @Delete
    fun killBook(knigaTypes: KnigaTypes)

    /* Таблица Жанров*/
    @Query("SELECT * FROM $BOOKS_ZHANR_TABLE")
    fun getAllZhanrs(): LiveData<List<KnigaZhanr>>

    @Insert
    fun addZhanr(booksZhanr: KnigaZhanr)

    @Update
    fun saveZhanr(booksZhanrSave: KnigaZhanr)

    @Delete
    fun killZhanr(booksZhanrDelete: KnigaZhanr)

}