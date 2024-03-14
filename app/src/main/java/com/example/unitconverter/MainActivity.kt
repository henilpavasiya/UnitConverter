package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}


@Composable
fun UnitConverter() {
    var isCMTrue by remember { mutableStateOf(false) }
    var isMTrue by remember { mutableStateOf(false) }
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var conversionFactor by remember { mutableDoubleStateOf(1.00) }
    var oConversionFactor by remember { mutableDoubleStateOf(1.00) }

    fun convertUnits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result =
            (inputValueDouble * conversionFactor * 100 / oConversionFactor).roundToInt() / 100.0
        outputValue = result.toString()
    }


    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Text("Unit Converter",fontWeight= FontWeight.Bold)
        CustomText("Unit Converter")
        Spacer(modifier = Modifier.height(15.dp))
//        Spacer(modifier = Modifier.fillMaxSize())

        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            convertUnits()
        }, label = { Text("Enter Value:") })
        Spacer(modifier = Modifier.height(15.dp))

        Row {
            val context = LocalContext.current

            Box {
                Button(onClick = {
                    isCMTrue = true
                }) {
                    Text(text = inputUnit)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown button"
                    )
                }
                DropdownMenu(
                    expanded = isCMTrue,
                    onDismissRequest = {
                        isCMTrue = false
                    },
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            isCMTrue = false
                            inputUnit = "Centimeters"
                            conversionFactor = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            isCMTrue = false
                            inputUnit = "Meters"
                            conversionFactor = 1.0
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "ML") },
                        onClick = {
                            isCMTrue = false
                            inputUnit = "ML"
                            conversionFactor = 0.001
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            isCMTrue = false
                            inputUnit = "Feet"
                            conversionFactor = 0.3048
                            convertUnits()
                        })
                }
            }
            Spacer(modifier = Modifier.width(12.dp))

            Box {
                Button(onClick = {
                    isMTrue = true
                }) {
                    Text(text = outputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown, contentDescription = "Dropdown button"
                    )
                }
                DropdownMenu(
                    expanded = isMTrue,
                    onDismissRequest = {
                        isMTrue = false
                    },
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            isMTrue = false
                            outputUnit = "Centimeters"
                            oConversionFactor = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            isMTrue = false
                            outputUnit = "Meters"
                            oConversionFactor = 1.0
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "ML") },
                        onClick = {
                            isMTrue = false
                            outputUnit = "ML"
                            oConversionFactor = 0.001
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            isMTrue = false
                            outputUnit = "Feet"
                            oConversionFactor = 0.3048
                            convertUnits()
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))

        Text("Result: $outputValue $outputUnit")
    }
}

@Composable
fun CustomText(text: String) {
    Text(text = text, style = TextStyle(Color.DarkGray, fontWeight = FontWeight.Bold))
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}