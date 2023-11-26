package com.example.pruebainterrapidisimo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebainterrapidisimo.R
import com.example.pruebainterrapidisimo.model.TableModel

class TablesAdapter(
    val context: Context,
    var listTables: List<TableModel>
): RecyclerView.Adapter<TablesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById(R.id.tvNameTable) as TextView
        val tvKey = itemView.findViewById(R.id.tvPrimaryKey) as TextView
        val tvColumns = itemView.findViewById(R.id.tvNumberColumns) as TextView
        val tvDate = itemView.findViewById(R.id.tvDate) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_table, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val table = listTables[position]

        holder.tvTitle.text = table.nombreTabla
        holder.tvKey.text = table.pk
        holder.tvColumns.text = table.numeroCampos.toString()
        holder.tvDate.text = table.fechaActualizacionSincro
    }

    override fun getItemCount(): Int {
        return listTables.size
    }

}