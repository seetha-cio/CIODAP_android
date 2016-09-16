package com.connectedio.ciodav001;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.CellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.widget.ListView;
import android.widget.TextView;

import com.connectedio.ciodav001.adapter.ResultListAdapter;
import com.connectedio.ciodav001.model.Result;

import java.util.ArrayList;
import java.util.List;

public class SurveyResultActivity extends AppCompatActivity {
    TextView tv_data;
    public static final int UNKNOW_CODE = 99;
    int MAX_SIGNAL_DBM_VALUE = 31;

    TelephonyManager tel;
    MyPhoneStateListener myPhoneStateListener;
    ListView lv_result;
    ResultListAdapter adapter;
    ArrayList<Result> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_result);
        lv_result = (ListView) findViewById(R.id.lv_result);
        tv_data = (TextView) findViewById(R.id.tv_data);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1);

        }
            results = new ArrayList<Result>();
            adapter = new ResultListAdapter(this, results);
            myPhoneStateListener = new MyPhoneStateListener();
            tel = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            tel.listen(myPhoneStateListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
            tel.listen(myPhoneStateListener, PhoneStateListener.LISTEN_CELL_INFO);

    }
    private class MyPhoneStateListener extends PhoneStateListener{


        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
        }

        @Override
        public void onCellInfoChanged(List<CellInfo> cellInfo) {
            super.onCellInfoChanged(cellInfo);
        }

        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            if (null != signalStrength && signalStrength.getGsmSignalStrength() != UNKNOW_CODE) {
                int signalStrengthPercent = calculateSignalStrengthInPercent(signalStrength.getGsmSignalStrength());
                String signal = signalStrengthPercent + "";
                results.add(new Result("CDMA RSSI value", signalStrength.getCdmaDbm() + " (dBm)"));
                results.add(new Result("CDMA Ec/Io value", signalStrength.getCdmaEcio() + " (dB*10)"));
                results.add(new Result("EVDO RSSI value", signalStrength.getEvdoDbm() + " (dBm)"));
                results.add(new Result("EVDO Ec/Io value", signalStrength.getEvdoEcio() + " (dB*10)"));
                results.add(new Result("EVDO SNR (0-8)", signalStrength.getEvdoSnr() + ""));
                results.add(new Result("GSM bit error rate (0-7, 99).", signalStrength.getGsmBitErrorRate() + ""));
                results.add(new Result("GSM Signal Strength, (0-31, 99)", signalStrength.getGsmSignalStrength() + ""));
                results.add(new Result("GSM ASU", (int) (signalStrength.getCdmaDbm() + 113) / 2 + ""));
                lv_result.setAdapter(adapter);
                tv_data.setText(signalStrength.toString());
            }

        }
    }
    private int calculateSignalStrengthInPercent(int signalStrength) {
        return (int) ((float) signalStrength / MAX_SIGNAL_DBM_VALUE * 100);
    }
}
