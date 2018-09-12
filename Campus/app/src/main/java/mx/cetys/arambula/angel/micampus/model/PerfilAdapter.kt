package mx.cetys.arambula.angel.micampus.model

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.perfil_row.view.*
import mx.cetys.arambula.angel.micampus.R

class PerfilAdapter(val items: List<Perfil>, val context: Context) :
        RecyclerView.Adapter<PerfilAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(context).inflate(
                        R.layout.perfil_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt_perfilitos.text = items.get(position).nombre
        holder.txt_apellidon.text = items.get(position).apellido
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txt_perfilitos = view.txt_perfil
        val txt_apellidon = view.txt_apellido
    }
}