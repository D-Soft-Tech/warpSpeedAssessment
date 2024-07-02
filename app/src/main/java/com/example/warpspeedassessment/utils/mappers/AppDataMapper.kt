package com.example.warpspeedassessment.utils.mappers

import com.example.warpspeedassessment.data.local.database.entities.MovieDetailsEntity
import com.example.warpspeedassessment.data.local.database.entities.MovieEntity
import com.example.warpspeedassessment.data.remote.dtos.Cast
import com.example.warpspeedassessment.data.remote.dtos.Crew
import com.example.warpspeedassessment.data.remote.dtos.Genre
import com.example.warpspeedassessment.data.remote.dtos.MovieCastsResponseDto
import com.example.warpspeedassessment.data.remote.dtos.MovieDetailsResponseDto
import com.example.warpspeedassessment.data.remote.dtos.Result
import com.example.warpspeedassessment.domain.models.Movie
import com.example.warpspeedassessment.domain.models.MovieCastDomain
import com.example.warpspeedassessment.domain.models.MovieCastsAndCrew
import com.example.warpspeedassessment.domain.models.MovieCrewDomain
import com.example.warpspeedassessment.domain.models.MovieDetails
import com.example.warpspeedassessment.domain.models.MovieGenreDomain
import com.google.gson.Gson

object AppDataMapper {
    fun Result.toMovie(pageNumber: Int): Movie =
        Movie(
            id.toString(),
            title,
            poster_path,
            backdrop_path,
            release_date,
            overview,
            vote_count.toString(),
            vote_average.toString(),
            pageNumber
        )

    fun MovieDetailsResponseDto.toMovieDetails() =
        MovieDetails(
            id.toString(),
            title,
            poster_path,
            backdrop_path,
            overview,
            release_date,
            runtime.runTimeToString(),
            approximateNumber(vote_count),
            vote_average.toInt().toString(),
            null,
            null,
            genres.map { it.toMovieGenreDomain() })

    private fun Genre.toMovieGenreDomain(): MovieGenreDomain = MovieGenreDomain(id, name)

    fun Crew.toMovieCrewDomain() =
        MovieCrewDomain(id.toString(), credit_id, job, name, profile_path)


    fun Cast.toMovieCastDomain() =
        MovieCastDomain(id.toString(), cast_id.toString(), name, profile_path)

    fun MovieCastsResponseDto.toMovieCastAndCrew(): MovieCastsAndCrew =
        MovieCastsAndCrew(
            id.toString(),
            cast.map { it.toMovieCastDomain() },
            crew.find { it.job.equals("Director", true) }?.toMovieCrewDomain()
        )

    fun Movie.toMovieEntity(): MovieEntity = MovieEntity(
        id.toInt(),
        title,
        posterPath,
        backDropPath,
        releaseDate,
        overview,
        voteCount,
        voteAverageRating,
        pageNumber
    )

    fun MovieEntity.toMovie(): Movie = Movie(
        id.toString(),
        title,
        posterPath,
        backDropPath,
        releaseDate,
        overview,
        voteCount,
        voteAverageRating,
        pageNumber
    )

    fun List<String>.toListOfMovieCastDomain(): List<MovieCastDomain> =
        this.map { Gson().fromJson(it, MovieCastDomain::class.java) }

    fun List<MovieGenreDomain>.toListOfString(): List<String> =
        this.map { Gson().toJson(it) }

    fun MovieDetails.toMovieDetailsEntity(): MovieDetailsEntity =
        MovieDetailsEntity(
            id,
            title,
            posterPath,
            backDropPath,
            overview,
            releaseDate,
            runTime,
            voteCount,
            voteAverageRating,
            directorName,
            casts,
            genres
        )

    fun MovieDetailsEntity.toMovieDetails(): MovieDetails = MovieDetails(
        id,
        title,
        posterPath,
        backDropPath,
        overview,
        releaseDate,
        runTime,
        voteCount,
        voteAverageRating,
        directorName,
        casts,
        genres
    )

    private fun Int.runTimeToString(): String {
        val hrs = this / 60
        val min = this % 60

        val hrsInString = if (hrs > 0) {
            "${hrs}h "
        } else ""

        val minInString = if (min > 0) {
            "${min}m"
        } else {
            ""
        }

        return "$hrsInString$minInString"
    }

    private fun approximateNumber(value: Int): String {
        return when {
            value < 1_000 -> "${(value / 100)}h"  // Hundreds
            value < 1_000_000 -> "${(value / 1_000)}k"  // Thousands
            value < 1_000_000_000 -> "${(value / 1_000_000)}m"  // Millions
            else -> "${(value / 1_000_000_000)}b"  // Billions
        }
    }
}