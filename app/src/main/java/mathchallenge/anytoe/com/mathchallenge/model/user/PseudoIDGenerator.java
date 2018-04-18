package mathchallenge.anytoe.com.mathchallenge.model.user;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import java.util.UUID;

/**
 * Created by anytoe on 05/10/2015.
 */
public class PseudoIDGenerator {

    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    /**
     * Return pseudo unique ID
     *
     * @return ID
     */
    public static String getUniquePsuedoID() {

        String m_szDevIDShort = "35" + (Build.BOARD.length() % 10) +
                (Build.BRAND.length() % 10) +
                //(Build.CPU_ABI.length() % 10) +
                (Build.DEVICE.length() % 10) +
                (Build.MANUFACTURER.length() % 10) +
                (Build.MODEL.length() % 10) +
                (Build.PRODUCT.length() % 10);

        String serial = null;
        try {
            serial = android.os.Build.class.getField("SERIAL").get(null).toString();

            // Go ahead and return the serial for api => 9
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            serial = "foihw4o8fr79fhw489fjo8jto8jiyiw3yir3qg";
        }

        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }

}
