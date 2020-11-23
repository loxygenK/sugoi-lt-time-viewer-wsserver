import com.google.gson.Gson
import types.LTInfo
import ws.Command
import ws.CommandResult
import ws.command

class LTInfoStore{

    val commandName: String = "RLT"
    var presentationInfo: String? = null;

    val command: Command = command("RLT") { cmd, ws ->
        if(presentationInfo == null) CommandResult.EMPTY
        ws?.sendText(cmd, "SLT#$presentationInfo")
        CommandResult.SUCCESS
    }

    fun store(ltInfo: LTInfo) {
        this.presentationInfo = Gson().toJson(ltInfo)
    }

}