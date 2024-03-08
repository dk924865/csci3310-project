package edu.cuhk.csci3310.ui.habitList

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.cuhk.csci3310.ui.nav.Screen

@Composable
fun HabitListScreen(
    viewModel: HabitListViewModel = hiltViewModel(),
    navController: NavController,
) {
    Button(onClick = {
        navController.navigate(Screen.Debug.route)
    }) {
        Text("Go to debug page")
    }
}
