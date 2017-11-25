package com.st00.afir.boardingpass;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.st00.afir.boardingpass.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this , R.layout.activity_main);
        BoardingPassInfo boardingPassInfo = FakeDataUtils.generateFakeBoardingPassInfo();
        displayBoardingPassInfo(boardingPassInfo);

    }

    private void displayBoardingPassInfo(BoardingPassInfo info) {

        mBinding.textViewPassengerName.setText(info.passengerName);
        mBinding.textViewOriginAirport.setText(info.originCode);
        mBinding.textViewFlightCode.setText(info.flightCode);
        mBinding.textViewDestinationAirport.setText(info.destCode);

        SimpleDateFormat formatter = new SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault());
        String arrivalTime = formatter.format(info.arrivalTime);
        String boardingTime = formatter.format(info.boardingTime);
        String departureTime = formatter.format(info.departureTime);

        mBinding.textViewArrivalTime.setText(arrivalTime);
        mBinding.textViewBoardingTime.setText(boardingTime);
        mBinding.textViewDepartureTime.setText(departureTime);

        long totalMinutesUntilBoarding = info.getMinutesUntilBoarding();
        long hoursUntilBoarding = TimeUnit.MINUTES.toHours(totalMinutesUntilBoarding);

        long minutesLessHoursUntilBoarding =
                totalMinutesUntilBoarding - TimeUnit.HOURS.toMinutes(hoursUntilBoarding);

        String hoursAndMinutesUntilBoarding = getString(R.string.countDownFormat,
                hoursUntilBoarding,
                minutesLessHoursUntilBoarding);

        mBinding.textViewBoardingInCountdown.setText(hoursAndMinutesUntilBoarding);
        mBinding.textViewTerminal.setText(info.departureTerminal);
        mBinding.textViewGate.setText(info.departureGate);
        mBinding.textViewSeat.setText(info.seatNumber);

    }
}
