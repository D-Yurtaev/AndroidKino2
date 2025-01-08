package com.yuraev.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.yuraev.database.model.DocsDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface DocsDao {


    @Query("Select * from docs")
    fun getAll(): Flow<List<DocsDBO>>

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insert(docs: List<DocsDBO>)

    @Query("SELECT COUNT(*) > 0 FROM docs WHERE id = :id")
    fun isExist(id: Long): Flow<Boolean>
}


