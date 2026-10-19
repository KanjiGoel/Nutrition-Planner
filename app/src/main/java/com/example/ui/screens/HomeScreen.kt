package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.NutriGreenGradientEnd
import com.example.ui.theme.NutriGreenLightBg
import com.example.ui.theme.NutriOrange
import com.example.ui.theme.NutriTeal
import com.example.ui.theme.NutriTextDark
import com.example.ui.theme.NutriTextGray

@Composable
fun HomeScreen(
    onCreatePlanClick: () -> Unit,
    onSavedPlansClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        NutriGreenLightBg,
                        NutriGreenLightBg,
                        NutriGreenGradientEnd
                    )
                )
            )
            .statusBarsPadding()
            .navigationBarsPadding()
            .testTag("home_screen"),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 600.dp)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(28.dp))

            // Top Center Logo: Crossed spoon and fork in dark green circle
            Box(
                modifier = Modifier
                    .size(108.dp)
                    .shadow(elevation = 6.dp, shape = CircleShape)
                    .clip(CircleShape)
                    .background(Color(0xFF1B5E20)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_nutrition_logo),
                    contentDescription = "Nutrition Logo",
                    modifier = Modifier.size(108.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Title: Nutrition Planner bold 24sp
            Text(
                text = "Nutrition Planner",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = NutriTextDark,
                textAlign = TextAlign.Center,
                modifier = Modifier.testTag("home_title")
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Subtitle: AI-Powered Meal Planning for Mothers & Children 14sp gray
            Text(
                text = "AI-Powered Meal Planning for Mothers & Children",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = NutriTextGray,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .testTag("home_subtitle")
            )

            Spacer(modifier = Modifier.height(36.dp))

            // Card 1: Create New Plan
            HomeActionCard(
                circleColor = NutriTeal,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Icon",
                        tint = Color.White,
                        modifier = Modifier.size(26.dp)
                    )
                },
                title = "Create New Plan",
                subtitle = "Generate personalized nutrition plan with AI",
                onClick = onCreatePlanClick,
                testTag = "create_new_plan_card"
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Card 2: Saved Plans
            HomeActionCard(
                circleColor = NutriOrange,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Folder,
                        contentDescription = "Folder Icon",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                },
                title = "Saved Plans",
                subtitle = "View and manage your nutrition plans",
                onClick = onSavedPlansClick,
                testTag = "saved_plans_card"
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Regional Health Note badge
            Surface(
                color = Color.White.copy(alpha = 0.7f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Sukkur Maternal & Child Health Initiative",
                        fontSize = 12.sp,
                        color = Color(0xFF2E7D32),
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun HomeActionCard(
    circleColor: Color,
    icon: @Composable () -> Unit,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    testTag: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag(testTag),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left colored circle with icon
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(circleColor),
                contentAlignment = Alignment.Center
            ) {
                icon()
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Title & Subtitle column
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = NutriTextDark
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    fontSize = 13.sp,
                    color = NutriTextGray,
                    lineHeight = 18.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Right arrow icon
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "Navigate Arrow",
                tint = Color(0xFF9E9E9E),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
