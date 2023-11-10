import java.util.Locale
import java.util.Scanner

enum class Resposta(val resposta: String) {
  EU("A responsabilidade é sua"),
  VAZIA("Não me incomode"),
  GRITAR("Opa! Calma aí!"),
  GRITAR_COM_PERGUNTA("Relaxa, eu sei o que estou fazendo!"),
  OUTRA_COISA("Tudo bem, como quiser"),
  PERGUNTA( "Certamente")
}

interface AcaoPersonalizada {
  fun executarAcao()
}

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

class MarcianoPremium(private val acaoPersonalizada: String) : MarcianoComMatematica(), AcaoPersonalizada {
  override fun executarAcao() {
    println("É pra já! Realizando a ação personalizada: $acaoPersonalizada")
  }

  override fun executar() {
    super.executar()
  }
}

class MenuRobos {
  private val scanner = Scanner(System.`in`)
  private var escolha: Int = 0

  fun exibirMenu() {
    do {
      println("Escolha um tipo de robô Marciano:")
      println("1. Marciano Padrão")
      println("2. Marciano com Matemática")
      println("3. Marciano Premium")
      println("4. Sair")

      print("Digite o número da opção desejada: ")
      escolha = scanner.nextInt()

      when (escolha) {
        1 -> Marciano().executar()
        2 -> MarcianoComMatematica().executar()
        3 -> criarMarcianoPremium()
        4 -> println("Saindo do programa.")
        else -> println("Opção inválida. Tente novamente.")
      }

    } while (escolha != 4)
  }

  private fun criarMarcianoPremium() {
    print("Digite a ação personalizada para o Marciano Premium: ")
    val acaoPersonalizada = readLine() ?: ""
    //executarRobo(MarcianoPremium(acaoPersonalizada))
  }

}

fun main() {
  val menuRobos = MenuRobos()
  menuRobos.exibirMenu()
}