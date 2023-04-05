package com.example.moodset

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime


@Dao
interface MOODDao {

    @Insert
    suspend fun insert(moodentity: moodentity)


    @Query("SELECT * FROM `mood-table`")
    fun fetchAllMoods():Flow<List<moodentity>>

    }

