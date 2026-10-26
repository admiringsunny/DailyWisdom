package com.learn.dailywisdom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Preview(showBackground = true)
@Composable
fun QuoteScreen(
    viewModel: QuoteViewModel = QuoteViewModel(),
    name: String = "Daily Wisdom",
    modifier: Modifier = Modifier
) {
    val quote by viewModel.quote.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsStateWithLifecycle()


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "A little wisdom for your day",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (loading) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp),
                        strokeWidth = 5.dp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Finding your wisdom...",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else {
                LazyColumn {
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "",
                                    fontSize = 56.sp,
                                    lineHeight = 56.sp,
                                    color = MaterialTheme.colorScheme.primary,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = quote.quote,
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = Color.White,
                                    fontStyle = FontStyle.Italic,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(24.dp))

                                Text(
                                    text = if (quote.author.isNotEmpty()) "- ${quote.author}" else "",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
        Button(
            onClick = { viewModel.fetchQuote() },
            enabled = !loading,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
        ) {
            Text(text = "\uD83D\uDD01 Next Quote", fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}