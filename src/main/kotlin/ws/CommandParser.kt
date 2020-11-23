package ws

import org.java_websocket.WebSocket

class CommandParser {
    val commands: MutableList<Command> = mutableListOf()

    fun parse(text: String, conn: WebSocket?): CommandResult {
        val commandName = text.split("#")[0]
        return commands
            .find { it.commandName == commandName }
            .notNullOr { always(CommandResult.PARSE_ERROR) }
            .execute(text, conn)
    }

    fun registerCommand(command: Command) = commands.add(command)

    private fun always(result: CommandResult) = command("") { _, _ -> result }
}
fun <T> T?.notNullOr(ifNull: (T?) -> T): T = this ?: ifNull(this)