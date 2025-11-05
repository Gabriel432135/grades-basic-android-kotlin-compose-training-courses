package com.example.grades.model

import com.example.grades.R

object DataSource {

    val originalTopics = listOf(
        Topic(R.string.architecture, 58, R.drawable.architecture),
        Topic(R.string.crafts, 121, R.drawable.crafts),
        Topic(R.string.business, 78, R.drawable.business),
        Topic(R.string.culinary, 118, R.drawable.culinary),
        Topic(R.string.design, 423, R.drawable.design),
        Topic(R.string.fashion, 92, R.drawable.fashion),
        Topic(R.string.film, 165, R.drawable.film),
        Topic(R.string.gaming, 164, R.drawable.gaming),
        Topic(R.string.drawing, 326, R.drawable.drawing),
        Topic(R.string.lifestyle, 305, R.drawable.lifestyle),
        Topic(R.string.music, 212, R.drawable.music),
        Topic(R.string.painting, 172, R.drawable.painting),
        Topic(R.string.photography, 321, R.drawable.photography),
        Topic(R.string.tech, 118, R.drawable.tech))

    val topics = originalTopics + originalTopics + originalTopics + originalTopics + originalTopics + originalTopics

}

// O repositório busca os dados da fonte de dados.
class TopicRepository {
    // Esta função busca a lista de tópicos.
    fun getTopics(): List<Topic> {
        return DataSource.topics
    }
}

