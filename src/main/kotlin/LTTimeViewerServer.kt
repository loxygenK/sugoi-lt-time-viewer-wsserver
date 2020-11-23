import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import ws.CommandParser
import ws.CommandResult
import java.lang.Exception
import java.net.InetSocketAddress

class LTTimeViewerServer(openPort: Int, val commandParser: CommandParser): WebSocketServer(InetSocketAddress(openPort)) {

    override fun onOpen(conn: WebSocket?, handshake: ClientHandshake?) {
        if(conn == null) {
            println("--> Connected, but connection information was null.")
            return
        }
        println("--> Connected! (${conn.remoteSocketAddress.address.hostAddress})")
    }

    override fun onClose(conn: WebSocket?, code: Int, reason: String?, remote: Boolean) {
        if(conn == null) {
            println("--> Client left, but connection information was null.")
            return
        }
        println("--> Client Left${if(remote) " by client" else ""}. [$reason](${conn.remoteSocketAddress.address.hostAddress})")
    }

    override fun onMessage(conn: WebSocket?, message: String?) {
        if(conn == null) return
        if(message?.startsWith("SYSTEM|") != false) return
        println("RX<< \"${message}\"")

        val result = commandParser.parse(message.substring(7), conn)
        println("-->  Result: $result")
        when(result) {
            CommandResult.SUCCESS -> {}
            CommandResult.FAILED -> {}
            CommandResult.PARSE_ERROR -> {conn.sendText(message, "? PARSE-ERR")}
            CommandResult.EMPTY -> {conn.sendText(message, "? EMPTY-QUERY")}
        }
    }

    override fun onError(conn: WebSocket?, ex: Exception?) {
        ex?.printStackTrace() ?: println("Error has reported, but the exception was null (wtf)")
    }

    override fun onStart() {
        println("--> Server started!")
    }

}

fun WebSocket.sendText(cmd: String, text: String) {
    this.send("SYSTEM|${cmd}>>${text}")
}

