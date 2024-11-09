package com.example.adsmatrizdinamica.viewmodel

import android.app.Application
import androidx.collection.objectFloatMap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adsmatrizdinamica.model.DisciplineModel
import com.example.adsmatrizdinamica.repository.DisciplineRepository

class MainViewmodel(application: Application) : AndroidViewModel(application) {

    private val repository = DisciplineRepository(application)

    private val _listDiscipline = MutableLiveData<List<DisciplineModel>>()
    val listDiscipline: LiveData<List<DisciplineModel>> = _listDiscipline

    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int> = _progress

    private fun progressBar() {

        val list = repository.list()
        var disciplineChecked = 0

        for (i in list) {
            if (i.checked) {
                disciplineChecked++
            }
        }

        _progress.value = 100 * disciplineChecked / 33
    }

    fun loadDisciplines() {
        _listDiscipline.value = repository.list()
    }

    /*
    * Methods when clicked.
    * Changes the value of checked, sees if it has dependents and prerequisites.
    * Calls other auxiliary functions to check and modify
    * */

    fun disciplineChecked(id: Int) {

        var discipline = get(id)

        if (discipline.prerequisite != "") {
            discipline.availability = !discipline.availability
            val preRequisite = converterStringFromInteger(discipline.prerequisite)

            dependenceAvailability(preRequisite, discipline.id)

            if (discipline.checked) {
                disableDependents(preRequisite)
            }
        }

        discipline.checked = !discipline.checked

        if (discipline.dependencies != "") {
            val dependence = converterStringFromInteger(discipline.dependencies)

            if (dependence.size == 1) {
                onlyDependent(dependence[0])
                if (get(dependence[0]).checked) {
                    discipline.availability = true
                } else {
                    discipline.availability = false
                }
            } else {
                onlyDependent(dependence[0])
                onlyDependent(dependence[1])

                discipline = doubleDependence(discipline)
            }
        }

        update(discipline)
        progressBar()
    }

    /*
    * Methods when you have two dependencies
    * */

    private fun doubleDependence(discipline: DisciplineModel): DisciplineModel {
        when (discipline.id) {
            22, 23 -> if (get(7).checked && get(16).checked) {
                if (discipline.id == 22) {
                    discipline.availability = true

                    val discipline23 = get(23)
                    discipline23.availability = true

                    update(discipline23)
                } else {
                    discipline.availability = true

                    val discipline22 = get(22)
                    discipline22.availability = true

                    update(discipline22)
                }
            }
            26 -> {
                if (get(1).checked && get(8).checked) {
                    discipline.availability = true
                }
            }
            27 -> {
                if (get(7).checked && get(12).checked) {
                    discipline.availability = true
                }
            }
            29 -> {
                if (get(11).checked && get(23).checked) {
                    discipline.availability = true
                }
            }
            32 -> {
                if (get(12).checked && get(23).checked) {
                    discipline.availability = true
                }
            }
        }

        return discipline
    }

    /*
    * Methods that remove the availability of dependent disciplines
    * */

    private fun disableDependents(list: List<Int>) {
        for (i in list) {
            val discipline = get(i)
            discipline.checked = false
            discipline.availability = false

            update(discipline)

            if (discipline.prerequisite != "") {
                val preRequisite = converterStringFromInteger(discipline.prerequisite)
                disableDependents(preRequisite)
            }
        }
    }

    /*
    * Methods when you have a dependency
    * */

    private fun onlyDependent(dependence: Int) {

        val preRequisire = get(dependence)

        preRequisire.checked = true
        preRequisire.availability = false

        val list = converterStringFromInteger(preRequisire.prerequisite)
        dependenceAvailability(list, dependence)

        update(preRequisire)

        if (preRequisire.dependencies != "") {
            val list = converterStringFromInteger(preRequisire.dependencies)
            if (list.size == 2) {
                onlyDependent(list[1])
            }
            onlyDependent(list[0])
        }
    }

    /*
    * Methods that modify the availability of dependent disciplines
    * */

    private fun dependenceAvailability(list: List<Int>, preRequisiteId: Int) {
        for (i in list) {
            val discipline = get(i)

            val dependence = converterStringFromInteger(discipline.dependencies)

            if (dependence.size == 1 && dependence[0] == preRequisiteId) {
                discipline.availability = true
            } else if (dependence.size == 2) {
                if (get(dependence[0]).id == preRequisiteId && get(dependence[1]).checked) {

                    discipline.availability = true
                } else if (get(dependence[1]).id == preRequisiteId && get(dependence[0]).checked) {

                    discipline.availability = !discipline.availability
                }
            }

            update(discipline)
        }

    }

    /*
    * Methods that convert string to a list of integers
    * */

    private fun converterStringFromInteger(string: String): List<Int> {
        return string.split(", ").map { it.toInt() }
    }

    /*
    * Methods that the repository layer calls
    * */

    fun insert(discipline: DisciplineModel) {
        repository.insert(discipline)
    }

    private fun update(discipline: DisciplineModel) {
        repository.update(discipline)
    }

    fun get(id: Int): DisciplineModel {
        return repository.get(id)
    }

    fun clear() {
        repository.clear()
    }
}