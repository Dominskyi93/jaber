package com.messenger.jaber.domain.repositories

import com.messenger.jaber.domain.entities.KeyFeature
import java.time.Period
import java.time.ZonedDateTime

interface KeyFeaturesRepository {
    suspend fun getKeyFeatures(): List<KeyFeature>
    suspend fun getDisplayPeriod(): Period
    suspend fun saveDisplayTime(keyFeature: KeyFeature, time: ZonedDateTime)
}


//@Singleton
//class KeyFeaturesRepository @Inject constructor() {
//
//    suspend fun getKeyFeatures(): List<KeyFeature> {
//        delay(1000)
//        return keyFeatures
//    }
//
//    suspend fun getDisplayPeriod(): Period {
//        return Period.ofDays(1)
//    }
//
//    suspend fun saveDisplayTime(keyFeature: KeyFeature, time: ZonedDateTime) {
//        keyFeatures = keyFeatures.map {
//            if (it.id == keyFeature.id) {
//                it.copy(lastDisplayTime = time)
//            } else {
//                it
//            }
//        }
//    }
//
//    private var keyFeatures = listOf(
//        KeyFeature(
//            id = Id(1),
//            title = "Security Configuration Audit",
//            description = "Automated assessment of critical Android security settings, including screen lock protection, device encryption, bootloader status, security patch level, SELinux mode, and USB debugging. Identifies misconfigurations that increase the risk of device compromise.",
//            image = ImageSource.Remote("https://picsum.photos/600/400")
//        ), KeyFeature(
//            id = Id(2),
//            title = "Application Risk Analysis",
//            description = "Analyzes installed applications to detect prohibited, sideloaded, or potentially dangerous apps, as well as applications with excessive or sensitive permissions. Provides clear explanations of why specific apps pose a security risk.",
//            image = ImageSource.Remote("https://picsum.photos/800")
//        ),
//        KeyFeature(
//            id = Id(3),
//            title = "Compliance Report & PDF Export",
//            description = "Generates a structured PDF report with audit results, risk levels, issue severity, technical details, and remediation recommendations. Designed for internal security reviews, compliance checks, and official documentation.",
//            image = ImageSource.Remote("https://picsum.photos/seed/security/600/400")
//        )
//    )
//}