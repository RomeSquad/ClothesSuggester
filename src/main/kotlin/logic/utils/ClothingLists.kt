package org.example.logic.utils

object ClothingLists {
    val clothingForFreezeWeather = listOf(
        ClothingWears.HEAVY_COAT,
        ClothingWears.SCARF,
        ClothingWears.GLOVES,
        ClothingWears.THERMAL_SHIRT,
        ClothingWears.WARM_PANTS,
        ClothingWears.WOOL_SOCKS,
        ClothingWears.BOOTS
    )

    val clothingForColdWeather = listOf(
        ClothingWears.JACKET,
        ClothingWears.SWEATER,
        ClothingWears.LONG_PANTS,
        ClothingWears.CLOSED_SHOES
    )

    val clothingForWarmWeather = listOf(
        ClothingWears.LIGHT_JACKET,
        ClothingWears.HOODIE,
        ClothingWears.JEANS,
        ClothingWears.SNEAKERS
    )

    val clothingForHotWeather = listOf(
        ClothingWears.T_SHIRT,
        ClothingWears.SHORTS,
        ClothingWears.SANDALS
    )

    val clothingForRainyWeather = listOf(
        ClothingWears.UMBRELLA,
        ClothingWears.RAINCOAT,
        ClothingWears.WATERPROOF_SHOES
    )

    val clothingForUvIndexWeather = listOf(
        ClothingWears.SUN_HAT,
        ClothingWears.SUNGLASSES
    )

    val clothingForHumidityWeather = listOf(
        ClothingWears.BREATHABLE_FABRICS
    )
}