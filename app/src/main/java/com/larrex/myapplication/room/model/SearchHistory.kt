package com.larrex.myapplication.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History")
data class SearchHistory(@PrimaryKey(autoGenerate = true) val id: Int? = null,val keyword:String)
