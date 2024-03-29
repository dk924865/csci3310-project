package edu.cuhk.csci3310.ui.habitList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.cuhk.csci3310.ui.utils.CommonUiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun HabitListScreen(
    viewModel: HabitListViewModel = hiltViewModel(),
    onNavigate: (CommonUiEvent.Navigate) -> Unit,
) {
    val habits = viewModel.habitsList.collectAsState(initial = listOf())
    LaunchedEffect(key1 = true, block = {
        viewModel.uiChannel.collect {
                event ->
            when (event) {
                is CommonUiEvent.Navigate -> {
                    onNavigate(event)
                }
            }
        }
    })
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
        ) {
            items(habits.value) {
                    habit ->
                HabitItem(
                    habit = habit,
                    deleteHabit = { viewModel.onEvent(HabitListEvent.RemoveHabit(it)) },
                    modifier =
                        Modifier.clickable {
                            viewModel.onEvent(HabitListEvent.HabitDetail(habit))
                        },
                )
            }
        }
        Button(
            onClick = {
                viewModel.onEvent(HabitListEvent.AddHabit)
            },
            modifier =
                Modifier
                    .align(Alignment.End)
                    .padding(end = 8.dp, bottom = 8.dp),
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add New Habit")
            Text(text = "Add New Habit")
        }
    }
}
