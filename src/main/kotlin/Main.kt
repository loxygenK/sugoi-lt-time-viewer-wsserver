import types.LTInfo
import types.PresentationInfo
import types.Presenter
import ws.CommandParser

fun main() {
    val ltInfoStore = LTInfoStore()
    val commandParser = CommandParser()
    commandParser.registerCommand(ltInfoStore.command)

    ltInfoStore.store(
        LTInfo(
            "限界LT #2",
            "準備中です",
            PresentationInfo(
                Presenter(
                    "loxygen_k",
                    "フライさん"
                ),
                "ZshとFish、どちらを使うべきか",
                "無難で、ユーザ数も多いZshと、\n" +
                    "カスタマイズしやすく補完も強いがPOSIX非互換のFish、\n" +
                    "どちらも一長一短である。\n" +
                    "我々はどちらを選ぶべきだろうか？",
                arrayOf("環境構築", "Linux", "シェル"),
            )
        )
    )

    LTTimeViewerServer(12333, commandParser).start()
}