package com.curiel_ruelas.republicserviceschallenge.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.curiel_ruelas.republicserviceschallenge.utils.Constants

@Entity(tableName = Constants.TABLE_ROUTES)
data class Route(
    @PrimaryKey var id:Int,
    var type:String,
    var name:String
)
