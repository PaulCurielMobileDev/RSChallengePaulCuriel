package com.curiel_ruelas.republicserviceschallenge.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.curiel_ruelas.republicserviceschallenge.utils.Constants

@Entity(tableName = Constants.TABLE_DRIVERS)
data class Driver(
    @PrimaryKey var id: String,
    var name: String
)
