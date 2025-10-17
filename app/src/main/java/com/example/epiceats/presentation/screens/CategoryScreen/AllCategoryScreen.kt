package com.example.epiceats.presentation.screens.CategoryScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.epiceats.presentation.components.ExploreRow
import com.example.epiceats.presentation.components.HomeScreenCards

@Composable
fun AllCategoryScreen(navController: NavController){

    Column(modifier = Modifier.fillMaxSize()){
        ExploreRow()
        Row(
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp,top = 16.dp),
            horizontalArrangement = Arrangement.Start
        ){
            Column{
                Text(
                   text = "533 Restaurants Delivering Near You",
                    modifier = Modifier,
                    color = Color.Gray
                )
                Text(
                    text = "Featured",
                    modifier = Modifier.padding(start = 5.dp),
                    color = Color.Gray
                )
            }
        }

        HomeScreenCards(navController = navController)
        Spacer(modifier = Modifier.height(16.dp))
        HomeScreenCards(navController = navController)
        Spacer(modifier = Modifier.height(16.dp))
        HomeScreenCards(navController = navController)
        Spacer(modifier = Modifier.height(16.dp))
        HomeScreenCards(navController = navController)
        Spacer(modifier = Modifier.height(16.dp))
    }
}