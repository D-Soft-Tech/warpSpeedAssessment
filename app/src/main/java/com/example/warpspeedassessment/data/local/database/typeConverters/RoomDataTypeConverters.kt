package com.example.warpspeedassessment.data.local.database.typeConverters

import androidx.room.TypeConverter
import com.example.warpspeedassessment.data.local.database.entities.MovieDetailsEntity
import com.example.warpspeedassessment.data.local.database.entities.MovieEntity
import com.example.warpspeedassessment.domain.models.Movie
import com.example.warpspeedassessment.domain.models.MovieCastDomain
import com.example.warpspeedassessment.domain.models.MovieDetails
import com.example.warpspeedassessment.domain.models.MovieGenreDomain
import com.example.warpspeedassessment.utils.mappers.AppDataMapper.toMovie
import com.example.warpspeedassessment.utils.mappers.AppDataMapper.toMovieDetails
import com.example.warpspeedassessment.utils.mappers.AppDataMapper.toMovieDetailsEntity
import com.example.warpspeedassessment.utils.mappers.AppDataMapper.toMovieEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomDataTypeConverters {
    companion object {
        @JvmStatic
        @TypeConverter
        fun fromMovieCastDomain(movieCastDomain: MovieCastDomain): String =
            Gson().toJson(movieCastDomain)

        @JvmStatic
        @TypeConverter
        fun fromListOfMovieCastDomain(movieCasts: List<MovieCastDomain>): String {
            val gson = Gson()
            val type = object : TypeToken<List<MovieCastDomain>>() {}.type
            return gson.toJson(movieCasts, type) ?: ""
        }

        @JvmStatic
        @TypeConverter
        fun toListOfMovieCastDomain(movieCastsAsString: String): List<MovieCastDomain>? {
            val gson = Gson()
            val type = object : TypeToken<List<MovieCastDomain>>() {}.type
            return gson.fromJson(movieCastsAsString, type)
        }

        @JvmStatic
        @TypeConverter
        fun fromListOfMovieGenreDomain(movieGenres: List<MovieGenreDomain>): String {
            val gson = Gson()
            val type = object : TypeToken<List<MovieGenreDomain>>() {}.type
            return gson.toJson(movieGenres, type) ?: ""
        }

        @JvmStatic
        @TypeConverter
        fun toListOfMovieGenreDomain(movieGenreAsString: String): List<MovieGenreDomain>? {
            val gson = Gson()
            val type = object : TypeToken<List<MovieGenreDomain>>() {}.type
            return gson.fromJson(movieGenreAsString, type)
        }

        @JvmStatic
        @TypeConverter
        fun toMovieCastDomain(movieCastDomainInStringForm: String): MovieCastDomain =
            Gson().fromJson(movieCastDomainInStringForm, MovieCastDomain::class.java)

        @JvmStatic
        @TypeConverter
        fun fromMovieGenreDomain(movieGenreDomain: MovieGenreDomain): String =
            Gson().toJson(movieGenreDomain)

        @JvmStatic
        @TypeConverter
        fun toMovieGenreDomain(movieGenreInStringForm: String): MovieGenreDomain =
            Gson().fromJson(movieGenreInStringForm, MovieGenreDomain::class.java)

        @JvmStatic
        @TypeConverter
        fun toMovieEntity(movie: Movie): MovieEntity = movie.toMovieEntity()

        @JvmStatic
        @TypeConverter
        fun toMovie(movieEntity: MovieEntity): Movie = movieEntity.toMovie()

        @JvmStatic
        @TypeConverter
        fun toMovieDetailsEntity(movieDetails: MovieDetails): MovieDetailsEntity =
            movieDetails.toMovieDetailsEntity()

        @JvmStatic
        @TypeConverter
        fun toMovieDetails(movieDetailsEntity: MovieDetailsEntity): MovieDetails =
            movieDetailsEntity.toMovieDetails()
    }
}