//package hila.peri.hoursreportapp;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.CalendarView;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.Filterable;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.ThemedSpinnerAdapter;
//
//import androidx.annotation.NonNull;
//
//import hila.peri.hoursreportapp.ui.reports.Day;
//import hila.peri.hoursreportapp.ui.reports.Month;
//
//
//public class MyAdapter extends BaseAdapter {
//
//    private final Context context;
//    private final Month monthDays;
//    private int resource;
//    private Day day = new Day();
//
//
//    public MyAdapter(@NonNull Context context, int resource, Month monthDays) {
//        super();
//        this.context = context;
//        this.monthDays = monthDays;
//        this.resource = resource;
//    }
//
//
//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
////        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////        View view = inflater.inflate(resource, parent, false);
//        TextView dateNumber = view.findViewById(R.id.dateNumber);
//        TextView one_date_picker_kindOfWork = view.findViewById(R.id.one_date_picker_kindOfWork);
//        TextView one_date_pickerd_totalHour = view.findViewById(R.id.one_date_pickerd_totalHour);
//        TextView One_date_picker_editTotalHour = view.findViewById(R.id.One_date_picker_editTotalHour);
//        dateNumber.setText(monthDays.getDay(i).getDay());
//        one_date_picker_kindOfWork.setText(monthDays.getDay(i).getsTypeOfDay());
////        One_date_picker_editTotalHour.setText(monthDays.getDay(i).);
//
//        return view;
//    }
//
////    @Override
////    public View getView(int position, View convertView, ViewGroup parent) {
////        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////        View view = inflater.inflate(resource, parent, false);
////            TextView dateNumber = view.findViewById(R.id.dateNumber);
////            TextView one_date_picker_kindOfWork = view.findViewById(R.id.one_date_picker_kindOfWork);
////            TextView one_date_pickerd_totalHour = view.findViewById(R.id.one_date_pickerd_totalHour);
////            TextView One_date_picker_editTotalHour = view.findViewById(R.id.One_date_picker_editTotalHour);
////            dateNumber.setText((position+1)+") "+monthDays.getDay(position).getDay());
////            one_date_picker_kindOfWork.setText();
////
////        return view;
//////    }
//
//
//}
//
//
//
////public class MyAdapter extends BaseAdapter {
//////    protected void onCreate(Bundle savedInstanceState) {
//////        Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);
//////        // Create an ArrayAdapter using the string array and a default spinner layout
//////        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//////                R.array.planets_array, android.R.layout.simple_spinner_item);
//////        // Specify the layout to use when the list of choices appears
//////        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//////        // Apply the adapter to the spinner
//////        spinner.setAdapter(adapter);
////  //  }
////private final Context context;
//////    private final TopTenRecords tenRecords;
////    private int resource;
////
////    public MyAdapter(@NonNull Context context, int resource) {
//////        super(context, resource);
////        super();
////        this.context = context;
////        this.resource = resource;
////    }
////
////
////    @Override
////    public int getCount() {
////        return 0;
////    }
////
////    @Override
////    public Object getItem(int position) {
////        return null;
////    }
////
////    @Override
////    public long getItemId(int position) {
////        return 0;
////    }
////
////    @Override
////    public View getView(int position, View convertView, ViewGroup parent) {
////
////        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////        View calView = inflater.inflate(resource, parent, false);
////        DatePicker thePicker= calView.findViewById(R.id.datePicker1);
////        TextView editTotalMonthHours = calView.findViewById(R.id.editTotalMonthHours);
////        EditText salary_TXT = calView.findViewById(R.id.salary_TXT);
////        Button calc_BTN_calc = calView.findViewById(R.id.calc_BTN_calc);
////        TextView editTotalSalary = calView.findViewById(R.id.editTotalSalary);
////        TextView editTotalFreeDayes = calView.findViewById(R.id.editTotalFreeDayes);
////        TextView editTotalWorkingDayes = calView.findViewById(R.id.editTotalWorkingDayes);
////        TextView editTotalSickDays = calView.findViewById(R.id.editTotalSickDays);
////
////
////
////        return null;
////    }
////}