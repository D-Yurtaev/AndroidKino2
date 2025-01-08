package com.yuraev.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yuraev.database.dao.DocsDao
import com.yuraev.database.model.DocsDBO

class KinopoiskDataBase internal constructor(private val newsRoomDataBase: NewsRoomDataBase) {
    val  docsDao: DocsDao
        get() = newsRoomDataBase.docsDao()

}

@Database(entities = [DocsDBO::class], version = 1)

internal abstract class NewsRoomDataBase: RoomDatabase()  {
    abstract fun docsDao(): DocsDao
}

fun KinopoiskDataBase(applicationContext:Context): KinopoiskDataBase {

    val roomDataBase =  Room.databaseBuilder(
        checkNotNull(applicationContext.applicationContext),
        NewsRoomDataBase::class.java,
        "news"
    ).build()
    return KinopoiskDataBase(roomDataBase)
}
