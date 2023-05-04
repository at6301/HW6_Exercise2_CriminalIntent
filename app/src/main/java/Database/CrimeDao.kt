package Database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.thies.hw6_exercise1.Crime
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface CrimeDao {
    // ADDING QUERY FUNCTIONS
    // asks Room to pull all columns for all rows in the crime database table
    @Query("SELECT * FROM crime")
    fun getCrimes(): Flow<List<Crime>>

    // asks Room to pull all columns from only the row whose id matches the ID value provided
    @Query("SELECT * FROM crime WHERE id=(:id)")
    suspend fun getCrime(id: UUID): Crime

    // query to update an entry already in database
    @Update
    suspend fun updateCrime(crime: Crime)
}