package com.example.heart_rate_limits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heart_rate_limits.ui.theme.HeartRateLimitsTheme
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HeartRateLimitsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HeartRateLimits(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HeartRateLimits(modifier: Modifier = Modifier) {
    var ageInput by remember { mutableStateOf("") } // State variable for age
    var age = ageInput.toIntOrNull() ?: 0 // As as int
    val upper = if (age > 0) (220 - age) * 0.85f else 0.0f // upper limit
    val lower = if (age > 0) (220 - age) * 0.65f else 0.0f// lower limit
    val upperFormatted = String.format("%.2f", upper)  // formatted upper limit
    val lowerFormatted = String.format("%.2f", lower) // formatted lower limit

    Column (
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = ageInput,
            onValueChange = {ageInput = it},
            label = {Text(text= stringResource(R.string.age)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = stringResource(R.string.your_heart_rate_limits_are,
                lowerFormatted,upperFormatted)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HeartRateLimitsTheme {
        HeartRateLimits()
    }
}