package com.planner.models

import android.annotation.SuppressLint
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.planner.misc.DateConverter
import java.time.LocalDateTime

@Entity(tableName = "tarefas")
@SuppressLint("NewApi")
data class Tarefa(
    @ColumnInfo @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo var titulo: String,
    @ColumnInfo var descricao: String,
    @ColumnInfo @TypeConverters(DateConverter::class) var dataFinal: LocalDateTime,
    @ColumnInfo var status: STATUS? = STATUS.PENDENTE
) {
    fun mudarStatus() {
        status = if (status == STATUS.PENDENTE) {
            STATUS.COMPLETA
        } else {
            STATUS.PENDENTE
        }
    }
}

enum class STATUS {
    PENDENTE,
    COMPLETA
}


