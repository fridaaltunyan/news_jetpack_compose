package com.fridaaltunyan.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NewsItemEntity::class],
    version = 1
)
abstract class NewsDatabase : RoomDatabase() {
    abstract val dao: NewsDao
}