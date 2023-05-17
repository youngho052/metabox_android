package com.clone.metabox.view.theater

import androidx.activity.ComponentActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clone.metabox.R
import com.clone.metabox.data.api.response.FloorInformation
import com.clone.metabox.ui.theme.LightBlue
import com.clone.metabox.ui.theme.LightGray
import com.clone.metabox.view.common.HorizontalLineView
import com.clone.metabox.view.common.IconTextView
import com.clone.metabox.view.common.IconView
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TheaterContainer(
    theaterViewModel: TheaterViewModel
) {
    val theaterDetailUiState = theaterViewModel.theaterDetailUiState.collectAsState()

    LazyColumn() {
        stickyHeader {
            TheaterDetailHeader(
                theaterName = theaterDetailUiState.value.theaterDetail.name
            )
        }

        item {
            GlideImage(
                imageModel = { "https://img.megabox.co.kr/static/mb/images/theater/visual-img-01.png"},
                modifier = Modifier
                    .fillMaxSize()
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(25.dp),
                modifier = Modifier
                    .background(Color.White)
                    .padding(bottom = 50.dp)
            ) {
                TheaterDescriptionContainer(
                    theaterDescription = theaterDetailUiState.value.theaterDetail.description,
                    theaterSubscription = theaterDetailUiState.value.theaterDetail.subscription
                )
                HorizontalLineView(
                    color = Color(0xFFF5F5F5),
                    height = 10
                )
                TheaterFacilitiesContainer(
                    theaterFacilities = theaterDetailUiState.value.theaterDetail.facilities
                )
                HorizontalLineView(
                    color = Color(0xFFF5F5F5),
                    height = 10
                )
                TheaterFloorContainer(
                    theaterFloorInformation = theaterDetailUiState.value.theaterDetail.floorInformation
                )
                HorizontalLineView(
                    color = Color(0xFFF5F5F5),
                    height = 10
                )
                TheaterAddressContainer(
                    theaterAddress = theaterDetailUiState.value.theaterDetail.address
                )
                HorizontalLineView(
                    color = Color(0xFFF5F5F5),
                    height = 10
                )
                TheaterParkingContainer()
            }
        }
    }
}

@Composable
private fun TheaterDetailHeader(
    theaterName: String
) {
    val context = LocalContext.current as ComponentActivity

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 18.dp, end = 18.dp)
        ) {
            IconView(
                painter = painterResource(id = R.drawable.icon_back_black),
                description = "${R.drawable.icon_back_black}",
                tint = Color.Unspecified,
                size = 30,
                modifier = Modifier.clickable {
                    context.finish()
                }
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "$theaterName",
                color = Color.Black,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
private fun TheaterDescriptionContainer (
    theaterDescription: String,
    theaterSubscription: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .padding(top = 10.dp, start = 18.dp, end = 18.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color.White, RoundedCornerShape(5.dp))
                .shadow(elevation = 1.dp, spotColor = MaterialTheme.colors.LightGray)
                .padding(top = 8.dp, bottom = 8.dp)
        ) {
            IconTextView(
                imagePainter = painterResource(id = R.drawable.icon_star_empty),
                category = "선호극장"
            )
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(MaterialTheme.colors.LightGray)
            )
            IconTextView(
                imagePainter = painterResource(id = R.drawable.icon_clock_black),
                category = "상영시간표"
            )
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(MaterialTheme.colors.LightGray)
            )
            IconTextView(
                imagePainter = painterResource(id = R.drawable.icon_movie_black),
                category = "관람료"
            )
        }

        Text(
            text = "$theaterDescription",
            color = MaterialTheme.colors.LightBlue,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
//        Text(
//            text = "로맨틱 멀티플렉스! 젊은 도시 강남이 한 눈에 보이는 최상의 View를 제공",
//            color = Color.Black,
//            fontSize = 18.sp,
//            textAlign = TextAlign.Center
//        )
        Text(
            text = "$theaterSubscription",
            color = Color.Black,
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )
    }
}

@Composable
private fun TheaterFacilitiesContainer (
    theaterFacilities: List<String>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(start = 18.dp, end = 18.dp)
    ) {
        Text(
            text = "보유시설",
            color = Color.Black,
            fontSize = 16.sp
        )

        Text(
            text ="${theaterFacilities.joinToString()}",
            color = Color.Black,
            fontSize = 15.sp
        )
    }
}

@Composable
fun TheaterFloorContainer (
    theaterFloorInformation: List<FloorInformation>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(start = 18.dp, end = 18.dp)
    ) {
        Text(
            text ="층별안내",
            color = Color.Black,
            fontSize = 16.sp,
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight()
        ) {
            repeat(theaterFloorInformation.size) {
                TheaterDescriptionView(
                    title = "${theaterFloorInformation[it].floor}",
                    description = "${theaterFloorInformation[it].information.joinToString()}"
                )
            }
        }
    }
}

@Composable
fun TheaterAddressContainer (
    theaterAddress: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(start = 18.dp, end = 18.dp)
    ) {
        Text(
            text ="약도",
            color = Color.Black,
            fontSize = 16.sp,
        )
        TheaterDescriptionView(
            title = "도로명 주소",
            description = "$theaterAddress"
        )
    }
}

@Composable
fun TheaterParkingContainer () {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(start = 18.dp, end = 18.dp)
    ) {
        Text(
            text ="주차",
            color = Color.Black,
            fontSize = 16.sp,
        )
        TheaterDescriptionView(
            title = "주차안내",
            description = "아라타워 건물 內 지하 3층 ~ 지하 6층 주차장 이용"
        )
        TheaterDescriptionView(
            title = "주차확인",
            description = "매표소에서 당일 관람 티켓 인증 후, 차량 번호 할인 등록하여 지하 정산소에서 결제"
        )
        TheaterDescriptionView(
            title = "주차요금",
            description = "주차 요금은 입차시간을 기준으로 합니다."
        )
    }
}

@Composable
private fun TheaterDescriptionView (
    title: String,
    description: String,
) {
    Column() {
        Text(
            text = "$title",
            color = Color.Black,
            fontWeight = FontWeight(700),
            fontSize = 15.sp
        )
        Text(
            text = " - $description",
            color = Color.Black,
            fontSize = 15.sp
        )
    }
}

