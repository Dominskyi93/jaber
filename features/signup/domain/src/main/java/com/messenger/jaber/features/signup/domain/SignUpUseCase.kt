package com.messenger.jaber.features.signup.domain

import com.messenger.jaber.features.signup.domain.entities.NewAccount
import com.messenger.jaber.features.signup.domain.exceptions.EmptyFieldException
import com.messenger.jaber.features.signup.domain.exceptions.InvalidRangeException
import com.messenger.jaber.features.signup.domain.exceptions.LoginAlreadyExistException
import com.messenger.jaber.features.signup.domain.exceptions.PasswordMismatchException
import com.messenger.jaber.features.signup.domain.exceptions.TooLongValueException
import com.messenger.jaber.features.signup.domain.exceptions.TooShortValueException

interface SignUpUseCase {

    /**
     * @throws EmptyFieldException
     * @throws InvalidRangeException
     * @throws LoginAlreadyExistException
     * @throws PasswordMismatchException
     * @throws TooLongValueException
     * @throws TooShortValueException
     */
    suspend operator fun invoke(account: NewAccount)
}