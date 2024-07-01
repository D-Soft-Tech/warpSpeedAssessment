package com.example.warpspeedassessment.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.warpspeedassessment.domain.models.MovieCastDomain
import com.example.warpspeedassessment.domain.models.MovieGenreDomain
import com.example.warpspeedassessment.utils.AppConstants.MOVIE_DETAILS_TABLE_NAME

@Entity(tableName = MOVIE_DETAILS_TABLE_NAME)
data class MovieDetailsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val posterPath: String,
    val backDropPath: String,
    val overview: String,
    val releaseDate: String,
    val runTime: String,
    val voteCount: String,
    val voteAverageRating: String,
    var directorName: String? = null,
    var casts: List<MovieCastDomain>? = null,
    val genres: List<MovieGenreDomain>,
)