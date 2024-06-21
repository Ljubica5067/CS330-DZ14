package com.example.dz14_4400.network

import com.example.dz14_4400.data.Company
import retrofit2.http.*

interface ApiService {

    @GET(Constants.COMPANY_URL)
    suspend fun getAll(): List<Company>

    @POST(Constants.COMPANY_URL)
    suspend fun add(@Body ad: Company)

    @DELETE(Constants.COMPANY_URL+"/{id}")
    suspend fun delete(@Path("id") id: String)
}