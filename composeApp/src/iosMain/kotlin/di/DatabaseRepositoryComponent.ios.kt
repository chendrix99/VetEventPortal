package di

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual fun provideDbDriver(schema: SqlSchema<QueryResult.Value<Unit>>): SqlDriver {
    return NativeSqliteDriver(schema, "result.db")
}