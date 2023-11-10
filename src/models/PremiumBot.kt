import src.interfaces.AcaoPersonalizada
import kotlin.random.Random


class MarcianoPremium() : MarcianoComMatematica(), AcaoPersonalizada {
    override fun executarAcaoPersonalizada() {
        println("É pra já! Realizando a ação personalizada!")
        jogarForca()
    }

    private fun jogarForca() {
        val palavras = listOf("gato", "cachorro", "elefante", "girafa", "tigre")
        val palavraSecreta = palavras[Random.nextInt(palavras.size)]
        val palavraEscondida = "_".repeat(palavraSecreta.length).toCharArray()

        val letrasErradas = mutableListOf<Char>()
        var chances = 6

        println("Bem-vindo ao jogo da forca!")
        println("A palavra tem ${palavraSecreta.length} letras.")
        println(palavraEscondida.joinToString(" "))

        while (chances > 0) {
            print("Digite uma letra: ")
            val tentativa = readLine()?.firstOrNull()

            if (tentativa!! !in palavraSecreta) {
                if (tentativa !in letrasErradas) {
                    letrasErradas.add(tentativa!!)
                    chances--
                    println("Letra incorreta! Restam $chances chances.")
                    println("Letras erradas: ${letrasErradas.joinToString(", ")}")
                } else {
                    println("Você já tentou esta letra. Tente outra.")
                }
            } else {
                for (i in palavraSecreta.indices) {
                    if (palavraSecreta[i] == tentativa) {
                        palavraEscondida[i] = tentativa!!
                    }
                }
                println(palavraEscondida.joinToString(" "))
            }

            if (!palavraEscondida.contains('_')) {
                println("Parabéns, você acertou a palavra!")
                return
            }
        }

        println("Suas chances acabaram! A palavra era: $palavraSecreta")
    }

    override fun executar() {
        var input: String?

        println("Digite comandos ao robô Marciano Prêmium (ou 'FIM' para encerrar)")
        println("O robô Marciano Prêmium possui comandos simples e matemáticos, além do comando 'Agir'")
        while (true) {
            print("Comando => ")
            input = readLine() ?: ""
            var operando1: String = ""
            var operando2: String = ""

            if (input.equals("FIM", ignoreCase = true)) {
                break
            }

            if(input.equals("AGIR", ignoreCase = true)){
                executarAcaoPersonalizada()
            }else{
                if (input == "some" || input == "subtraia" || input == "multiplique" || input == "divida") {
                    print("Operando 1 => ")
                    operando1 = readLine() ?: ""
                    print("Operando 2 => ")
                    operando2 = readLine() ?: ""
                }

                super.responda(input, operando1, operando2)
            }
        }

    }
}
