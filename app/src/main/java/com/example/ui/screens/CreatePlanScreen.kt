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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.dashedBorder
import com.example.ui.theme.NutriBlue
import com.example.ui.theme.NutriBorderGray
import com.example.ui.theme.NutriBoxBackground
import com.example.ui.theme.NutriGreenPrimary
import com.example.ui.theme.NutriTeal
import com.example.ui.theme.NutriTextDark
import com.example.ui.theme.NutriTextGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePlanScreen(
    onBackClick: () -> Unit,
    onGeneratePlanClick: (
        userType: String,
        age: String,
        height: String,
        weight: String,
        muac: String,
        activityLevel: String,
        dietPreference: String,
        allergies: String,
        healthNotes: String
    ) -> Unit,
    modifier: Modifier = Modifier
) {
    // Form States with initial selections as requested
    var userType by remember { mutableStateOf("Child") } // Child selected by default
    var age by remember { mutableStateOf("3") }
    var height by remember { mutableStateOf("2.9") }
    var weight by remember { mutableStateOf("11.5") }
    var muac by remember { mutableStateOf("12.1") }
    var activityLevel by remember { mutableStateOf("Moderate") } // Moderate selected by default
    var dietPreference by remember { mutableStateOf("Both") } // Both selected by default
    var allergies by remember { mutableStateOf("") }
    var healthNotes by remember { mutableStateOf("") }
    var hasPhoto by remember { mutableStateOf(false) }
    var showMuacInfoDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Create Plan",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = NutriTextDark
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.testTag("back_button")
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
            // Sticky blue button 'Generate Plan' with sparkle icon, height 56dp, rounded 12dp
            Surface(
                color = Color.White,
                shadowElevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            onGeneratePlanClick(
                                userType,
                                age,
                                height,
                                weight,
                                muac,
                                activityLevel,
                                dietPreference,
                                allergies,
                                healthNotes
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .testTag("generate_plan_button"),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = NutriBlue,
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = "Sparkle Icon",
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Generate Plan",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 600.dp)
            ) {
                // Top: Dashed border box 120dp height with camera icon and text 'Add Photo (Optional)' centered, light gray background
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(NutriBoxBackground)
                        .dashedBorder(
                            strokeWidth = 1.5.dp,
                            color = NutriBorderGray,
                            cornerRadius = 12.dp
                        )
                        .clickable { hasPhoto = !hasPhoto }
                        .testTag("add_photo_box"),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        if (hasPhoto) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .background(NutriGreenPrimary),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Photo Added",
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Photo Added (Tap to change)",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = NutriGreenPrimary
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.PhotoCamera,
                                contentDescription = "Camera Icon",
                                tint = NutriTextGray,
                                modifier = Modifier.size(36.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Add Photo (Optional)",
                                fontSize = 14.sp,
                                color = NutriTextGray,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Section 'User Type' with two toggle chips 'Mother' and 'Child' - Child selected teal color
                Text(
                    text = "User Type",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = NutriTextDark
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val userTypes = listOf("Mother", "Child")
                    userTypes.forEach { type ->
                        val isSelected = userType == type
                        FilterChip(
                            selected = isSelected,
                            onClick = { userType = type },
                            label = {
                                Text(
                                    text = type,
                                    fontSize = 15.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = NutriTeal,
                                selectedLabelColor = Color.White,
                                containerColor = Color.White,
                                labelColor = NutriTextDark
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = isSelected,
                                borderColor = if (isSelected) NutriTeal else NutriBorderGray,
                                borderWidth = if (isSelected) 2.dp else 1.dp
                            ),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .testTag("chip_user_type_$type")
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Input field 'Age - Enter age in years' with outline border
                OutlinedTextField(
                    value = age,
                    onValueChange = { age = it },
                    label = { Text("Age - Enter age in years") },
                    placeholder = { Text("e.g., 3") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_age"),
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = NutriTeal,
                        unfocusedBorderColor = NutriBorderGray,
                        focusedLabelColor = NutriTeal
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Two fields side-by-side: 'Height (ft)' hint '5.3' and 'Weight (kg)' hint '13'
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = height,
                        onValueChange = { height = it },
                        label = { Text("Height (ft)") },
                        placeholder = { Text("5.3") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("input_height"),
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = NutriTeal,
                            unfocusedBorderColor = NutriBorderGray,
                            focusedLabelColor = NutriTeal
                        )
                    )

                    OutlinedTextField(
                        value = weight,
                        onValueChange = { weight = it },
                        label = { Text("Weight (kg)") },
                        placeholder = { Text("13") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("input_weight"),
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = NutriTeal,
                            unfocusedBorderColor = NutriBorderGray,
                            focusedLabelColor = NutriTeal
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Field 'MUAC - Mid-Upper Arm Circumference (cm)' hint 'e.g., 13' with small info icon
                OutlinedTextField(
                    value = muac,
                    onValueChange = { muac = it },
                    label = { Text("MUAC - Arm Circumference (cm)") },
                    placeholder = { Text("e.g., 13") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    trailingIcon = {
                        IconButton(onClick = { showMuacInfoDialog = true }) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "MUAC Information",
                                tint = NutriTeal,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_muac"),
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = NutriTeal,
                        unfocusedBorderColor = NutriBorderGray,
                        focusedLabelColor = NutriTeal
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Section 'Activity Level' with 3 choice chips: Low, Moderate (selected), Active
                Text(
                    text = "Activity Level",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = NutriTextDark
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val activityLevels = listOf("Low", "Moderate", "Active")
                    activityLevels.forEach { level ->
                        val isSelected = activityLevel == level
                        FilterChip(
                            selected = isSelected,
                            onClick = { activityLevel = level },
                            label = {
                                Text(
                                    text = level,
                                    fontSize = 14.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = NutriTeal,
                                selectedLabelColor = Color.White,
                                containerColor = Color.White,
                                labelColor = NutriTextDark
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = isSelected,
                                borderColor = if (isSelected) NutriTeal else NutriBorderGray,
                                borderWidth = if (isSelected) 2.dp else 1.dp
                            ),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .testTag("chip_activity_$level")
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Section 'Diet Preference' with 3 chips: Veg, Non-Veg, Both (selected)
                Text(
                    text = "Diet Preference",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = NutriTextDark
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val dietPreferences = listOf("Veg", "Non-Veg", "Both")
                    dietPreferences.forEach { diet ->
                        val isSelected = dietPreference == diet
                        FilterChip(
                            selected = isSelected,
                            onClick = { dietPreference = diet },
                            label = {
                                Text(
                                    text = diet,
                                    fontSize = 14.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = NutriTeal,
                                selectedLabelColor = Color.White,
                                containerColor = Color.White,
                                labelColor = NutriTextDark
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = isSelected,
                                borderColor = if (isSelected) NutriTeal else NutriBorderGray,
                                borderWidth = if (isSelected) 2.dp else 1.dp
                            ),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .testTag("chip_diet_$diet")
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Fields: 'Allergies' hint 'e.g., Peanuts, Dairy'
                OutlinedTextField(
                    value = allergies,
                    onValueChange = { allergies = it },
                    label = { Text("Allergies") },
                    placeholder = { Text("e.g., Peanuts, Dairy") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_allergies"),
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = NutriTeal,
                        unfocusedBorderColor = NutriBorderGray,
                        focusedLabelColor = NutriTeal
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 'Health Notes' hint 'Any health conditions' multi-line
                OutlinedTextField(
                    value = healthNotes,
                    onValueChange = { healthNotes = it },
                    label = { Text("Health Notes") },
                    placeholder = { Text("Any health conditions, fever, digestion, etc.") },
                    minLines = 3,
                    maxLines = 5,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_health_notes"),
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = NutriTeal,
                        unfocusedBorderColor = NutriBorderGray,
                        focusedLabelColor = NutriTeal
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }

    // MUAC Information Dialog for Rural Mothers
    if (showMuacInfoDialog) {
        AlertDialog(
            onDismissRequest = { showMuacInfoDialog = false },
            title = {
                Text(
                    text = "Understanding MUAC",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Mid-Upper Arm Circumference (MUAC) is a rapid test to evaluate nutritional status:",
                        fontSize = 14.sp,
                        color = NutriTextDark
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF2E7D32))
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Green (> 12.5 cm): Normal nutrition",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFBC02D))
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Yellow (11.5 - 12.5 cm): Moderate risk",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFD32F2F))
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Red (< 11.5 cm): Severe risk (Immediate care)",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showMuacInfoDialog = false }) {
                    Text("Got it", color = NutriTeal, fontWeight = FontWeight.Bold)
                }
            }
        )
    }
}
