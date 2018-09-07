package mx.cetys.arambula.angel.micampus.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import mx.cetys.arambula.angel.micampus.R
import mx.cetys.arambula.angel.micampus.controller.Utils
import mx.cetys.arambula.angel.micampus.model.EndPoints
import mx.cetys.arambula.angel.micampus.model.Perfil

class MainActivity : AppCompatActivity() {
    private val utils = Utils()
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            obtenerPerfilRequest()
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        btn_login.setOnClickListener{ view ->
            obtenerPerfilRequest()
        }
    }

    private fun obtenerPerfilRequest() {
        val queue = Volley.newRequestQueue(this)

        val matricula = edt_matricula.text.toString()
        val password = edt_password.text.toString()
        val stringRequest = StringRequest(Request.Method.GET, EndPoints.PERFIL.url.toString(),
                Response.Listener { response ->
                    val perfiles = gson.fromJson(response, Array<Perfil>::class.java).toList()
                    val perfil = utils.buscarPerfilN(perfiles, matricula, password)

                    if(perfil != null) {
                        val intent = Intent(applicationContext, MainMenuActivity::class.java)
                        //intent.putExtra("nombre",perfil.nombre)
                        intent.putExtra("perfildepersona", perfil)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(
                                applicationContext, "Contraseña erronea", Toast.LENGTH_SHORT).show()
                    }

                }, Response.ErrorListener { error ->
            Toast.makeText(
                    applicationContext, error.message, Toast.LENGTH_SHORT).show()
        })
        queue.add(stringRequest)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
