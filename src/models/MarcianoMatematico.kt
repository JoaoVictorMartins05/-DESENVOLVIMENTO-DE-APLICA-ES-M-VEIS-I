

open class MarcianoComMatematica : Marciano() {

    override fun responda(input: String, operando1: String, operando2: String) {
        if (operando1.isNotBlank() || operando2.isNotBlank()) {
            try {
                val operando1Double = operando1.toDouble()
                val operando2Double = operando2.toDouble()

                operacaoMatematica(input, operando1Double, operando2Double)
            } catch (e: NumberFormatException) {
                println("Erro: Operando(s) não são números válidos.")
            }
        } else {
            val resposta = super.encontrarResposta(input)
            println(resposta.resposta)
        }
    }

    fun operacaoMatematica(operacao: String, operando1: Double, operando2: Double) {
        val resultado = when (operacao) {
            "some" -> operando1 + operando2
            "subtraia" -> operando1 - operando2
            "multiplique" -> operando1 * operando2
            "divida" -> {
                if (operando2.toInt() != 0) operando1 / operando2
                else {
                    println("Divisão por zero não é permitida.")
                    return
                }
            }

            else -> {
                println("Operação matemática inválida.")
                return
            }
        }
        println("Essa eu sei: $resultado")
    }

    override fun executar() {
        var input: String?

        println("Digite comandos ao robô Marciano Matemático (ou 'FIM' para encerrar)")
        println("O robô Marciano Matemático possui também os comandos : some, subtraia, multiplique ou divida")
        while (true) {
            print("Comando => ")
            input = readLine() ?: ""
            var operando1: String = ""
            var operando2: String = ""

            if (input.equals("FIM", ignoreCase = true)) {
                break
            }

            if (input == "some" || input == "subtraia" || input == "multiplique" || input == "divida") {
                print("Operando 1 => ")
                operando1 = readLine() ?: ""
                print("Operando 2 => ")
                operando2 = readLine() ?: ""
            }

            responda(input, operando1, operando2)
        }
    }
}
