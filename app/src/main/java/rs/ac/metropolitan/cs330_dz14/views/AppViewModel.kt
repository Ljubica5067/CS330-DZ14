package com.example.dz14_4400.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dz14_4400.business.UseCases
import com.example.dz14_4400.data.Category
import com.example.dz14_4400.data.Company
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {
    private val business = UseCases()
    private var category: Category by mutableStateOf(Category.OTHER)
    var granted = mutableStateOf(false)
    var tabIndex by mutableStateOf(0)

    private var _company = MutableLiveData<List<Company>>()
    val company: LiveData<List<Company>>
        get() = _company

    @OptIn(DelicateCoroutinesApi::class)
    fun loadAdds() {
        GlobalScope.launch {
            business.load()
            println("In ViewModel (repository.companyFlow)")
            business.companyFlow.collect { value -> println(value) }
            MainScope().launch {
                business.companyFlow.collect {
                    _company.value = it
                }
            }
        }
    }


    fun setTabCategory(category: Category) {
        this.category = category
        MainScope().launch {
            business.loadAdsForCategory(category)
        }
    }

}