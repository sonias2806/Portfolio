package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PortfolioData

@Composable
fun ContactSection(
    prefilledSubject: String = "",
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var subject by remember(prefilledSubject) { mutableStateOf(prefilledSubject.ifEmpty { "Strategic Discussion / Opportunity" }) }
    var message by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var submittedSuccessfully by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        // Geometric Section Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier.size(36.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        Icons.Outlined.ContactMail,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Column {
                Text(
                    text = "Contact & Connect",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.testTag("contact_section_title")
                )
                Text(
                    text = "Explore synergies, strategic frameworks, and high-impact analytics initiatives.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Direct Contact Cards
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Email Card
            DirectContactCard(
                icon = Icons.Outlined.Email,
                title = "Email Address",
                value = PortfolioData.EMAIL,
                actionLabel = "Compose",
                onClick = {
                    sendEmail(context, PortfolioData.EMAIL, "Connecting with Sonia Saxena", "")
                }
            )

            // Phone Card
            DirectContactCard(
                icon = Icons.Outlined.Phone,
                title = "Phone / WhatsApp",
                value = PortfolioData.PHONE,
                actionLabel = "Direct Call",
                onClick = {
                    val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${PortfolioData.PHONE.replace(" ", "")}"))
                    try {
                        context.startActivity(dialIntent)
                    } catch (e: Exception) {
                        Toast.makeText(context, "Dialer unavailable", Toast.LENGTH_SHORT).show()
                    }
                }
            )

            // Location Card
            DirectContactCard(
                icon = Icons.Outlined.LocationOn,
                title = "Current Location",
                value = PortfolioData.LOCATION,
                actionLabel = "View City",
                onClick = {
                    val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Hyderabad,+Telangana"))
                    try {
                        context.startActivity(mapIntent)
                    } catch (e: Exception) {
                        Toast.makeText(context, PortfolioData.LOCATION, Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Interactive Contact Form Card (Geometric Rounded 24.dp)
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("contact_form_card")
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "Send a Direct Message",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp
                    )
                )

                if (submittedSuccessfully) {
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(
                                Icons.Outlined.CheckCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Message dispatched to email client! Sonia will respond promptly.",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }
                    }
                }

                // Name Input
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it; errorMessage = null },
                    label = { Text("Your Name *") },
                    leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_contact_name"),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )

                // Email Input
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it; errorMessage = null },
                    label = { Text("Your Email Address *") },
                    leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_contact_email"),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )

                // Subject Input
                OutlinedTextField(
                    value = subject,
                    onValueChange = { subject = it; errorMessage = null },
                    label = { Text("Subject *") },
                    leadingIcon = { Icon(Icons.Outlined.Subject, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_contact_subject"),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )

                // Message Input
                OutlinedTextField(
                    value = message,
                    onValueChange = { message = it; errorMessage = null },
                    label = { Text("Message / Strategic Inquiry *") },
                    leadingIcon = { Icon(Icons.Outlined.Message, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(115.dp)
                        .testTag("input_contact_message"),
                    shape = RoundedCornerShape(14.dp),
                    maxLines = 4
                )

                errorMessage?.let { err ->
                    Text(
                        text = err,
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = MaterialTheme.colorScheme.error,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }

                Button(
                    onClick = {
                        if (name.isBlank()) {
                            errorMessage = "Please enter your name"
                        } else if (email.isBlank() || !email.contains("@")) {
                            errorMessage = "Please provide a valid email address"
                        } else if (subject.isBlank()) {
                            errorMessage = "Please enter a subject line"
                        } else if (message.isBlank()) {
                            errorMessage = "Please enter your message"
                        } else {
                            errorMessage = null
                            val fullBody = "Sender: $name ($email)\n\nMessage:\n$message"
                            sendEmail(context, PortfolioData.EMAIL, subject, fullBody)
                            submittedSuccessfully = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("submit_contact_button"),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(Icons.Outlined.Send, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Send Direct Inquiry", fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Social / Profile Links Row (Pill Buttons)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = {
                    openBrowser(context, PortfolioData.LINKEDIN)
                },
                shape = RoundedCornerShape(50.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                modifier = Modifier
                    .weight(1f)
                    .testTag("link_linkedin")
            ) {
                Icon(Icons.Outlined.AccountBox, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("LinkedIn", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
            }

            OutlinedButton(
                onClick = {
                    openBrowser(context, PortfolioData.GITHUB)
                },
                shape = RoundedCornerShape(50.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                modifier = Modifier
                    .weight(1f)
                    .testTag("link_github")
            ) {
                Icon(Icons.Outlined.Code, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("GitHub", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Executive Geometric Footer
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "© 2026 Sonia Saxena • All Rights Reserved",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                text = "Hyderabad, Telangana • Business Analyst & AI Integration Specialist",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                    fontSize = 10.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
private fun DirectContactCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    value: String,
    actionLabel: String,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.onSecondaryContainer, modifier = Modifier.size(20.dp))
                    }
                }

                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 11.sp
                        )
                    )
                    Text(
                        text = value,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 14.sp
                        )
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(50.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Text(
                    text = actionLabel,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    ),
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                )
            }
        }
    }
}

private fun sendEmail(context: Context, to: String, subject: String, body: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$to")
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, body)
    }
    try {
        context.startActivity(Intent.createChooser(intent, "Send Email"))
    } catch (e: Exception) {
        Toast.makeText(context, "Email client unavailable", Toast.LENGTH_SHORT).show()
    }
}

private fun openBrowser(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Browser unavailable", Toast.LENGTH_SHORT).show()
    }
}

