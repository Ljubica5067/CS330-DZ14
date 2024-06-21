package com.example.dz14_4400.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.dz14_4400.data.Category
import com.example.dz14_4400.data.Company
import com.example.dz14_4400.data.Delatnost

@Composable
fun AdListPage(vm: AppViewModel, paddingValues: PaddingValues) {
    val ads = vm.company.observeAsState(emptyList())
    LaunchedEffect(vm.loadAdds()) {
    }
    Column {
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(ads.value) { ad ->
                AdRow(ad)
            }
        }
    }
}

@Composable
fun AdRow(company: Company) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { print("Comoany: $company") }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = company.avatar,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .width(250.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = company.title,
                    fontSize = 18.sp
                )
                Text(
                    text = "Category: ${company.category}", color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Delatnost: ${company.delatnost}", color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(verticalAlignment = Alignment.CenterVertically) {

                Box(modifier = Modifier.padding(16.dp)) {
                    PromotionIcon(company.delatnost)
                }
            }
        }
    }
}

@Composable
fun PromotionIcon(delatnost: Delatnost): Unit {
    return when (delatnost) {
        Delatnost.TRADE -> return Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "Trade"
        )

        Delatnost.ENTERTAINMENT -> return Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Entertainment"
        )

        Delatnost.IT -> return Icon(
            imageVector = Icons.Filled.ShoppingCart,
            contentDescription = "It"
        )
        Delatnost.NONE -> return return Spacer(modifier = Modifier.size(0.dp))
    }
}

@Composable
fun TabScreen(vm: AppViewModel, paddingValues: PaddingValues) {
    val tabs = listOf("Entertainment", "Trade", "It")
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = vm.tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = vm.tabIndex == index,
                    onClick = { vm.tabIndex = index }
                )
            }
        }
        when (vm.tabIndex) {
            0 -> {
                vm.setTabCategory(Category.U_SISTEMU)
                AdListPage(vm, paddingValues)
            }

            1 -> {
                vm.setTabCategory(Category.NIJE_U_SISTEMU)
                AdListPage(vm, paddingValues)
            }

            2 -> {
                vm.setTabCategory(Category.OTHER)
                AdListPage(vm, paddingValues)
            }
        }
    }
}