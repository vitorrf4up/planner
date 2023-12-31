package com.planner.view.viewholder

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.planner.R

class TarefaViewHolder(tarefaLayout: View) : RecyclerView.ViewHolder(tarefaLayout) {
    var txtNomeTarefa = tarefaLayout.findViewById<TextView>(R.id.txtNomeTarefa)
    var btnStatus = tarefaLayout.findViewById<Button>(R.id.btnStatus)
}