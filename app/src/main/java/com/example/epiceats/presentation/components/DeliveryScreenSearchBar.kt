package com.example.epiceats.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.epiceats.R
import com.example.epiceats.presentation.navigation.Routes
import com.example.epiceats.presentation.navigation.SubNavigation

@Composable
fun DeliveryScreenSearchBar(navController: NavController) {
    var query by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 6.dp)
            .shadow(2.dp, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White) // make whole background white
            .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp))
            .clickable {
                navController.navigate(Routes.SearchBarScreen)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))

        Icon(
            Icons.Default.Search,
            contentDescription = "Search",
            tint = colorResource(R.color.redish),
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Use Box to show placeholder if query is empty
        Box(modifier = Modifier.weight(1f)) {
            if (query.isEmpty()) {
                Text(
                    text = "Restaurant name or dish...",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
            BasicTextField(
                value = query,
                onValueChange = { query = it },
                singleLine = true,
                enabled = false,
                textStyle = TextStyle(
                    fontSize = 15.sp,
                    color = Color.Black // user input text color
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            painter = painterResource(R.drawable.mic),
            contentDescription = "Voice Search",
            tint = colorResource(R.color.redish),
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
fun VegModeToggle(isChecked: Boolean, onCheckedChange: (Boolean)-> Unit){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
    ){
        Text(
            text = "VEG",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Text(
            text = "MODE",
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        CustomSwitch(isChecked,onCheckedChange)
    }
}
@Composable
fun CustomSwitch(checked: Boolean, onCheckedChange:(Boolean)-> Unit){
    Box(
        modifier = Modifier
            .size(width = 38.dp, height = 18.dp)
            .clip(RoundedCornerShape(50))
            .background(if(checked) Color(0xFF008000) else Color.Magenta)
            .clickable{ onCheckedChange(!checked)},
        contentAlignment = Alignment.CenterStart
    ){
        Box(
            modifier = Modifier
                .size(16.dp)
                .offset(x = if(checked) 25.dp else 3.dp)
                .clip(CircleShape)
                .shadow(elevation = 8.dp)
                .background(Color.White)
        )
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun ShowSearchBar(){
//    DeliveryScreenSearchBar(rememberNavController())
//}