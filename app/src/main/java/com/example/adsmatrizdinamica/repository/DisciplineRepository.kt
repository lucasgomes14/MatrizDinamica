package com.example.adsmatrizdinamica.repository

import android.content.Context
import com.example.adsmatrizdinamica.model.DisciplineModel

class DisciplineRepository(context: Context) {

    private val disciplineDatabase = DisciplineDatabase.getDatabase(context).disciplineDAO()

    fun list() : List<DisciplineModel> {
        return disciplineDatabase.list()
    }

    fun insert(discipline: DisciplineModel) {
        disciplineDatabase.insert(discipline)
    }

    fun update(discipline: DisciplineModel) {
        disciplineDatabase.update(discipline)
    }

    fun get(id: Int) : DisciplineModel{
        return disciplineDatabase.get(id)
    }

    fun clear() {
        disciplineDatabase.clear()
    }
}