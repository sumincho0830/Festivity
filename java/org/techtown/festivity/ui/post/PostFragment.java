package org.techtown.festivity.ui.post;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.techtown.festivity.OnPostButtonClickListener;
import org.techtown.festivity.R;
import org.techtown.festivity.ui.Posting;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class PostFragment extends Fragment {
    //ArrayAdapter<>
    OnPostButtonClickListener listener;
    int[] itemImages = {
            R.drawable.birthday_party_spinner, R.drawable.brunch_party,R.drawable.dinner_party,
            R.drawable.pool_party, R.drawable.garden_barbeque, R.drawable.halloween,
            R.drawable.home_bar, R.drawable.karaoke_night, R.drawable.picnic,
            R.drawable.tea_party, R.drawable.poker_night, R.drawable.garden_party,
            R.drawable.ball
    };
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    EditText title;
    Button dateButton;
    EditText location;
    EditText content;
    Date selectedDate;
    TextView postButton;
    ImageView selectedImage;
    int selectedImgRes;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnPostButtonClickListener){
            listener = (OnPostButtonClickListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_addpost, container, false);


        title = root.findViewById(R.id.title_input);
        dateButton = root.findViewById(R.id.dateButton_input);
        location = root.findViewById(R.id.address_input);
        content = root.findViewById(R.id.content_input);
        postButton = root.findViewById(R.id.postButton_text);
        selectedImage = root.findViewById(R.id.selectedImage);

        // ================ DatePicker ==================
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        Date date = new Date();
        setDate(date);

        // ================= Spinner =================
        Spinner spinner = root.findViewById(R.id.spinner); //textview가 스피너가 될 수 있나..?
            //Create an ArrayAdapter using the string array and a custom spinner item layout;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(), R.array.spinner_items, R.layout.spinner_item); //직접 설정한 레이아웃으로 스피너 설정 가능
            //Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedImgRes = itemImages[position];
                selectedImage.setImageResource(selectedImgRes);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // ================= Post Button =================
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleStr = title.getText().toString();
                String dateStr = dateButton.getText().toString();
                String locationStr = location.getText().toString();
                String contentStr = content.getText().toString();
                int imageRes = selectedImgRes;

                Posting posting = new Posting(titleStr,contentStr,imageRes,dateStr, locationStr);
                //자, 이제 추가.
                if(listener != null){
                    listener.onPost(posting); //MainAcitvity의 메서드 호출
                }
            }
        });

        return root;
    }

    private  void showDateDialog(){

        String dateStr = dateButton.getText().toString();

        Calendar calendar = Calendar.getInstance();
        Date curDate = new Date();

        try{
            curDate = format.parse(dateStr); //문자열을 이용해 Date객체 생성
        }catch(Exception e){
            e.printStackTrace();;
        }

        calendar.setTime(curDate); //Date으로 시간 설정

        int curYear = calendar.get(Calendar.YEAR);
        int curMonth = calendar.get(Calendar.MONTH);
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(getContext(),dateSetListener,curYear, curMonth, curDay);
        // 이 액티비티에, dateSetListener메서드를, Year, month, day를 가지고 실행하는 객체를 생성할 것
        dialog.show();


    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            Date curDate = calendar.getTime();
            setDate(curDate); //버튼에 설정
        }
    };

    private void setDate(Date date){
        selectedDate = date;
        String dateStr = format.format(date);
        dateButton.setText(dateStr);
    }
}
