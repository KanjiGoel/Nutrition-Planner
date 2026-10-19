package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BakeryDining
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Egg
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.NutriBlue
import com.example.ui.theme.NutriGreenPrimary
import com.example.ui.theme.NutriTextDark
import com.example.ui.theme.NutriTextGray
import com.example.ui.theme.NutriYellowRiskBg
import com.example.ui.theme.NutriYellowRiskBorder
import com.example.ui.theme.NutriYellowWarning
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    onBackClick: () -> Unit,
    onSavePlanClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFF9FAFB),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Assessment Result",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = NutriTextDark
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.testTag("result_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = NutriTextDark
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = NutriTextDark
                )
            )
        },
        bottomBar = {
            Surface(
                color = Color.White,
                shadowElevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Bottom two buttons: 'Save Plan' outlined and 'Contact Doctor' filled blue
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Plan saved to Saved Plans!")
                                }
                                onSavePlanClick()
                            },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(52.dp)
                                .testTag("save_plan_button"),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = NutriBlue
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Save,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Save Plan",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp
                            )
                        }

                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Connecting to Sukkur Health Worker line...")
                                }
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = NutriBlue,
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .height(52.dp)
                                .testTag("contact_doctor_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Call,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Contact Doctor",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Disclaimer text at bottom: 'This is screening only, consult doctor.'
                    Text(
                        text = "This is screening only, consult doctor.",
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic,
                        color = NutriTextGray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.testTag("result_disclaimer")
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 600.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Top card: Large status - 'Mild Malnutrition Risk' with yellow warning icon, background light yellow
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = NutriYellowRiskBg),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, NutriYellowRiskBorder, RoundedCornerShape(16.dp))
                        .testTag("status_card")
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(52.dp)
                                .clip(CircleShape)
                                .background(NutriYellowWarning.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = "Warning Icon",
                                tint = NutriYellowWarning,
                                modifier = Modifier.size(30.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(
                                text = "Mild Malnutrition Risk",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF6D4C41)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Weight-for-height below median • MUAC 12.1 cm",
                                fontSize = 13.sp,
                                color = Color(0xFF8D6E63)
                            )
                        }
                    }
                }

                // Second card: WHO Growth chart - Height vs Weight graph, with red dot showing child is below average, with Z-Score -2.1
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("who_chart_card")
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "WHO Growth Chart (Height vs Weight)",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = NutriTextDark
                            )

                            // Z-Score badge
                            Surface(
                                color = Color(0xFFFFEBEE),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = "Z-Score: -2.1",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFD32F2F),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Custom WHO Growth Chart Canvas
                        WhoGrowthChartCanvas(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Chart Legend
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ChartLegendItem(color = Color(0xFF4CAF50), label = "WHO Median (Normal)")
                            ChartLegendItem(color = Color(0xFFFFB300), label = "-2 SD Boundary")
                            ChartLegendItem(color = Color(0xFFD32F2F), label = "Child (-2.1 SD)")
                        }
                    }
                }

                // Third card: 'Recommended Thari Diet Plan' list
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("diet_plan_card")
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        Text(
                            text = "Recommended Thari Diet Plan",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = NutriTextDark
                        )
                        Text(
                            text = "High-protein & calorie-dense traditional local foods",
                            fontSize = 12.sp,
                            color = NutriTextGray
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Morning: Bajra Khichdi + Lassi
                        DietMealItem(
                            time = "Morning",
                            meal = "Bajra Khichdi + Lassi",
                            nutrients = "Rich in iron, fiber, probiotics & slow carbs",
                            icon = Icons.Default.Restaurant,
                            accentColor = Color(0xFF00897B)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Afternoon: Mung Daal + Roti
                        DietMealItem(
                            time = "Afternoon",
                            meal = "Mung Daal + Roti",
                            nutrients = "High quality plant protein, zinc & energy",
                            icon = Icons.Default.BakeryDining,
                            accentColor = Color(0xFFE65100)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Evening: Milk + Boiled Egg
                        DietMealItem(
                            time = "Evening",
                            meal = "Milk + Boiled Egg",
                            nutrients = "Complete amino acids, vitamin D & calcium",
                            icon = Icons.Default.Egg,
                            accentColor = Color(0xFF1976D2)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ChartLegendItem(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            color = NutriTextGray,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun DietMealItem(
    time: String,
    meal: String,
    nutrients: String,
    icon: ImageVector,
    accentColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF9FAFB))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(accentColor.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = accentColor,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = time,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = accentColor
                )
                Text(
                    text = "•",
                    fontSize = 12.sp,
                    color = NutriTextGray
                )
                Text(
                    text = meal,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = NutriTextDark
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = nutrients,
                fontSize = 11.sp,
                color = NutriTextGray
            )
        }
    }
}

@Composable
fun WhoGrowthChartCanvas(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        val leftPad = 24.dp.toPx()
        val rightPad = 16.dp.toPx()
        val topPad = 16.dp.toPx()
        val bottomPad = 24.dp.toPx()

        val chartWidth = w - leftPad - rightPad
        val chartHeight = h - topPad - bottomPad

        // Grid lines
        for (i in 0..4) {
            val y = topPad + (chartHeight * i / 4)
            drawLine(
                color = Color(0xFFECEFF1),
                start = Offset(leftPad, y),
                end = Offset(w - rightPad, y),
                strokeWidth = 1.dp.toPx()
            )
        }

        // WHO Median curve (green)
        val medianPath = Path().apply {
            moveTo(leftPad, topPad + chartHeight * 0.7f)
            quadraticTo(
                leftPad + chartWidth * 0.5f, topPad + chartHeight * 0.45f,
                w - rightPad, topPad + chartHeight * 0.2f
            )
        }
        drawPath(
            path = medianPath,
            color = Color(0xFF4CAF50),
            style = Stroke(width = 2.5.dp.toPx(), cap = StrokeCap.Round)
        )

        // -2 SD curve (amber)
        val minus2SdPath = Path().apply {
            moveTo(leftPad, topPad + chartHeight * 0.82f)
            quadraticTo(
                leftPad + chartWidth * 0.5f, topPad + chartHeight * 0.6f,
                w - rightPad, topPad + chartHeight * 0.4f
            )
        }
        drawPath(
            path = minus2SdPath,
            color = Color(0xFFFFB300),
            style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
        )

        // Child's position: red dot below average
        val childX = leftPad + chartWidth * 0.55f
        val childY = topPad + chartHeight * 0.68f

        // Outer glow
        drawCircle(
            color = Color(0xFFD32F2F).copy(alpha = 0.25f),
            radius = 12.dp.toPx(),
            center = Offset(childX, childY)
        )
        // Red dot
        drawCircle(
            color = Color(0xFFD32F2F),
            radius = 6.dp.toPx(),
            center = Offset(childX, childY)
        )
        // Inner white center
        drawCircle(
            color = Color.White,
            radius = 2.5.dp.toPx(),
            center = Offset(childX, childY)
        )
    }
}
