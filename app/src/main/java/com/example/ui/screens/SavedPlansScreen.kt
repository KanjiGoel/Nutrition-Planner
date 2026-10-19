package com.example.ui.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.NutriBlue
import com.example.ui.theme.NutriGreenDark
import com.example.ui.theme.NutriGreenLightBg
import com.example.ui.theme.NutriGreenPrimary
import com.example.ui.theme.NutriOrange
import com.example.ui.theme.NutriOrangeLight
import com.example.ui.theme.NutriStatusHealthy
import com.example.ui.theme.NutriStatusHealthyBg
import com.example.ui.theme.NutriStatusMild
import com.example.ui.theme.NutriStatusMildBg
import com.example.ui.theme.NutriStatusSevere
import com.example.ui.theme.NutriStatusSevereBg
import com.example.ui.theme.NutriTextDark
import com.example.ui.theme.NutriTextGray

data class SavedPlanItem(
    val id: String,
    val name: String,
    val userType: String,
    val age: String,
    val date: String,
    val status: PlanStatus,
    val muac: String
)

enum class PlanStatus(
    val label: String,
    val color: Color,
    val backgroundColor: Color
) {
    HEALTHY("Healthy", NutriStatusHealthy, NutriStatusHealthyBg),
    MILD("Mild Risk", NutriStatusMild, NutriStatusMildBg),
    SEVERE("Severe Risk", NutriStatusSevere, NutriStatusSevereBg)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedPlansScreen(
    onBackClick: () -> Unit,
    onCreatePlanClick: () -> Unit,
    onPlanSelect: (SavedPlanItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val samplePlans = remember {
        listOf(
            SavedPlanItem(
                id = "1",
                name = "Amina Bibi",
                userType = "Child",
                age = "3 Years",
                date = "Oct 15, 2026",
                status = PlanStatus.MILD,
                muac = "12.1 cm"
            ),
            SavedPlanItem(
                id = "2",
                name = "Bilal Khan",
                userType = "Child",
                age = "18 Months",
                date = "Oct 12, 2026",
                status = PlanStatus.HEALTHY,
                muac = "13.5 cm"
            ),
            SavedPlanItem(
                id = "3",
                name = "Fatima Z.",
                userType = "Mother",
                age = "26 Years",
                date = "Oct 08, 2026",
                status = PlanStatus.SEVERE,
                muac = "11.2 cm"
            )
        )
    }

    // Toggle to view both Empty State and Data State easily
    var showEmptyState by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFF9FAFB),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Saved Plans",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = NutriTextDark
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.testTag("saved_plans_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = NutriTextDark
                        )
                    }
                },
                actions = {
                    // Small preview toggle between Data and Empty State
                    FilterChip(
                        selected = showEmptyState,
                        onClick = { showEmptyState = !showEmptyState },
                        label = {
                            Text(
                                text = if (showEmptyState) "View List" else "Preview Empty",
                                fontSize = 12.sp
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = NutriOrangeLight,
                            selectedLabelColor = NutriOrange
                        ),
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .testTag("toggle_empty_state")
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = NutriTextDark
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            if (showEmptyState) {
                // Empty state with illustration of empty folder, text 'No saved plans yet' and button 'Create First Plan'
                EmptyPlansView(
                    onCreatePlanClick = onCreatePlanClick,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                )
            } else {
                // List of cards with Child Name, Age, Date, and status badge Healthy/Mild/Severe with green/yellow/red colors
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 600.dp)
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    item {
                        Text(
                            text = "Recent Assessments (${samplePlans.size})",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = NutriTextGray,
                            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                        )
                    }

                    items(samplePlans, key = { it.id }) { plan ->
                        SavedPlanCard(
                            plan = plan,
                            onClick = { onPlanSelect(plan) }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = onCreatePlanClick,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("create_another_plan_button"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = NutriBlue
                            )
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Create New Plan",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyPlansView(
    onCreatePlanClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.testTag("empty_plans_view"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Illustration of empty folder
        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(NutriOrangeLight),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.FolderOpen,
                contentDescription = "Empty Folder",
                tint = NutriOrange,
                modifier = Modifier.size(54.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Text 'No saved plans yet'
        Text(
            text = "No saved plans yet",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = NutriTextDark,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Generate and save a personalized nutrition assessment to view it anytime.",
            fontSize = 14.sp,
            color = NutriTextGray,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Button 'Create First Plan'
        Button(
            onClick = onCreatePlanClick,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = NutriBlue,
                contentColor = Color.White
            ),
            modifier = Modifier
                .height(52.dp)
                .padding(horizontal = 16.dp)
                .testTag("create_first_plan_button")
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Create First Plan",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SavedPlanCard(
    plan: SavedPlanItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("saved_plan_card_${plan.id}"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE8F5E9)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = NutriGreenDark,
                    modifier = Modifier.size(26.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            // Details
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = plan.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = NutriTextDark
                    )
                    Text(
                        text = "• ${plan.age}",
                        fontSize = 13.sp,
                        color = NutriTextGray
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = plan.date,
                        fontSize = 12.sp,
                        color = NutriTextGray
                    )
                    Text(
                        text = "MUAC: ${plan.muac}",
                        fontSize = 12.sp,
                        color = NutriTextGray
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Status Badge: Healthy / Mild / Severe with green / yellow / red colors
            Surface(
                color = plan.status.backgroundColor,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = plan.status.label,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = plan.status.color,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = null,
                tint = Color(0xFFBDBDBD),
                modifier = Modifier.size(14.dp)
            )
        }
    }
}
