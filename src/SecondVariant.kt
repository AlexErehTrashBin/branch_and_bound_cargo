data class Cargo(val weight: Int)

data class Truck(
    private var capacity: Int
): Iterable<Cargo> {
    private var cargos: MutableList<Cargo> = mutableListOf()

    fun canAddCargo(cargo: Cargo): Boolean {
        return this.capacity >= cargos.sumOf { it.weight } + cargo.weight
    }

    fun addCargo(cargo: Cargo) {
        if (canAddCargo(cargo)) {
            cargos.add(cargo)
        }
    }

    override fun iterator(): Iterator<Cargo> = cargos.iterator()
}

fun distributeCargos(cargos: MutableList<Cargo>, truckCapacity: Int): List<Truck> {
    val trucks = mutableListOf<Truck>()
    trucks.add(Truck(truckCapacity))
    cargos.sortWith { c1: Cargo, c2: Cargo -> c2.weight - c1.weight }

    for (cargo in cargos) {
        var loaded = false
        for (truck in trucks) {
            if (truck.canAddCargo(cargo)) {
                truck.addCargo(cargo)
                // Отбрасываем подзадачу
                loaded = true
                break
            }
        }
        if (!loaded) {
            val newTruck = Truck(truckCapacity)
            newTruck.addCargo(cargo)
            trucks.add(newTruck)
        }
    }

    return trucks
}

fun main() {
    val cargos = listOf(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)
        .map { Cargo(it) }
        .toMutableList()
    val result = distributeCargos(cargos, 100)
    println("Грузовиков использовано: " + result.size)
    for (truck in result) {
        println("Грузовик с грузами: ")
        for (cargo in truck) {
            println(cargo.weight)
        }
    }
}