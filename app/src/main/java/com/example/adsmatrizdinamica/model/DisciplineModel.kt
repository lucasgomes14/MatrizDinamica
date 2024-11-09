package com.example.adsmatrizdinamica.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Discipline")
class DisciplineModel{

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "semester")
    var semester: Int = 0

    @ColumnInfo(name = "dependencies")
    var dependencies: String = ""

    @ColumnInfo(name = "prerequisite")
    var prerequisite: String = ""

    @ColumnInfo(name = "checked")
    var checked: Boolean = false

    @ColumnInfo(name = "availability")
    var availability: Boolean = false
}