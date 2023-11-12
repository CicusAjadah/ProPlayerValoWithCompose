package com.tugas.proplayervalorantwithcompose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tugas.proplayervalorantwithcompose.models.PlayersData

@Composable
fun DetailScreen(
    playerId : Long,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {
    PlayerDetail(name = PlayersData.players.find { it.id == playerId }!!.name
        , ign = PlayersData.players.find { it.id == playerId }!!.in_game_name
        , photoUrl = PlayersData.players.find { it.id == playerId }!!.photoUrl
        , detail = PlayersData.players.find { it.id == playerId }!!.detail
        , onBackClick = navigateBack )
}

@Composable
fun PlayerDetail(
    name: String,
    ign: String,
    photoUrl: String,
    detail: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        val borderWidth = 4.dp
        Spacer(modifier = Modifier.height(20.dp))
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                AsyncImage(
                    model = photoUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(300.dp)
                        .border(
                            BorderStroke(borderWidth, Color.Magenta),
                            CircleShape
                        )
                        .padding(borderWidth)
                        .clip(CircleShape)
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onBackClick() }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = name,
                fontWeight = FontWeight.Medium,
                fontSize = 28.sp
            )
            Text(
                text = ign,
                fontSize = 24.sp,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = detail,
                fontSize = 20.sp
            )
        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun PlayerDetailPreview() {
//    ProPlayerValorantWithComposeTheme {
//        PlayerDetail(
//            name = "Sen Elevenz",
//            ign = "ElevenZ",
//            photoUrl = "",
//            detail = "cupid",
//            onBackClick =
//        )
//    }
//}