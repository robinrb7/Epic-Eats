package com.example.epiceats.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.epiceats.R

@Composable
fun SearchBarDiningTabScreen(){
    RestaurantsNearMeCard()
    Spacer(modifier = Modifier.height(16.dp))

    Title()
    Spacer(modifier = Modifier.height(16.dp))

    RestaurantLazyRow()
    Spacer(modifier = Modifier.height(16.dp))

    EditorChoiceText()

    TrendingSportsLazyRow()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantsNearMeCard(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
    ){
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                painter = painterResource(id = R.drawable.diningsearchbanner),
                contentDescription = "Restaurants near me",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun Title(){
    Text(
       text = "HOTSPOTS UNDER 10 KM",
        style = TextStyle(
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Normal,
            letterSpacing = 2.sp,
            fontFamily = FontFamily.SansSerif
        ),
        modifier = Modifier.padding(vertical = 16.dp).padding(start = 75.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun RestaurantLazyRow(){
    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(5){index ->
            RestaurantCard(
                imageId = when(index){
                    0 -> R.drawable.hotspot1
                    1 -> R.drawable.hotspots2
                    2 -> R.drawable.hotspots3
                    3 -> R.drawable.hotspots4
                    4 -> R.drawable.hotspots5
                    else -> R.drawable.restaurant6
                },
                locationName = when(index){
                    0 -> "High Street"
                    1 -> "Capital Building"
                    2 -> "Crown Plaza"
                    3 -> "Seaside View"
                    4 -> "Phoenix MarketCity"
                    else -> "Location"
                }
            )
        }
    }
}

@Composable
fun RestaurantCard(imageId: Int, locationName: String){
    Card(
        modifier = Modifier
            .width(220.dp)
            .height(180.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ){
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "Location Background",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(12.dp)
            ){
                Text(
                    text = locationName,
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .background(
                            color = Color.Black.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun EditorChoiceText(){
    Text(
        text = "EDITOR CHOICE",
        style = TextStyle(
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Normal,
            letterSpacing = 2.sp,
            fontFamily = FontFamily.SansSerif
        ),
        modifier = Modifier.padding(vertical = 16.dp).padding(start = 125.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun TrendingSportsLazyRow(){
    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(5){index ->
            TrendingSpotsCard(
                imageId = when(index){
                    0 -> R.drawable.restaurant1
                    1 -> R.drawable.restaurant2
                    2 -> R.drawable.restaurant3
                    3 -> R.drawable.restaurant4
                    4 -> R.drawable.restaurant5
                    else -> R.drawable.restaurant6
                },
                locationName = when(index){
                    0 -> "top trending spots"
                    1 -> "best rooftop places"
                    2 -> "new places"
                    3 -> "iftar specials"
                    4 -> "romantic"
                    else -> "Trending Spot"
                }
            )
        }
    }
}

@Composable
fun TrendingSpotsCard(
    imageId: Int,
    locationName: String
){
    Card(
        modifier = Modifier
            .width(180.dp)
            .height(200.dp),
        shape = RoundedCornerShape(
            topStart = 150.dp,
            topEnd = 150.dp,
            bottomStart = 12.dp,
            bottomEnd = 12.dp
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ){
        Box(modifier = Modifier.fillMaxSize()){
            //Background Image
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "Trending Spot",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        RoundedCornerShape(
                            topStart = 150.dp,
                            topEnd = 150.dp,
                            bottomStart = 12.dp,
                            bottomEnd = 12.dp
                        )
                    ),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(12.dp)
            ){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = locationName,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .background(
                                color = Color.Black.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(4.dp)
                            )
                    )
                }
            }
        }
    }
}