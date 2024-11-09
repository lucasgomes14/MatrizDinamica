package com.example.adsmatrizdinamica.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.adsmatrizdinamica.R
import com.example.adsmatrizdinamica.constants.Constants
import com.example.adsmatrizdinamica.databinding.ActivityMainBinding
import com.example.adsmatrizdinamica.model.DisciplineModel
import com.example.adsmatrizdinamica.viewmodel.MainViewmodel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewmodel: MainViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel = ViewModelProvider(this).get(MainViewmodel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        disciplines()
        viewmodel.loadDisciplines()

        // eventos
        binding.textAlgorithm.setOnClickListener(this)
        binding.textIc.setOnClickListener(this)
        binding.textMathematics.setOnClickListener(this)
        binding.textPlpt1.setOnClickListener(this)
        binding.textEnglish1.setOnClickListener(this)

        binding.textEnglish2.setOnClickListener(this)
        binding.textPoo.setOnClickListener(this)
        binding.textProbability.setOnClickListener(this)
        binding.textGraph.setOnClickListener(this)
        binding.textPlpt2.setOnClickListener(this)
        binding.textNetwork.setOnClickListener(this)

        binding.textDaw1.setOnClickListener(this)
        binding.textMethodology.setOnClickListener(this)
        binding.textEda.setOnClickListener(this)
        binding.textDesignPatterns.setOnClickListener(this)
        binding.textDb1.setOnClickListener(this)

        binding.textDb2.setOnClickListener(this)
        binding.textAdministration.setOnClickListener(this)
        binding.textRht.setOnClickListener(this)
        binding.textSo.setOnClickListener(this)
        binding.textSociety.setOnClickListener(this)
        binding.textAps.setOnClickListener(this)
        binding.textDaw2.setOnClickListener(this)

        binding.textMobile.setOnClickListener(this)
        binding.textEntrepreneurship.setOnClickListener(this)
        binding.textArtificialIntelligence.setOnClickListener(this)
        binding.textDaw3.setOnClickListener(this)
        binding.textProject1.setOnClickListener(this)

        binding.textInformationSecurity.setOnClickListener(this)
        binding.textManagement.setOnClickListener(this)
        binding.textProject2.setOnClickListener(this)
        binding.textTests.setOnClickListener(this)
        binding.textOptional.setOnClickListener(this)

        observe()

    }

    /*
    * Click event methods
    * */

    override fun onClick(v: View) {

        // First Semester

        if (v.id == R.id.text_algorithm) {
            handleChecked(1)
        }

        if (v.id == R.id.text_ic) {
            handleChecked(2)
        }

        if (v.id == R.id.text_mathematics) {
            handleChecked(3)
        }

        if (v.id == R.id.text_plpt_1) {
            handleChecked(4)
        }

        if (v.id == R.id.text_english_1) {
            handleChecked(5)
        }

        // Second Semester

        if (v.id == R.id.text_english_2) {
            handleChecked(6)
        }

        if (v.id == R.id.text_poo) {
            handleChecked(7)
        }

        if (v.id == R.id.text_probability) {
            handleChecked(8)
        }

        if (v.id == R.id.text_graph) {
            handleChecked(9)
        }

        if (v.id == R.id.text_plpt_2) {
            handleChecked(10)
        }

        if (v.id == R.id.text_network) {
            handleChecked(11)
        }

        // Third Semester

        if (v.id == R.id.text_daw_1) {
            handleChecked(12)
        }

        if (v.id == R.id.text_methodology) {
            handleChecked(13)
        }

        if (v.id == R.id.text_eda) {
            handleChecked(14)
        }

        if (v.id == R.id.text_design_patterns) {
            handleChecked(15)
        }

        if (v.id == R.id.text_db_1) {
            handleChecked(16)
        }

        // Fourth Semester

        if (v.id == R.id.text_db_2) {
            handleChecked(17)
        }

        if (v.id == R.id.text_administration) {
            handleChecked(18)
        }

        if (v.id == R.id.text_rht) {
            handleChecked(19)
        }

        if (v.id == R.id.text_so) {
            handleChecked(20)
        }

        if (v.id == R.id.text_society) {
            handleChecked(21)
        }

        if (v.id == R.id.text_aps) {
            handleChecked(22)
        }

        if (v.id == R.id.text_daw_2) {
            handleChecked(23)
        }

        // Fifth Semester

        if (v.id == R.id.text_mobile) {
            handleChecked(24)
        }

        if (v.id == R.id.text_entrepreneurship) {
            handleChecked(25)
        }

        if (v.id == R.id.text_artificial_intelligence) {
            handleChecked(26)
        }

        if (v.id == R.id.text_daw_3) {
            handleChecked(27)
        }

        if (v.id == R.id.text_project_1) {
            handleChecked(28)
        }

        // Sixth Semester

        if (v.id == R.id.text_information_security) {
            handleChecked(29)
        }

        if (v.id == R.id.text_management) {
            handleChecked(30)
        }

        if (v.id == R.id.text_project_2) {
            handleChecked(31)
        }

        if (v.id == R.id.text_tests) {
            handleChecked(32)
        }

        if (v.id == R.id.text_optional) {
            handleChecked(33)
        }
    }

    /*
    * Viewmodel observation methods
    * */

    @SuppressLint("SetTextI18n")
    private fun observe() {
        viewmodel.listDiscipline.observe(this) {
            for (i in it) {
                val view = disciplineIdElement(i.id)
                val textView = disciplineIdTextElement(i.id)

                if (i.availability) {
                    if (view != null && textView != null) {
                        disciplineAvailable(view, textView)
                    }
                }

                if (i.checked) {
                    if (view != null && textView != null) {
                        disciplineChecked(view, textView)
                    }
                }

                if (!i.availability && !i.checked) {
                    if (view != null && textView != null) {
                        disciplineNotAvailable(view, textView)
                    }
                }
            }
        }

        viewmodel.progress.observe(this) {
            binding.textProgress.text = it.toString() + Constants.PROGRESS_BAR.PORCENTAGE
            binding.progressBar.progress = it
        }
    }

    /*
    * Methods when the element is clicked
    * */

    private fun handleChecked(id: Int) {
        viewmodel.disciplineChecked(id)
        viewmodel.loadDisciplines()
    }

    /*
    * Methods that return a View
    * */

    private fun disciplineIdElement(id: Int): View? {
        return when (id) {
            1 -> binding.textAlgorithm
            2 -> binding.textIc
            3 -> binding.textMathematics
            4 -> binding.textPlpt1
            5 -> binding.textEnglish1
            6 -> binding.textEnglish2
            7 -> binding.textPoo
            8 -> binding.textProbability
            9 -> binding.textGraph
            10 -> binding.textPlpt2
            11-> binding.textNetwork
            12 -> binding.textDaw1
            13 -> binding.textMethodology
            14 -> binding.textEda
            15 -> binding.textDesignPatterns
            16 -> binding.textDb1
            17 -> binding.textDb2
            18 -> binding.textAdministration
            19 -> binding.textRht
            20 -> binding.textSo
            21 -> binding.textSociety
            22 -> binding.textAps
            23 -> binding.textDaw2
            24 -> binding.textMobile
            25 -> binding.textEntrepreneurship
            26 -> binding.textArtificialIntelligence
            27 -> binding.textDaw3
            28 -> binding.textProject1
            29 -> binding.textInformationSecurity
            30 -> binding.textManagement
            31 -> binding.textProject2
            32 -> binding.textTests
            33 -> binding.textOptional

            else -> null
        }
    }

    /*
    * Methods that return a TextView
    * */

    private fun disciplineIdTextElement(id: Int): TextView? {
        return when (id) {
            1 -> binding.textAlgorithm
            2 -> binding.textIc
            3 -> binding.textMathematics
            4 -> binding.textPlpt1
            5 -> binding.textEnglish1
            6 -> binding.textEnglish2
            7 -> binding.textPoo
            8 -> binding.textProbability
            9 -> binding.textGraph
            10 -> binding.textPlpt2
            11-> binding.textNetwork
            12 -> binding.textDaw1
            13 -> binding.textMethodology
            14 -> binding.textEda
            15 -> binding.textDesignPatterns
            16 -> binding.textDb1
            17 -> binding.textDb2
            18 -> binding.textAdministration
            19 -> binding.textRht
            20 -> binding.textSo
            21 -> binding.textSociety
            22 -> binding.textAps
            23 -> binding.textDaw2
            24 -> binding.textMobile
            25 -> binding.textEntrepreneurship
            26 -> binding.textArtificialIntelligence
            27 -> binding.textDaw3
            28 -> binding.textProject1
            29 -> binding.textInformationSecurity
            30 -> binding.textManagement
            31 -> binding.textProject2
            32 -> binding.textTests
            33 -> binding.textOptional

            else -> null
        }
    }

    /*
    * Methods that modify colors in the view
    * */

    private fun disciplineAvailable(view: View, textView: TextView) {
        view.setBackgroundResource(R.color.discipline_available_color)
        textView.setTextColor(ContextCompat.getColor(this, R.color.text_discipline_available_color))
    }

    private fun disciplineNotAvailable(view: View, textView: TextView) {
        view.setBackgroundResource(R.color.discipline_not_available_color)
        textView.setTextColor(ContextCompat.getColor(this, R.color.text_color))
    }

    private fun disciplineChecked(view: View, textView: TextView) {
        view.setBackgroundResource(R.color.light_green)
        textView.setTextColor(ContextCompat.getColor(this, R.color.text_color))
    }

    /*
    * Method that adds all subjects to the database, so that in the future if there is a change
    * in the curriculum it can be updated in the app
    * */

    private fun disciplines() {
        viewmodel.clear()

        val algorithm = DisciplineModel().apply {
            id = 1
            name = "algorithms and programming logic"
            semester = 1
            availability = true
            prerequisite = "7, 9, 26"
        }

        val ic = DisciplineModel().apply {
            id = 2
            name = "introduction to computing"
            semester = 1
            availability = true
            prerequisite = "11"
        }

        val mathematics = DisciplineModel().apply {
            id = 3
            name = "mathematics applied to computing"
            semester = 1
            availability = true
        }

        val plpt1 = DisciplineModel().apply {
            id = 4
            name = "reading practice and text production 1"
            semester = 1
            availability = true
            prerequisite = "10"
        }

        val english1 = DisciplineModel().apply {
            id = 5
            name = "instrumental english 1"
            semester = 1
            availability = true
            prerequisite = "6"
        }

        val english2 = DisciplineModel().apply {
            id = 6
            name = "instrumental english 2"
            semester = 2
            dependencies = "5"
        }

        val poo = DisciplineModel().apply {
            id = 7
            name = "object-oriented programming"
            semester = 2
            dependencies = "1"
            prerequisite = "14, 15, 22, 23, 24, 27"
        }

        val prob = DisciplineModel().apply {
            id = 8
            name = "probability and statistics"
            semester = 2
            availability = true
            prerequisite = "26"
        }

        val graph = DisciplineModel().apply {
            id = 9
            name = "logic and graph theory"
            semester = 2
            dependencies = "1"
        }

        val plpt2 = DisciplineModel().apply {
            id = 10
            name = "reading practice and text production 2"
            semester = 2
            dependencies = "4"
        }

        val networks = DisciplineModel().apply {
            id = 11
            name = "Introduction to Computer Networks"
            semester = 2
            dependencies = "2"
            prerequisite = "29"
        }

        val daw1 = DisciplineModel().apply {
            id = 12
            name = "web application development 1"
            semester = 3
            availability = true
            prerequisite = "27, 32"
        }

        val methodology = DisciplineModel().apply {
            id = 13
            name = "scientific research methodology"
            semester = 3
            availability = true
        }

        val eda = DisciplineModel().apply {
            id = 14
            name = "data structure and algorithm"
            semester = 3
            dependencies = "7"
        }

        val designPatterns = DisciplineModel().apply {
            id = 15
            name = "design patterns"
            semester = 3
            dependencies = "7"
            prerequisite = "20"
        }

        val db1 = DisciplineModel().apply {
            id = 16
            name = "database 1"
            semester = 3
            availability = true
            prerequisite = "17, 22, 23"
        }

        val db2 = DisciplineModel().apply {
            id = 17
            name = "database 2"
            semester = 4
            dependencies = "16"
        }

        val management = DisciplineModel().apply {
            id = 18
            name = "introduction to management"
            semester = 4
            availability = true
        }

        val humanRelationsAtWork = DisciplineModel().apply {
            id = 19
            name = "human relations at work"
            semester = 4
            availability = true
        }

        val operatingSystems = DisciplineModel().apply {
            id = 20
            name = "operating systems"
            semester = 4
            dependencies = "15"
        }

        val societyAndInformationTechnology = DisciplineModel().apply {
            id = 21
            name = "society and information technology"
            semester = 4
            availability = true
        }

        val systemsAnalysisAndDesign = DisciplineModel().apply {
            id = 22
            name = "systems analysis and design"
            semester = 4
            dependencies = "7, 16"
            prerequisite = "28"
        }

        val daw2 = DisciplineModel().apply {
            id = 23
            name = "web application development 2"
            semester = 4
            dependencies = "7, 16"
            prerequisite = "29, 30, 32"
        }

        val mobile = DisciplineModel().apply {
            id = 24
            name = "programming for mobile devices"
            semester = 5
            dependencies = "7"
        }

        val entrepreneurship = DisciplineModel().apply {
            id = 25
            name = "entrepreneurship"
            semester = 5
            availability = true
        }

        val ia = DisciplineModel().apply {
            id = 26
            name = "artificial intelligence"
            semester = 5
            dependencies = "1, 8"
        }

        val daw3 = DisciplineModel().apply {
            id = 27
            name = "web application development 3"
            semester = 5
            dependencies = "7, 12"
        }

        val project1 = DisciplineModel().apply {
            id = 28
            name = "software design 1"
            semester = 5
            dependencies = "22"
            prerequisite = "31"
        }

        val security = DisciplineModel().apply {
            id = 29
            name = "information security"
            semester = 6
            dependencies = "11, 23"
        }

        val configurationAndChangeManagement = DisciplineModel().apply {
            id = 30
            name = "configuration and change management"
            semester = 6
            dependencies = "23"
        }

        val project2 = DisciplineModel().apply {
            id = 31
            name = "software design 2"
            semester = 6
            dependencies = "28"
        }

        val testing = DisciplineModel().apply {
            id = 32
            name = "testing techniques"
            semester = 6
            dependencies = "12, 23"
        }

        val optional = DisciplineModel().apply {
            id = 33
            name = "optional"
            semester = 6
            availability = true
        }

        insertDiscipline(algorithm)
        insertDiscipline(ic)
        insertDiscipline(mathematics)
        insertDiscipline(plpt1)
        insertDiscipline(english1)

        insertDiscipline(english2)
        insertDiscipline(poo)
        insertDiscipline(prob)
        insertDiscipline(graph)
        insertDiscipline(plpt2)
        insertDiscipline(networks)

        insertDiscipline(daw1)
        insertDiscipline(methodology)
        insertDiscipline(eda)
        insertDiscipline(designPatterns)
        insertDiscipline(db1)

        insertDiscipline(db2)
        insertDiscipline(management)
        insertDiscipline(humanRelationsAtWork)
        insertDiscipline(operatingSystems)
        insertDiscipline(societyAndInformationTechnology)
        insertDiscipline(systemsAnalysisAndDesign)
        insertDiscipline(daw2)

        insertDiscipline(mobile)
        insertDiscipline(entrepreneurship)
        insertDiscipline(ia)
        insertDiscipline(daw3)
        insertDiscipline(project1)

        insertDiscipline(security)
        insertDiscipline(configurationAndChangeManagement)
        insertDiscipline(project2)
        insertDiscipline(testing)
        insertDiscipline(optional)

    }

    private fun insertDiscipline(discipline: DisciplineModel) {
        viewmodel.insert(discipline)
    }
}