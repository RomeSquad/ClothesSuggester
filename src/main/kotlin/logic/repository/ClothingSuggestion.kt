package org.example.logic.repository

import logic.entity.Weather

interface ClothingSuggestion {
    fun condition(weather: Weather): Boolean
    fun getClothingLists(): List<String>
}