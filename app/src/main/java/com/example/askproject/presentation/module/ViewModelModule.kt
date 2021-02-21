package com.example.askproject.presentation.module

import com.example.askproject.presentation.auth.login.LoginViewModel
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
//    viewModel<SplashViewModel>()
//
//    viewModel<WalkthroughViewModel>()
//
//    viewModel<AuthViewModel>()

    viewModel<LoginViewModel>()

}