package com.example.domain.utils

import com.example.entities.Constants.Companion.BASE_URL_IMAGE
import com.example.entities.localmodels.*
import com.example.entities.responcemodels.*

fun PopularMoveItemResponse.toLocalPopularMove() = PopularMoveItem(
    popularity = popularity,
    id = id,
    video = video,
    scoreCount = voteCount?.div(voteAverage!!)?.toInt(),
    title = title,
    releaseDate = releaseDate,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    genreIds = genreIds,
    backdropPath = BASE_URL_IMAGE + backdropPath,
    adult = adult,
    overview = overview,
    posterPath = BASE_URL_IMAGE + posterPath
)

fun DetailMoveResponse.toDetailMove() = DetailMove(
    adult = adult,
    backdropPath = BASE_URL_IMAGE + backdropPath,
    belongsToCollection = belongsToCollection?.toBelongsToCollection(),
    budget = budget,
    genres = genres?.toGenre(),
    homepage = homepage,
    id = id,
    imdbId = imdbId,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = BASE_URL_IMAGE + posterPath,
    productionCompanies = productionCompanies?.toProductionCompany(),
    productionCountries = productionCountries?.toProductionCountry(),
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    spokenLanguages = spokenLanguages?.toSpokenLanguage(),
    status = status,
    tagline = tagline,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)

private fun BelongsToCollectionResponse.toBelongsToCollection() = BelongsToCollection(
    id = id,
    name = name,
    posterPath = posterPath,
    backdropPath = backdropPath,
)

private fun List<GenreResponse>.toGenre(): List<Genre> {
    val genreList: MutableList<Genre> = mutableListOf()
    forEach {
        genreList.add(Genre(it.id, it.name))
    }
    return genreList
}

private fun List<ProductionCompanyResponse>.toProductionCompany(): List<ProductionCompany> {
    val productionCompanyList: MutableList<ProductionCompany> = mutableListOf()
    forEach {
        productionCompanyList.add(ProductionCompany(it.id, it.logoPath, it.name, it.originCountry))
    }
    return productionCompanyList
}

private fun List<ProductionCountryResponse>.toProductionCountry(): List<ProductionCountry> {
    val productionCountryList: MutableList<ProductionCountry> = mutableListOf()
    forEach {
        productionCountryList.add(ProductionCountry(it.iso31661, it.name))
    }
    return productionCountryList
}

private fun List<SpokenLanguageResponse>.toSpokenLanguage(): List<SpokenLanguage> {
    val spokenLanguageList: MutableList<SpokenLanguage> = mutableListOf()
    forEach {
        spokenLanguageList.add(SpokenLanguage(it.iso6391, it.name))
    }
    return spokenLanguageList
}

fun TopCastResponse.toCast() = TopCast(
    id = id,
    cast = cast?.toTopCast(),
    crew = crew?.toCrew()
)

private fun List<CastResponse>.toTopCast(): List<Cast> {
    val cast: MutableList<Cast> = mutableListOf()
    forEach {
        cast.add(
            Cast(
                it.castId,
                it.character,
                it.creditId,
                it.gender,
                it.id,
                it.name,
                it.order,
                BASE_URL_IMAGE + it.profilePath
            )
        )
    }
    return cast
}


private fun List<CrewResponse>.toCrew(): List<Crew> {
    val crew: MutableList<Crew> = mutableListOf()
    forEach {
        crew.add(
            Crew(
                it.creditId,
                it.department,
                it.gender,
                it.id,
                it.job,
                it.name,
                BASE_URL_IMAGE + it.profilePath
            )
        )
    }
    return crew
}




