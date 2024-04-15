package data.database

import com.example.vetfdaportal.Result
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
    suspend fun getAllData(): Flow<List<Result>>

    suspend fun insertData(
        id: Long? = null,
        resultId: String?,
        animalSpecies: String?,
        animalAge: Double?,
        animalAgeUnit: String?,
        animalBreedComponent: String?,
        drugActiveIngredients: String?,
        seriousAdverseEvent: String?
    )

    suspend fun deleteData()
}