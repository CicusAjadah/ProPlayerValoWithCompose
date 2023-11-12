package com.tugas.proplayervalorantwithcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.tugas.proplayervalorantwithcompose.data.PlayerRepository

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    viewModel: ProPlayerViewModel = viewModel(factory = ViewModelFactory(PlayerRepository()))
) {
    val groupedPlayers by viewModel.groupedPlayers.collectAsState()
    val query by viewModel.query

    Box(modifier = modifier) {
        LazyColumn{
            // Searchbar
            item {
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::search,
                    modifier = Modifier.background(MaterialTheme.colors.primary)
                )
            }
            groupedPlayers.forEach { (initial, players) ->
                items(players, key = {it.id} ) { player ->
                    PlayerListItem(
                        id = player.id,
                        name = player.name,
                        ign = player.in_game_name,
                        photoUrl = player.photoUrl,
                        navigateToDetail = navigateToDetail,
                        modifier = Modifier.fillMaxWidth(1f)
                    )
                }
            }

        }
    }
}

@Composable
fun PlayerListItem(
    id : Long,
    name: String,
    ign: String,
    photoUrl: String,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable{
            navigateToDetail(id)
        }
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(CircleShape)
        )
        Column {
            Text(
                text = name,
                fontWeight = FontWeight.Medium,
                fontSize = 22.sp
            )
            Text(
                text = ign,
            )
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text(stringResource(R.string.search_player))
        },
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

//@Preview(showBackground = true)
//@Composable
//fun HeroListItemPreview() {
//    ProPlayerValorantWithComposeTheme {
//        val navController = rememberNavController()
//        PlayerListItem(
//            id = 1,
//            name = "Sen Elevenz",
//            ign = "ElevenZ",
//            photoUrl = "",
//            navigateToDetail = navController
//        )
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    ProPlayerValorantWithComposeTheme {
//        HomeScreen()
//    }
//}