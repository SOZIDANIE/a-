package com.bignerdranch.android.pract11.data.models

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bignerdranch.android.pract11.data.BOOKS_ZHANR_TABLE
import java.util.*

@Entity(tableName =  BOOKS_ZHANR_TABLE)
data class KnigaZhanr(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = BaseColumns._ID)
    val id: Int,
    @ColumnInfo(index =  true)
    var typeId: Int = 0,
    var zhanr: String,
    var buyDate: Date
)
