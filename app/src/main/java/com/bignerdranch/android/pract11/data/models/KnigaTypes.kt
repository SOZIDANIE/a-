package com.bignerdranch.android.pract11.data.models

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bignerdranch.android.pract11.data.BOOKS_TABLE

@Entity(tableName = BOOKS_TABLE)
data class KnigaTypes(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = BaseColumns._ID)
    val idKniga: Int,
    var name: String,
    var author: String,
    var numPages: String,
    //var zhanr: KnigaZhanr
)
