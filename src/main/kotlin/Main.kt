import ws.CommandParser
import ws.CommandResult
import ws.command
import java.net.Socket

fun main() {

    val echoCommand = command("echo") { args, ws ->
        println("Received $args")
        ws?.send("Echoing ${args.joinToString(separator = " ")}")
        CommandResult.SUCCESS
    }

    val commandParser = CommandParser()
    commandParser.registerCommand(echoCommand)

    LTTimeViewerServer(12333, commandParser).start()
}