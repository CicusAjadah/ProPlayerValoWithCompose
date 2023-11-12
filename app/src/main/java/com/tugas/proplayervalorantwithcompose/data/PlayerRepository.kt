package com.tugas.proplayervalorantwithcompose.data

import com.tugas.proplayervalorantwithcompose.models.Player
import com.tugas.proplayervalorantwithcompose.models.PlayersData

class PlayerRepository {
    fun getPlayers(): List<Player> {
        return PlayersData.players
    }

    fun searchPlayes(query: String): List<Player>{
        return PlayersData.players.filter {
            it.in_game_name.contains(query, ignoreCase = true) or it.name.contains(query, ignoreCase = true)
        }
    }
}