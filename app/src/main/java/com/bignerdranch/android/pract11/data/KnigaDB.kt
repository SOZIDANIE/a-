package com.bignerdranch.android.pract11.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.bignerdranch.android.pract11.data.converters.DateConverter
import com.bignerdranch.android.pract11.data.models.KnigaTypes
import com.bignerdranch.android.pract11.data.models.KnigaZhanr

@Database(entities = [KnigaTypes::class, KnigaZhanr::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class KnigaDB: RoomDatabase(){
    abstract fun knigaDAO(): BooksDAO

}