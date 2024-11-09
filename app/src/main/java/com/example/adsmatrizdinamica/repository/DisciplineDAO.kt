package com.example.adsmatrizdinamica.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.adsmatrizdinamica.model.DisciplineModel

@Dao
interface DisciplineDAO {

    @Query("SELECT * FROM Discipline")
    fun list(): List<DisciplineModel>

    @Insert
    fun insert(discipline: DisciplineModel)

    @Update
    fun update(discipline: DisciplineModel)

    @Query("SELECT * FROM Discipline WHERE id = :id")
    fun get(id: Int) : DisciplineModel

    @Query("DELETE FROM Discipline")
    fun clear()
}