package com.messenger.jaber.core.data.network

import com.elveum.container.Container
import com.elveum.container.errorContainer
import com.elveum.container.successContainer
import com.messenger.core.essentials.exceptions.BackendException
import com.messenger.core.essentials.exceptions.ConnectionException
import com.messenger.core.essentials.exceptions.InvalidBackendResponseException
import com.messenger.core.essentials.exceptions.UnknownException
import com.messenger.jaber.core.data.network.converter.errorJson
import com.messenger.jaber.core.data.network.dto.ErrorDto
import kotlinx.serialization.SerializationException
import okio.IOException
import retrofit2.HttpException

inline fun <T> containerOf(block: () -> T): Container.Completed<T> {

    return try {
        successContainer(block())
    } catch (e: HttpException) {
        val code = e.code()
        val message = try {
            e.response()?.errorBody()?.string()
                ?.let { errorJson.decodeFromString<ErrorDto>(it) }
                ?.error
                ?: e.message()
        } catch (e: Exception) {
            return errorContainer(InvalidBackendResponseException(e))
        }
        errorContainer(BackendException(code, message, null))
    } catch (e: SerializationException) {
        errorContainer(InvalidBackendResponseException(e))
    } catch (e: IOException) {
        errorContainer(ConnectionException(e))
    } catch (e: Exception) {
        errorContainer(UnknownException(e))
    }
}