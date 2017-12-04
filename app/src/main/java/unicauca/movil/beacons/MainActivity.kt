package unicauca.movil.beacons

import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker
import io.reactivex.disposables.Disposable
import unicauca.movil.beacons.receivers.BeaconReceiver

class MainActivity : AppCompatActivity() {

    private val beaconReceiver:BeaconReceiver = BeaconReceiver()
    lateinit var disposable:Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SystemRequirementsChecker.checkWithDefaultDialogs(this)
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(BeaconReceiver.ACTION)
        registerReceiver(beaconReceiver, filter)

        disposable = beaconReceiver.getBeacons()
                .subscribe { beacon->
                    Log.i("beacons123","major : ${beacon.first} minor: ${beacon.second}")

                }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(beaconReceiver)
        disposable.dispose()
    }


}
