package ws

import org.java_websocket.WebSocket

class CommandParser {


    val commands: MutableList<Command> = mutableListOf()

    fun parse(text: String, conn: WebSocket?): CommandResult {
        val elements = text.split(" ")
        if(elements.isEmpty()) return CommandResult.EMPTY

        return commands
            .find { it.commandName == elements[0] }
            .notNullOr { always(CommandResult.PARSE_ERROR) }
            .execute(elements.subList(1, elements.size), conn)
    }

    fun registerCommand(command: Command) = commands.add(command)

    private fun always(result: CommandResult) = command("") { _, _ -> result }
}
fun <T> T?.notNullOr(ifNull: (T?) -> T): T = this ?: ifNull(this)