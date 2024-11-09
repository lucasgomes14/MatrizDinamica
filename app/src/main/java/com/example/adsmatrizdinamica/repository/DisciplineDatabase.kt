package com.example.adsmatrizdinamica.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.adsmatrizdinamica.constants.Constants
import com.example.adsmatrizdinamica.model.DisciplineModel

@Database(entities = [DisciplineModel::class], version = 1)
abstract class DisciplineDatabase : RoomDatabase() {

    abstract fun disciplineDAO() : DisciplineDAO

    companion object {
        private lateinit var INSTANCE: DisciplineDatabase

         fun getDatabase(context: Context) : DisciplineDatabase {

             if (!::INSTANCE.isInitialized) {
                 synchronized(DisciplineDatabase::class) {
                     INSTANCE = Room.databaseBuilder(context, DisciplineDatabase::class.java, Constants.DISCIPLINE.DATABASE)
                         .allowMainThreadQueries()
                         .build()
                 }
             }
             return INSTANCE
         }
    }
}