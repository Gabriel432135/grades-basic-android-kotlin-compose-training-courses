package com.example.grades.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue // Embora não esteja usando, é bom ter por hábito
import com.example.grades.viewmodel.TopicViewModel
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.grades.R
import com.example.grades.model.DataSource
import com.example.grades.model.Topic


class MainActivity : ComponentActivity() {


    // Delegação de propriedade "by viewModels()" para obter a instância do ViewModel.
    // Isso garante que receba a mesma instância mesmo após rotações de tela.
    private val viewModel: TopicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {

            // 1. Coleta o StateFlow como um "State" do Compose.
            //    A variável `topicList` será automaticamente atualizada
            //    e fará a UI recompor sempre que o ViewModel emitir uma nova lista.
            val topicList by viewModel.topics.collectAsState()

            // 2. Chama o Composable principal e passa a lista de dados reativa.
            GridList(topicList = topicList)

        }
    }
}

@Composable
fun CardItem(modifier: Modifier = Modifier, topic: Topic){
    Card(modifier = modifier){
        Row(Modifier
            .fillMaxWidth()
            .height(68.dp)){
            Image(
                painter = painterResource(id = topic.imageRes),
                contentDescription = null,
                modifier = Modifier.width(68.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)){
                Text(
                    text = LocalContext.current.getString(topic.textRes),
                    modifier = Modifier,
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically){
                    Icon(painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier)

                    Text(
                        text = topic.availableCourses.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier)

                }
            }
        }
    }
}

@Composable
fun GridList(modifier: Modifier = Modifier, topicList: List<Topic>){
    LazyVerticalGrid(modifier = modifier, columns = GridCells.Adaptive(minSize = 180.dp)){
        items(topicList.size){ index ->
            CardItem(topic = topicList[index], modifier = Modifier.padding(2.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    GridList(topicList = DataSource.topics)
}