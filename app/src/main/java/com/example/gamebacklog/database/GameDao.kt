package com.example.gamebacklog.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gamebacklog.model.Game


@Dao
interface GameDao {

    // sort by title if they've been published the same exact day
    @Query("SELECT * FROM gameTable ORDER BY year, month, day, title")
    fun getAllGames(): LiveData<List<Game>>

    @Insert
    fun insertGame(game: Game)

    @Delete
    fun deleteGame(game: Game)

    @Query("DELETE FROM gameTable")
    suspend fun deleteAllGames()

    @Update
    suspend fun updateGame(game: Game)

}