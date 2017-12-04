package unicauca.movil.beacons.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class BeaconReceiver : BroadcastReceiver() {

    private val beacons: PublishSubject<Pair<Int, Int>> = PublishSubject.create()


    override fun onReceive(context: Context?, intent: Intent) {
        val minor = intent.extras.getInt(EXTRA_MINOR)
        val major = intent.extras.getInt(EXTRA_MAJOR)
        beacons.onNext(major to minor)

    }

    fun getBeacons(): Observable<Pair<Int, Int>> = beacons
            .distinctUntilChanged { b1, b2 -> b1.first == b2.first}


    companion object {
        val ACTION = "unicauca.movil.beacons.region"
        val EXTRA_MAJOR = "major"
        val EXTRA_MINOR = "minor"
    }

}
