package mx.cetys.arambula.angel.micampus.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_perfil_list_view.*
import kotlinx.android.synthetic.main.perfil_row.*
import mx.cetys.arambula.angel.micampus.R.layout.activity_perfil_list_view
import mx.cetys.arambula.angel.micampus.model.Perfil
import mx.cetys.arambula.angel.micampus.model.PerfilAdapter
import mx.cetys.arambula.angel.micampus.model.Tutor

class PerfilListViewActivity : AppCompatActivity() {

    val perfiles: ArrayList<Perfil> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_perfil_list_view)

        fillPerfil()
        rv_perfil_list.layoutManager = LinearLayoutManager(this)
        rv_perfil_list.adapter = PerfilAdapter(perfiles, applicationContext)
    }

    fun fillPerfil() {
        perfiles.add(Perfil(
                "2",
                "1234",
                "",
                "Angel",
                "Arambula",
                "",
                2,
                2,
                Tutor("tutorA"),
                Tutor("tutorB"))
        )
        perfiles.add(Perfil(
                "2",
                "1234",
                "",
                "Shamira",
                "",
                "",
                2,
                2,
                Tutor("tutorA"),
                Tutor("tutorB"))
        )
        perfiles.add(Perfil(
                "3",
                "1234",
                "",
                "Andrea",
                "",
                "",
                2,
                2,
                Tutor("tutorA"),
                Tutor("tutorB"))
        )
        perfiles.add(Perfil(
                "4",
                "1234",
                "",
                "Ivannia",
                "",
                "",
                2,
                2,
                Tutor("tutorA"),
                Tutor("tutorB"))
        )
        perfiles.add(Perfil(
                "4",
                "1234",
                "",
                "Luis",
                "",
                "",
                2,
                2,
                Tutor("tutorA"),
                Tutor("tutorB"))
        )
        perfiles.add(Perfil(
                "5",
                "1234",
                "",
                "Julio",
                "",
                "",
                2,
                2,
                Tutor("tutorA"),
                Tutor("tutorB"))
        )
    }
}
