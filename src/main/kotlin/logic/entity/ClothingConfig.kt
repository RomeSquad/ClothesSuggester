package logic.entity


data class ClothingConfig(
    val freezingThreshold: Double = -5.0,
    val coldThreshold: Double = 5.0,
    val coolThreshold: Double = 15.0,
    val warmThreshold: Double = 25.0,
    val hotThreshold: Double = 35.0,
    val precipThreshold: Double = 2.5,
    val windThreshold: Double = 20.0,
    val humidityThreshold: Double = 70.0,
    val uvThreshold: Double = 6.0
)