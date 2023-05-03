package Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thies.hw6_exercise1.Crime


@Database(entities = [Crime :: class], version=1, exportSchema = false)
// telling the database to use the functions in the class when converting date types
@TypeConverters(CrimeTypeConverters::class)
abstract class CrimeDatabase : RoomDatabase() {
    // function to generate an implementation of the DAO
    abstract fun crimeDao(): CrimeDao
}
