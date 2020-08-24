package com.example.domain.usecases

import com.example.data.datastore.PopularMoveRepository
import com.example.entities.Constants.Companion.API_KEY
import com.example.entities.Constants.Companion.LANGUAGE
import com.example.domain.interactors.PopularMoveInteractor
import com.example.entities.Constants.Companion.ERROR_NULL_DATA
import com.example.entities.Result
import com.example.entities.localmodels.QueryPopularDataBody
import com.example.entities.MoveAppException
import com.example.domain.utils.toLocalPopularMove
import com.example.entities.Constants.Companion.EXAPTION_FINISH_PAGINITION
import com.example.entities.localmodels.PopularMoveItem

class PopularMoveUseCase(private val popularMoveRepository: PopularMoveRepository) :
    PopularMoveInteractor {
    private val popularData: MutableList<PopularMoveItem> = mutableListOf()
    private var loadPageId: Int = 0
    private var totalPageCount: Int = 0
    override suspend fun getPopularMoveData(): Result<List<PopularMoveItem>> =
        analyzePopularData()

    private suspend fun analyzePopularData(): Result<List<PopularMoveItem>> {

        if (this.loadPageId <= totalPageCount) {
            this.loadPageId++
            val apiData = popularMoveRepository.getPopularMoveData(
                QueryPopularDataBody(
                    API_KEY, loadPageId,
                    LANGUAGE
                )
            )

            return when (apiData) {
                is Result.Success -> {
                    apiData.data?.let {
                        this.loadPageId = it.page!!
                        this.totalPageCount = it.total_pages!!
                        it.results?.run {
                            forEach {
                                popularData.add(it.toLocalPopularMove())
                            }
                        }
                    }

                    Result.Success(popularData)
                }
                else -> {
                    Result.Error(
                        MoveAppException(
                            ERROR_NULL_DATA,
                            null,
                            "Can't load popular move data into server"
                        )
                    )
                }
            }
        } else {
            return Result.Error(
                MoveAppException(
                    EXAPTION_FINISH_PAGINITION,
                    null,
                    "No more data"
                )
            )
        }
    }
}