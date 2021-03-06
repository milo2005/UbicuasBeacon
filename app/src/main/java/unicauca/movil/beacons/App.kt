package unicauca.movil.beacons

import android.app.Application
import android.content.Intent
import com.estimote.coresdk.observation.region.Region
import com.estimote.coresdk.observation.region.beacon.BeaconRegion
import com.estimote.coresdk.recognition.packets.Beacon
import com.estimote.coresdk.service.BeaconManager
import unicauca.movil.beacons.receivers.BeaconReceiver

class App : Application(), BeaconManager.BeaconMonitoringListener {

    override fun onCreate() {
        super.onCreate()
        val manager = BeaconManager(this)
        manager.connect {
            manager.startMonitoring(BeaconRegion("pinturas", null,
                    null, null))
            manager.setMonitoringListener(this)
        }
    }

    override fun onExitedRegion(beaconRegion: BeaconRegion?) {

    }

    override fun onEnteredRegion(beaconRegion: BeaconRegion?, beacons: MutableList<Beacon>) {
        val major = beacons[0].major
        val minor = beacons[0].minor

        val intent =  Intent(BeaconReceiver.ACTION)
        intent.putExtra(BeaconReceiver.EXTRA_MAJOR, major)
        intent.putExtra(BeaconReceiver.EXTRA_MINOR, minor)

        sendBroadcast(intent)
    }

}