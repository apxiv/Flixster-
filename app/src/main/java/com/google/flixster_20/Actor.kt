package com.google.flixster_20
import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Response(
    @SerialName("results")
    val response: List<Actor>?
)

@Keep
@Serializable
data class Actor(
    val name: String,
    @SerialName("profile_path") val profilePath: String?,
    @SerialName("known_for") val knownFor: List<KnownForItem>
) : java.io.Serializable

@Serializable
data class KnownForItem(
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("overview") val overview: String?,
    @SerialName("title") val title: String?=null
) : java.io.Serializable