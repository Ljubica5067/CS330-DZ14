package com.example.dz14_4400.data

data class Company (
    val id:String,
    val avatar:String,
    val title : String,
    val description: String,
    val promet:String,
    val delatnost : Delatnost,
    val category: Category

        )