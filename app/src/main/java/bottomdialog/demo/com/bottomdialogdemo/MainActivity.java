package bottomdialog.demo.com.bottomdialogdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button dialog;
    private TextView sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = (Button) findViewById(R.id.bottomdialog);
        sex = (TextView) findViewById(R.id.sex);

        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSex();
            }
        });
    }

    // 选择性别
    private void selectSex() {
        List<SelectInfo> infoList = new ArrayList<>();

        String[] languages = getResources().getStringArray(R.array.sexArray);
        for (String lang : languages) {
            infoList.add(new SelectInfo(lang, false));
        }

        SelectDialog.dialogSelect selectCallback = new SelectDialog.dialogSelect() {
            @Override
            public void selectPosition(int position, String txt) {
                sex.setText(txt);
                sex.setTag(position);
            }
        };

        SelectDialog dialog = new SelectDialog.Builder(this)
                .setLeftTxt("性别")
                .setRigthTxt("确定")
                .setSelectCallBack(selectCallback)
                .setData(infoList)
                .create();
        dialog.show();

    }
}
