package data.database

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import org.chendrix.veteventportal.Database
import orgchendrixveteventportal.Result
import di.ApplicationScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.Inject

@Inject
@ApplicationScope
class LocalDataSourceImpl(db: Database) : ILocalDataSource {
    private val queries = db.databaseQueries

    override suspend fun getAllData(): Flow<List<Result>> {
        return queries.transactionWithResult {
            queries.getAllResult().asFlow().mapToList(Dispatchers.IO)
        }
    }

    override suspend fun deleteData() {
        queries.transaction {
            queries.deleteAllResult()
        }
    }

    override suspend fun insertData(
        id: Long?,
        resultId: String?,
        animalSpecies: String?,
        animalAge: Double?,
        animalAgeUnit: String?,
        animalBreedComponent: String?,
        drugActiveIngredients: String?,
        seriousAdverseEvent: String?
    ) {
        queries.transaction {
            queries.insertResult(
                id,
                resultId,
                animalSpecies,
                animalAge,
                animalAgeUnit,
                animalBreedComponent,
                drugActiveIngredients,
                seriousAdverseEvent
            )
        }
    }
}