package src.view
import Marciano
import MarcianoPremium
import MarcianoComMatematica
import java.util.Scanner


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
        3 -> MarcianoPremium().executar()
        4 -> println("Saindo do programa.")
        else -> println("Opção inválida. Tente novamente.")
      }

    } while (escolha != 4)
  }
}
