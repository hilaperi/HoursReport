package hila.peri.hoursreportapp.ui.reports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import hila.peri.hoursreportapp.R;

import static hila.peri.hoursreportapp.ui.reports.DatePickerFragment.mDay;

public class DayFragment extends Fragment {

    DayFragment dayFrag;
    private String cday;
    private String sTypeOfDay;
    private double lat = 0.0;
    private double lng = 0.0;
    public static final String CDAY = "CDAY";
    public static final String STYPEOFDAY = "STYPEOFDAY";
    public static final String LAT = "LAT";
    public static final String LNG = "LNG";
    private Month monthDays = new Month();
    private Day d = new Day();
    private TextView dateNumber;
    private int numOfDays;
    TextView one_date_picker_kindOfWork;
    TextView one_date_pickerd_totalHour;
    TextView One_date_picker_editTotalHour;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.one_day_layout, container, false);

        findViews(view);
        initViews();

        return view;
    }

    private void initViews() {

        dateNumber.setText(String.valueOf(mDay));
//        createDates();



    }

    private void findViews(View view) {
        dateNumber = view.findViewById(R.id.dateNumber);
    }


//    private void createDates(){
//        Bundle bundle = this.getArguments();
//        if(bundle!= null) {
//            numOfDays = bundle.getInt("numOfDays", numOfDays);
//            if (numOfDays > 10) {
//                numOfDays = 10;
//            }
//            for (int i = 0; i < numOfDays; i++) {
//                cday = bundle.getString(CDAY + i, cday);
//                sTypeOfDay = bundle.getString(STYPEOFDAY + i, sTypeOfDay);
//                lat = bundle.getDouble(LAT+i, lat);
//                lng = bundle.getDouble(LNG+i, lng);
//                Day d = new Day(cday, sTypeOfDay , lat, lng);
//                monthDays.addDay(d);
//            }
//        }
//        final MyAdapter adapter = new MyAdapter(getContext(), R.layout.one_day_layout, monthDays);
//        regView.s
//        dateNumber.setText(monthDays.getDay(i).getDay());
//        one_date_picker_kindOfWork.setText(monthDays.getDay(i).getsTypeOfDay());

//    }

}
