package com.thies.hw6_exercise1

import Database.CrimeDatabase
import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.UUID

// private property to store reference to database
private const val DATABASE_NAME = "crime-database"

// implementing GlobalScope in order to properly update user edits onto CrimeList
class CrimeRepository private constructor(context: Context, private val coroutineScope: CoroutineScope = GlobalScope) {

    private val database: CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME
    )
        .createFromAsset(DATABASE_NAME)
        .build()

    // adding functions that relate to each function in DAO
    fun getCrimes(): Flow<List<Crime>> = database.crimeDao().getCrimes()
    suspend fun getCrime(id: UUID): Crime = database.crimeDao().getCrime(id)
    // exposing new update crime function
    fun updateCrime(crime: Crime) {
        coroutineScope.launch {
            database.crimeDao().updateCrime(crime)
        }
    }

    companion object {
        private var INSTANCE: CrimeRepository? = null

        // MAKING CrimeRepository A SINGLETON
        // initializes a new instance of the repo
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }
        // accesses the repo
        fun get(): CrimeRepository {
            return INSTANCE ?:
            throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}