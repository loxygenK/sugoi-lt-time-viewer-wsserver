package types

data class PresentationInfo(
        val presenter: Presenter = Presenter(),
        val title: String = "",
        val description: String = "",
        val tag: Array<String> = arrayOf()
)
