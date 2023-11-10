import src.enums.Resposta
import java.util.Locale

open class Marciano {
    open fun responda(input: String, operando1: String = "", operando2: String = "") {
        val resposta = encontrarResposta(input)
        println(resposta.resposta)
    }

    protected fun encontrarResposta(input: String): Resposta {
        return when {
            input.contains("EU", ignoreCase = true) -> Resposta.EU
            input.isBlank() -> Resposta.VAZIA
            input == input.uppercase(Locale.getDefault()) -> {
                if (input.contains("?")) {
                    Resposta.GRITAR_COM_PERGUNTA
                } else {
                    Resposta.GRITAR
                }
            }
            input.contains("?") -> Resposta.PERGUNTA
            else -> Resposta.OUTRA_COISA
        }
    }

    open fun executar(){
        var input: String?

        println("Digite comandos ao robô Marciano (ou 'FIM' para encerrar):")

        while (true) {
            print("Comando => ")
            input = readLine()
            if (input.equals("FIM", ignoreCase = true)) {
                break
            }
            responda(input ?: "")
        }
    }
}
