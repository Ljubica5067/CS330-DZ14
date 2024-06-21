package com.example.dz14_4400.business

import com.example.dz14_4400.data.Category
import com.example.dz14_4400.data.Company
import kotlinx.coroutines.flow.Flow

class UseCases {
    private val repository = Repository()
    var companyFlow: Flow<List<Company>> = repository.companyFlow

    suspend fun load() {
        repository.loadCompany()
        companyFlow = repository.companyFlow
    }

    suspend fun loadAdsForCategory(category: Category) {
        println("loadAdsForCategory $category")
        companyFlow = repository.companyFlow.getPriority(category)

    }
}