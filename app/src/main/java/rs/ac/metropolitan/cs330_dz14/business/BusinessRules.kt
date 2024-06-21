package com.example.dz14_4400.business

import com.example.dz14_4400.data.Category
import com.example.dz14_4400.data.Company
import com.example.dz14_4400.data.Delatnost
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun Flow<List<Company>>.getPriority(forCategory: Category): Flow<List<Company>> {
    return when (forCategory) {
        Category.U_SISTEMU -> {
            return flowOf(this.flatMapConcat { it.asFlow() }.toList()
                .sortedBy { it.delatnost == Delatnost.IT}
                .sortedBy { it.delatnost == Delatnost.ENTERTAINMENT }.reversed())
        }

        else -> {
            return flowOf(this.flatMapConcat { it.asFlow() }.toList()
                .sortedBy { it.category== forCategory }
                .sortedBy { it.delatnost == Delatnost.IT && it.category == forCategory }
                .sortedBy { it.delatnost == Delatnost.ENTERTAINMENT&& it.category == forCategory }
                .sortedBy { it.delatnost == Delatnost.TRADE && it.category == forCategory }
                .reversed())
        }
    }
}