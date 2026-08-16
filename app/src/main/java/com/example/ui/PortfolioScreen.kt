package com.example.ui

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.PortfolioData
import com.example.ui.components.*
import com.example.ui.theme.GeoThemeMoonIndigo
import com.example.ui.theme.GeoThemeSunAmber
import com.example.ui.theme.LocalThemeController
import com.example.ui.theme.ThemeMode
import com.example.ui.theme.ThemeSelectorDialog
import kotlinx.coroutines.launch

enum class PortfolioSection(val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    ABOUT("About", Icons.Outlined.Person),
    SKILLS("Skills", Icons.Outlined.Psychology),
    EXPERIENCE("Experience", Icons.Outlined.WorkHistory),
    PROJECTS("Projects", Icons.Outlined.Layers),
    CREDENTIALS("Certifications", Icons.Outlined.School),
    CONTACT("Contact", Icons.Outlined.Mail)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortfolioScreen(
    modifier: Modifier = Modifier
) {
    val themeController = LocalThemeController.current
    val systemInDark = isSystemInDarkTheme()
    val isDarkTheme = themeController.isDark(systemInDark)

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var showResumeDialog by remember { mutableStateOf(false) }
    var showThemeDialog by remember { mutableStateOf(false) }
    var prefilledContactSubject by remember { mutableStateOf("") }
    var activeSection by remember { mutableStateOf(PortfolioSection.ABOUT) }

    // Map sections to index in LazyColumn:
    // 0 -> Hero
    // 1 -> Skills
    // 2 -> Experience
    // 3 -> Projects
    // 4 -> Credentials
    // 5 -> Contact
    val sectionIndexMap = mapOf(
        PortfolioSection.ABOUT to 0,
        PortfolioSection.SKILLS to 1,
        PortfolioSection.EXPERIENCE to 2,
        PortfolioSection.PROJECTS to 3,
        PortfolioSection.CREDENTIALS to 4,
        PortfolioSection.CONTACT to 5
    )

    fun scrollToSection(section: PortfolioSection) {
        activeSection = section
        coroutineScope.launch {
            val targetIndex = sectionIndexMap[section] ?: 0
            listState.animateScrollToItem(targetIndex)
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Geometric SS Monogram Circle
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(38.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "SS",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                        }

                        Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                            Text(
                                text = "PORTFOLIO",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.2.sp,
                                    fontSize = 10.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                            Text(
                                text = PortfolioData.NAME,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            )
                        }
                    }
                },
                actions = {
                    // Dark / Light / System Mode Toggle in Geometric Container
                    IconButton(
                        onClick = { themeController.toggleTheme(systemInDark) },
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .size(38.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f))
                            .testTag("theme_toggle_button")
                    ) {
                        Icon(
                            imageVector = if (isDarkTheme) Icons.Outlined.LightMode else Icons.Outlined.DarkMode,
                            contentDescription = if (isDarkTheme) "Switch to Light Mode" else "Switch to Dark Mode",
                            tint = if (isDarkTheme) GeoThemeSunAmber else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    // Appearance Options Button (Opens full Theme selector)
                    IconButton(
                        onClick = { showThemeDialog = true },
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .size(38.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                            .testTag("theme_options_button")
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Palette,
                            contentDescription = "Display Appearance Settings",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    // Resume Pill Button in Top Bar
                    Button(
                        onClick = { showResumeDialog = true },
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .height(36.dp)
                            .testTag("topbar_resume_button"),
                        shape = RoundedCornerShape(50.dp),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) {
                        Icon(Icons.Outlined.Description, contentDescription = null, modifier = Modifier.size(15.dp))
                        Spacer(modifier = Modifier.width(5.dp))
                        Text("Resume", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        bottomBar = {
            // Geometric Balanced Navigation Bar
            Surface(
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                color = MaterialTheme.colorScheme.surface
            ) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 0.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("portfolio_navigation_bar")
                ) {
                    PortfolioSection.values().forEach { section ->
                        val selected = activeSection == section
                        NavigationBarItem(
                            selected = selected,
                            onClick = { scrollToSection(section) },
                            icon = {
                                Icon(section.icon, contentDescription = section.title)
                            },
                            label = {
                                Text(
                                    text = section.title,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 10.sp,
                                        fontWeight = if (selected) FontWeight.ExtraBold else FontWeight.Medium
                                    )
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                            )
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { scrollToSection(PortfolioSection.CONTACT) },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape,
                modifier = Modifier.testTag("fab_quick_contact")
            ) {
                Icon(Icons.Outlined.Chat, contentDescription = "Quick Contact")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // 0: Hero Section
            item(key = "section_hero") {
                HeroSection(
                    onNavigateToProjects = { scrollToSection(PortfolioSection.PROJECTS) },
                    onNavigateToContact = { scrollToSection(PortfolioSection.CONTACT) },
                    onOpenResume = { showResumeDialog = true }
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            }

            // 1: Skills Section
            item(key = "section_skills") {
                SkillsSection()
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            }

            // 2: Experience Timeline
            item(key = "section_experience") {
                ExperienceTimelineSection()
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            }

            // 3: Projects Section
            item(key = "section_projects") {
                ProjectsSection(
                    onDiscussProject = { project ->
                        prefilledContactSubject = "Discussion regarding: ${project.title}"
                        scrollToSection(PortfolioSection.CONTACT)
                    }
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            }

            // 4: Education & Certifications
            item(key = "section_credentials") {
                EducationCertificationsSection()
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            }

            // 5: Contact Section & Footer
            item(key = "section_contact") {
                ContactSection(
                    prefilledSubject = prefilledContactSubject
                )
            }
        }
    }

    // Resume Dialog
    if (showResumeDialog) {
        ResumeDialog(onDismiss = { showResumeDialog = false })
    }

    // Theme Appearance Selector Dialog
    if (showThemeDialog) {
        ThemeSelectorDialog(onDismiss = { showThemeDialog = false })
    }
}
