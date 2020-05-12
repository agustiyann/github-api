package com.masscode.githubapi.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.masscode.githubapi.network.GithubApi
import com.masscode.githubapi.network.GithubData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    private val _items = MutableLiveData<List<GithubData>>()

    val items: LiveData<List<GithubData>>
        get() = _items

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    private var vmJob = Job()
    private var crScope = CoroutineScope(vmJob + Dispatchers.Main)

    init {
        initData()
    }

    private fun initData() {
        _response.value = "Loading...."

        // kotlin coroutine
        crScope.launch {
            try {
                val results = GithubApi.retrofitService.showList()

                if (results.isNotEmpty()) {
                    _items.value = results
                    _response.value = "OK"
                }
            } catch (t: Throwable) {
                _response.value = "Something went wrong!"
                t.stackTrace
                Log.d("debug", "error: $t")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }
}