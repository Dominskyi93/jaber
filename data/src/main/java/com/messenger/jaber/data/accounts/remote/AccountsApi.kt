package com.messenger.jaber.data.accounts.remote

import com.messenger.jaber.data.accounts.remote.dto.SignInRequestDto
import com.messenger.jaber.data.accounts.remote.dto.SignInResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AccountsApi {

    @POST
    fun signIn(@Body request: SignInRequestDto): SignInResponseDto


}