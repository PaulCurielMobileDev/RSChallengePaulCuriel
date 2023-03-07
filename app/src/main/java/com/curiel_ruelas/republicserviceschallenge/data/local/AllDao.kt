package com.curiel_ruelas.republicserviceschallenge.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.curiel_ruelas.republicserviceschallenge.data.models.Driver
import com.curiel_ruelas.republicserviceschallenge.data.models.Route
import com.curiel_ruelas.republicserviceschallenge.utils.Constants

@Dao
interface AllDao {
    @Query("SELECT * FROM ${Constants.TABLE_DRIVERS}")
    fun getAllDrivers() : List<Driver>

    @Query("SELECT * FROM ${Constants.TABLE_DRIVERS} ORDER BY name")
    fun getSortedDrivers() : List<Driver>


    @Query("SELECT * FROM ${Constants.TABLE_DRIVERS} WHERE id = :id")
    fun getDriver(id:String) : List<Driver>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDrivers(drivers: List<Driver>)

    @Query("SELECT * FROM ${Constants.TABLE_ROUTES}")
    fun getAllRoutes() : List<Route>

    @Query("SELECT * FROM ${Constants.TABLE_ROUTES} WHERE id = :id")
    fun getRoute(id:Int) : List<Route>

    @Query("SELECT * FROM (" +
                            "SELECT * FROM (" +
                                            "SELECT * FROM ${Constants.TABLE_ROUTES} AS A WHERE A.id = :id "+
                                            ") " +
                            "UNION SELECT * FROM ("+
                                            " SELECT * FROM ${Constants.TABLE_ROUTES} AS B WHERE B.type = 'R' AND (:id % 2 = 0)  LIMIT 1" +
                                            ") "+
                           ") "+
            " UNION  SELECT * FROM ("+
                                    "SELECT id, type, name FROM ${Constants.TABLE_ROUTES} AS C WHERE C.type = 'C' AND (:id % 5) = 0  LIMIT 1 OFFSET 1"+
                                    ") "
            )
    fun getRoutesFilters(id: String): List<Route>

    @Query("SELECT * FROM ${Constants.TABLE_ROUTES} WHERE type = 'I' ORDER BY id DESC LIMIT 1")
    fun getLastI() : List<Route>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRoutes(drivers: List<Route>)

}