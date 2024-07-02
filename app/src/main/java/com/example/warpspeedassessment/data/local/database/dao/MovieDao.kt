package com.example.warpspeedassessment.data.local.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.warpspeedassessment.data.local.database.entities.MovieEntity
import com.example.warpspeedassessment.domain.models.Movie

@Dao
interface MovieDao {
    @Upsert
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM movie")
    suspend fun clearAll(): Int

    @Query("SELECT * FROM movie WHERE title LIKE '%' || :searchQuery || '%'")
    fun getMovies(searchQuery: String): PagingSource<Int, Movie>
}