package droiddevelopers254.devfestnairobi.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel

import droiddevelopers254.devfestnairobi.datastates.AboutDetailsState
import droiddevelopers254.devfestnairobi.repository.AboutDetailsRepo

class AboutDetailsViewModel : ViewModel() {
    private val detailsStateMediatorLiveData: MediatorLiveData<AboutDetailsState> = MediatorLiveData()
    private val aboutDetailsRepo: AboutDetailsRepo = AboutDetailsRepo()

    val aboutDetails: LiveData<AboutDetailsState>
        get() = detailsStateMediatorLiveData

    fun fetchAboutDetails(aboutType: String) {
        val aboutDetailsStateLiveData = aboutDetailsRepo.getAboutDetails(aboutType)
        detailsStateMediatorLiveData.addSource(aboutDetailsStateLiveData
        ) { detailsStateMediatorLiveData ->
            if (this.detailsStateMediatorLiveData.hasActiveObservers()) {
                this.detailsStateMediatorLiveData.removeSource(aboutDetailsStateLiveData)
            }
            this.detailsStateMediatorLiveData.setValue(detailsStateMediatorLiveData)
        }
    }
}
