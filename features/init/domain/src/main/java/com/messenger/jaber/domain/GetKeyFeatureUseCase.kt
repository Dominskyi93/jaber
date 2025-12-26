package com.messenger.jaber.domain

import com.messenger.core.essentials.Container
import com.messenger.core.essentials.entities.ImageSource
import com.messenger.jaber.domain.entities.KeyFeature
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


private val keyFeatures = listOf(
    KeyFeature(
        id = 1L,
        title = "Security Configuration Audit",
        description = "Automated assessment of critical Android security settings, including screen lock protection, device encryption, bootloader status, security patch level, SELinux mode, and USB debugging. Identifies misconfigurations that increase the risk of device compromise.",
        image = ImageSource.Remote("https://picsum.photos/600/400")
    ), KeyFeature(
        id = 2L,
        title = "Application Risk Analysis",
        description = "Analyzes installed applications to detect prohibited, sideloaded, or potentially dangerous apps, as well as applications with excessive or sensitive permissions. Provides clear explanations of why specific apps pose a security risk.",
        image = ImageSource.Remote("https://picsum.photos/800")
    ),
    KeyFeature(
        id = 3L,
        title = "Compliance Report & PDF Export",
        description = "Generates a structured PDF report with audit results, risk levels, issue severity, technical details, and remediation recommendations. Designed for internal security reviews, compliance checks, and official documentation.",
        image = ImageSource.Remote("https://picsum.photos/seed/security/600/400")
    )
)

class GetKeyFeatureUseCase @Inject constructor() {

    fun invoke(): Flow<Container<KeyFeature>> = flow {
        emit(Container.Loading)
        delay(2000)
        emit(Container.Success(keyFeatures.random()))
    }
}

