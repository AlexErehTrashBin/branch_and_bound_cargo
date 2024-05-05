import kotlin.math.min

fun solveCargoDistribution(cargoWeights: IntArray, truckCapacities: IntArray): Int {
    val n = cargoWeights.size
    val m = truckCapacities.size
    val dp = Array(n + 1) { IntArray(m + 1) }

    // Инициализация массива dp
    for (j in 1..m) {
        dp[0][j] = 0
    }
    for (i in 1..n) {
        dp[i][0] = i
    }

    // Заполнение массива dp
    for (i in 1..n) {
        for (j in 1..m) {
            if (cargoWeights[i - 1] <= truckCapacities[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1]
            } else {
                dp[i][j] = min(dp[i - 1][j], (dp[i][j - 1] + 1))
            }
        }
    }

    // Возвращаем минимальное количество автомобилей
    return dp[n][m]
}

fun main() {

    // Пример входных данных
    val cargoWeights = intArrayOf(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)
    val truckCapacities = intArrayOf(100, 100, 100, 100)


    // Решение задачи
    val requiredTrucks = solveCargoDistribution(cargoWeights, truckCapacities)


    // Вывод результата
    println("Требуется $requiredTrucks грузовиков.")
}