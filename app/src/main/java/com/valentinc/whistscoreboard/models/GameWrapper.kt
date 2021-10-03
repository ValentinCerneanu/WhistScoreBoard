package com.valentinc.whistscoreboard.models

data class GameWrapper(
    var game: Game,
    var users: List<User>
)