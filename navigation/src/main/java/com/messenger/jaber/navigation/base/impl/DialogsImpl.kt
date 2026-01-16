package com.messenger.jaber.navigation.base.impl

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import com.messenger.core.essentials.dialogs.Dialogs
import com.messenger.core.essentials.dialogs.DialogsConfig
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DialogsImpl @Inject constructor() : Dialogs {
    private val dialogState = SnapshotStateList<DialogRecord>()
    private var idSeq = 0L

    override suspend fun showAlertDialog(config: DialogsConfig): Boolean {
        return suspendCancellableCoroutine { continuation ->
            val currentId = ++idSeq
            val onDismissDialog = {
                dialogState.removeIf {
                    it.id == currentId
                }
            }
            val record = DialogRecord(
                id = idSeq,
                config = config,
                onConfirm = {
                    onDismissDialog()
                    continuation.resume(true)
                },
                onDismiss = {
                    onDismissDialog()
                    continuation.resume(false)
                }
            )
            dialogState.add(record)
            continuation.invokeOnCancellation {
                onDismissDialog()
            }
        }
    }

    @Composable
    fun Renderer(modifier: Modifier = Modifier) {
        dialogState.forEach { it.Renderer() }
    }

    @Composable
    private fun DialogRecord.Renderer() {
        val currentNegativeButton = negativeButton

        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    onClick = onConfirm
                ) {
                    Text(positiveButton)
                }
            },
            dismissButton = if (!currentNegativeButton.isNullOrBlank()) {
                {
                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text(currentNegativeButton)
                    }
                }
            } else {
                null
            },
            title = {
                Text(
                    text = title
                )
            },
            text = {
                Text(
                    text = message
                )
            }
        )
    }

    private data class DialogRecord(
        val id: Long,
        val config: DialogsConfig,
        val onConfirm: () -> Unit,
        val onDismiss: () -> Unit
    ) : DialogsConfig by config
}