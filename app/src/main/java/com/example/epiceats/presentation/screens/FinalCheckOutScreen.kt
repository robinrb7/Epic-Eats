package com.example.epiceats.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.epiceats.R
import com.example.epiceats.presentation.utils.OrderPlaceDialog

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FinalCheckOutScreen(navController: NavController){
    var showOrderDialog by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("La Roma Pizzzeria")}
    var time by remember { mutableStateOf("37 mins") }
    var productName by remember {mutableStateOf("Peri Peri Burger") }
    var itemPrice by remember {mutableIntStateOf(129)}
    val gst = 37
    var quantity by remember { mutableIntStateOf(1) }
    val totalBill = itemPrice * quantity + gst


    Scaffold(
        modifier = Modifier.fillMaxSize().padding(WindowInsets.navigationBars.only(WindowInsetsSides.Bottom).asPaddingValues()),
        topBar = {
            TopAppBar(
                title = {
                    Column(modifier = Modifier.height(60.dp)){
                        Text(text = name, fontSize = 13.sp, color = Color.DarkGray)
                        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically )
                        {
                            Text(text = "$time to Home", color = Color.Gray,
                                fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                            VerticalDivider(modifier = Modifier.padding(horizontal = 4.dp).padding(top = 10.dp,bottom = 12.dp))
                            Text(text = "Hostel J,Prem Nagar,Thapar...", fontSize = 12.sp,color = Color.DarkGray)
                            Icon(painter = painterResource(R.drawable.down_arrow),
                                modifier = Modifier.padding(horizontal = 3.dp).size(14.dp),
                                tint = Color.DarkGray,
                                contentDescription = "down arrow")
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(painter = painterResource(R.drawable.arrowback),
                            tint = Color.DarkGray,
                            modifier = Modifier.size(22.dp),
                            contentDescription = "Back Navigation")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(painter = painterResource(R.drawable.share),
                            modifier = Modifier.size(18.dp),
                            tint = Color.DarkGray,
                            contentDescription = "Back Navigation")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.White)
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ){
                //Payment Button
                Button(
                    onClick = {
                        showOrderDialog = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.redish)),
                    shape = RoundedCornerShape(8.dp)
                ){
                    Box(modifier = Modifier.fillMaxWidth())
                    {
                        Text(
                            text = "Place Order",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.Center)
                        )
                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_right_24),
                            modifier = Modifier.align(Alignment.CenterEnd).size(35.dp),
                            contentDescription = "Next"
                        )
                    }
                }
            }

            if(showOrderDialog){
                OrderPlaceDialog(
                    onDismiss = { showOrderDialog = false},
                    onConfirm = { showOrderDialog = false}
                )
            }
        }
    ){ innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(vertical = 0.dp),
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
        ){
            item{
                ProductCard(productName,itemPrice,quantity){ newQty ->
                    quantity = newQty
                }
                CouponCard()
                Spacer(modifier = Modifier.height(16.dp))
                AddressAndBillCard(time,totalBill)
                Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp,vertical = 16.dp),
                    verticalArrangement = Arrangement.Center){
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ){
                      Box(modifier = Modifier.fillMaxSize()){
                          Image(
                              painter = painterResource(id = R.drawable.donationbanner),
                              contentDescription = "Restaurants near me",
                              modifier = Modifier.fillMaxSize()
                          )
                      }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "CANCELLATION POLICY",
                        modifier = Modifier.padding(horizontal = 4.dp),
                        color = Color.DarkGray
                    )
                    Text(
                        text = "Help us reduce food waste by avoiding cancellations. The amount paid is non-refundable after placing the order",
                        modifier = Modifier.padding(horizontal = 4.dp),
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}

