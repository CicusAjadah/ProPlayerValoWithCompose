package com.tugas.proplayervalorantwithcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tugas.proplayervalorantwithcompose.ui.theme.ProPlayerValorantWithComposeTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    AboutMe(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .wrapContentWidth()
    )
}

@Composable
fun AboutMe(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(R.drawable.foto_profil),
            contentDescription = stringResource(R.string.foto_profil),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(200.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.namaku),
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = stringResource(R.string.emailku),
            fontSize = 20.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AboutMePreview() {
    ProPlayerValorantWithComposeTheme() {
        AboutMe()
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProPlayerValorantWithComposeTheme {
        ProfileScreen()
    }
}