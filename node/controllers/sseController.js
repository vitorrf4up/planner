const express = require("express");
const sseService = require("../services/sseService");
const router = express.Router();

router.get("/connect", async (req, res) => {
    try {
        await sseService.connect(req, res);
    } catch (e) {
        console.log("Erro:", e);
    }
});

router.post("/adicionar", async (req, res) => {
    try {
        sseService.adicionarTarefa(req);

        res.status(200).json("Adicionado");
    } catch(e) {
        console.log("Erro:", e);
    }
});

router.delete("/deletar/:id", async (req, res) => {
    try {
        sseService.excluirTarefa(req);
        sseService.sendTarefasToWeb();

        res.status(200).json("Tarefa excluida");
    } catch (e) {
        console.log("Erro:", e);
    }
});

router.put("/atualizar", async (req, res) => {
    try {
        sseService.atualizarTarefa(req);
        sseService.sendTarefasToWeb();

        res.status(200).json("Tarefa atualizada");
    } catch (e) {
        console.log("Erro:", e);
    }
});

router.post("/replace", (req, res) => {
    try {
        sseService.replaceTarefas(req);

        res.status(200).json("Tarefas sincronizadas");
    } catch (e) {
        console.log("Erro:", e);
    }
});

module.exports = router;