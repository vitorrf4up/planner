package com.planner.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.planner.database.TarefaRepository
import com.planner.models.Tarefa
import com.planner.models.ValidarTarefas
import com.planner.services.ApiService
import java.time.LocalDateTime

class CadastroViewModel(application: Application) : AndroidViewModel(application) {
    private var txtToast = MutableLiveData<String>()
    private var validacao = ValidarTarefas()
    private var tarefaRepository = TarefaRepository(application.applicationContext)
    private var tarefaFromDB = MutableLiveData<Tarefa>()
    private var apiService =  ApiService()

    fun getTarefaFromDB() : LiveData<Tarefa> {
        return tarefaFromDB
    }

    fun getTxtToast() : LiveData<String> {
        return txtToast
    }

    fun findTarefa(id: Int) {
        tarefaFromDB.value = tarefaRepository.getTarefa(id)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun salvarTarefa(titulo: String, descricao: String, dataFinal: LocalDateTime) : Tarefa? {
        if (validacao.verificarCampoVazio(titulo, descricao, dataFinal)){
            txtToast.value = "Preencha todos os campos!"

            return null
        }

        var tarefa = Tarefa(0, titulo, descricao, dataFinal)

        tarefa.id = tarefaRepository.salvarTarefa(tarefa).toInt()
        if (tarefa.id <= 0) {
            txtToast.value = "Erro ao tentar salvar tarefa. Tente novamente mais tarde"
            return null
        }

        apiService.adicionarTarefa(tarefa)

        txtToast.value = "Tarefa cadastrada com sucesso!"
        return tarefa
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun atualizarTarefa(tarefa: Tarefa) : Boolean {
        if (validacao.verificarCampoVazio(tarefa.titulo, tarefa.descricao,
                tarefa.dataFinal)) {
            txtToast.value = "Informe o nome da tarefa"
            return false
        }

        tarefaRepository.atualizarTarefa(tarefa)
        txtToast.value = "Tarefa atualizada"

        apiService.atualizarTarefa(tarefa)

        return true
    }

}