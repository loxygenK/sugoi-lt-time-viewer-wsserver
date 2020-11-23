package ws

import org.java_websocket.WebSocket

interface Command {
    val commandName: String
    fun execute(cmd: String, conn: WebSocket?): CommandResult
}

fun command(commandName: String, executor: (cmd: String, conn: WebSocket?) -> CommandResult): Command {
    return object : Command {
        override val commandName: String = commandName
        override fun execute(cmd: String, conn: WebSocket?): CommandResult = executor(cmd, conn)
    }
}
