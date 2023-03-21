package com.larrex.myapplication.hilt_di.repositoryImpl

import android.app.Application
import com.larrex.myapplication.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private var application: Application,) : Repository {
    override fun getIgboApiResponse() {

    }
}