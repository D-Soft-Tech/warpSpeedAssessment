package com.example.warpspeedassessment.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.warpspeedassessment.utils.AppConstants.MOVIE_TABLE_NAME

@Entity(tableName = MOVIE_TABLE_NAME)
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val posterPath: String?,
    val backDropPath: String?,
    val releaseDate: String,
    val overview: String,
    val voteCount: String,
    val voteAverageRating: String,
    val pageNum: Int
)