package di

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import org.chendrix.veteventportal.Database
import data.database.DatabaseRepository
import data.database.ILocalDataSource
import data.database.LocalDataSourceImpl
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

expect fun provideDbDriver(
    schema: SqlSchema<QueryResult.Value<Unit>>
): SqlDriver

@Component
abstract class DatabaseRepositoryComponent {
    abstract val repo: DatabaseRepository

    @Provides
    fun localDataSource(driver: SqlDriver): ILocalDataSource = LocalDataSourceImpl(Database(driver))

    @Provides
    fun sqlDriver(): SqlDriver = provideDbDriver(Database.Schema)
}