@Composable
fun ProductCard(productName: String,
                price: Int,
                quantity: Int,
                onQuantityChange: (Int) -> Unit)
{

    Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 16.dp),
        colors = CardDefaults.cardColors(Color.White))
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(R.color.finalScreenCard))
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ){
            Icon(
                painter = painterResource(R.drawable.goldicon1),
                tint = Color.Unspecified,
                contentDescription = "Gold",
                modifier = Modifier.size(20.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ){
                Text(
                    text = "Get Premium for 3 months",
                    color = Color.DarkGray,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Unlimited free deliveries & more benefits",
                    color = Color.DarkGray,
                    fontSize = 12.sp
                )
                Row(verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "Learn more",
                        color = Color.DarkGray,
                        fontSize = 14.sp
                    )
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_right_24),
                        tint = colorResource(R.color.redish),
                        contentDescription = "Learn More",
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End){
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.height(32.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = colorResource(R.color.addButtonRed)),
                    border = BorderStroke(1.dp,colorResource(R.color.addButtonRed))
                ) {
                    Text("ADD")
                }
                Spacer(modifier =  Modifier.height(2.dp))
                Text(
                    text = "₹30",
                    color = Color.Black,
                    fontSize = 14.sp,
                    textAlign = TextAlign.End
                )
            }
        }

        //Product details
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp,vertical = 8.dp)
        ){
            Icon(
                painter = painterResource(R.drawable.veg_icon),
                tint = Color.Unspecified,
                contentDescription = "Veg",
                modifier = Modifier.padding(top = 4.dp).size(16.dp)
            )

            Column(modifier = Modifier.padding(horizontal = 12.dp)){
                Text(
                    text = productName,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "₹${price}",
                    color = Color.DarkGray,
                    fontSize = 14.sp,
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            //Card to handle Quantity
            Column(horizontalAlignment = Alignment.End){
                Card(
                    modifier = Modifier.size(width = 75.dp,height = 26.dp),
                    colors = CardDefaults.cardColors(colorResource(R.color.lightPink)),
                    border = BorderStroke(width = 1.dp, colorResource(R.color.addButtonRed)),
                    shape = RoundedCornerShape(8.dp)
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Icon(
                            painterResource(R.drawable.outline_check_indeterminate_small_24),
                            modifier = Modifier.padding(2.dp).clickable{
                                if(quantity>1) onQuantityChange(quantity-1)
                            },
                            tint = colorResource(R.color.addButtonRed),
                            contentDescription = null
                        )
                        Text(
                            text = quantity.toString(),
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            painterResource(R.drawable.baseline_add_24),
                            modifier = Modifier.padding(2.dp).size(18.dp).clickable{
                                onQuantityChange(quantity+1)
                            },
                            tint = colorResource(R.color.addButtonRed),
                            contentDescription = null
                        )
                    }
                }
                val totalPrice = quantity * price
                Text(
                    text = "₹$totalPrice",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp,vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add more",
                tint = Color(0xFFE91E63),
                modifier = Modifier.size(18.dp)
            )

            Text(
                text = "Add items",
                color = colorResource(R.color.redish),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp, horizontal = 0.dp),
            color = colorResource(R.color.finalScreenCard))

        //lazyRow pf Product row
        LazyRow{
            item{
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp,vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    OutlinedButton(
                        onClick = { },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                        border = BorderStroke(1.dp,Color.LightGray),
                        modifier = Modifier.padding(end = 8.dp)
                    ){
                        Icon(
                            painter = painterResource(R.drawable.cutlery),
                            contentDescription = "Add more",
                            tint = Color(0xFFE91E63),
                            modifier = Modifier.size(18.dp)
                        )

                        Text(
                            text = "Don't send cutlery",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                    OutlinedButton(
                        onClick = { },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                        border = BorderStroke(1.dp,Color.LightGray)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.notes),
                            contentDescription = "Note",
                            tint = Color.Black,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Add a note for the",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

//Coupons Section
@Composable
fun CouponCard(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(24.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding( 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(
                    painter = painterResource(R.drawable.coupons),
                    contentDescription = "Coupons",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = "View all coupons",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Icon(
                painter = painterResource(R.drawable.arrowright),
                contentDescription = "View",
                tint = Color.Gray,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun AddressAndBillCard(time: String, newPrice: Int){
    //delivery Information
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){
        //delivery time
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ){
            Icon(
                painter = painterResource(R.drawable.timer),
                contentDescription = "delivery Time",
                tint = colorResource(R.color.ViewActivityClickableColor),
                modifier = Modifier.size(16.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ){
                Text(
                    text = "Delivery in $time",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Want this later? Schedule it",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    textDecoration = TextDecoration.Underline
                )
            }
        }

        HorizontalDivider(modifier = Modifier.padding(horizontal = 8.dp,vertical = 4.dp))

        //deliveryAddress
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            Icon(
                imageVector = Icons.Outlined.Home,
                contentDescription = "Home",
                tint = Color.DarkGray,
                modifier = Modifier.size(18.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f).padding(start = 6.dp)
            ){
                Text(
                    text = "Delivery at Home",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Hostel J,Prem Nagar.....",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Add instructions for delivery partner",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    textDecoration = TextDecoration.Underline
                )
            }

            Icon(
                painter = painterResource(R.drawable.arrowright),
                contentDescription = "Edit",
                tint = Color.Gray,
                modifier = Modifier.size(18.dp)
            )

        }

        HorizontalDivider(modifier = Modifier.padding(horizontal = 8.dp,vertical = 4.dp))

        //Contact Information
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Phone",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(18.dp)
                )

                Text(
                    text = "Toshar Bhardwaj",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "+91-8872551231",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Icon(
                painter = painterResource(R.drawable.arrowright),
                contentDescription = "Edit",
                tint = Color.Gray,
                modifier = Modifier.size(18.dp)
            )
        }

        HorizontalDivider(modifier = Modifier.padding(horizontal = 8.dp,vertical = 4.dp))

        //Bill Information
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column{
                Row{
                    Icon(
                        painter = painterResource(R.drawable.notes),
                        contentDescription = "Bill",
                        tint = Color.DarkGray,
                        modifier = Modifier.size(18.dp)
                    )

                    Text(
                        text = "Total Bill ₹$newPrice",
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Text(
                    text = "Incl. taxes and charges",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 28.dp)
                )
            }


            Icon(
                painter = painterResource(R.drawable.arrowright),
                contentDescription = "View",
                tint = Color.Gray,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun ShowFinalCheckout(){
//    FinalCheckOutScreen(rememberNavController())
//}