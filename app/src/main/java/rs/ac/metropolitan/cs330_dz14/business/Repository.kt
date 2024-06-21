package com.example.dz14_4400.business

import com.example.dz14_4400.data.Company
import com.example.dz14_4400.network.ApiService
import com.example.dz14_4400.network.RetrofitHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class Repository {
    var companyFlow: Flow<List<Company>> = flowOf(listOf<Company>())


    suspend fun loadCompany(){
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val company = apiService.getAll()
        companyFlow = flowOf(company)
    }

    suspend fun submitCompany(company: Company){
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val result = apiService.add(company)
    }

    suspend fun deleteCompany(id: String){
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val result = apiService.delete(id)
    }
}