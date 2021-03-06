package com.alexjudin.lifehacktestcase.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alexjudin.lifehacktestcase.LifeHackApp
import com.alexjudin.lifehacktestcase.R
import com.alexjudin.lifehacktestcase.presentation.viewmodel.CompanyViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: CompanyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_Screen)
        setContentView(R.layout.activity_main)

        LifeHackApp.component.injectMainActivity(this)


    }
}