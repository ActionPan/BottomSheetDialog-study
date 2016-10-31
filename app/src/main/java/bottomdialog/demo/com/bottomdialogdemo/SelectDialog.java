package bottomdialog.demo.com.bottomdialogdemo;


import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;


import java.util.List;


/**
 * Created by Action on 16/9/23.
 */
public class SelectDialog extends Dialog {
    private Context context;

    public SelectDialog(Context context) {
        super(context);
        this.context = context;
    }

    public SelectDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        DialogSelectAdapter adapter;
        private Context context;
        private String leftTxt;
        private String rigthTxt;
        dialogSelect selectCallBack;
        private List<SelectInfo> data;
        public Builder setRigthTxt(String rigthTxt) {
            this.rigthTxt = rigthTxt;
            return this;
        }

        public Builder setLeftTxt(String leftTxt) {
            this.leftTxt = leftTxt;
            return this;
        }

        public Builder setSelectCallBack(dialogSelect selectCallBack) {
            this.selectCallBack = selectCallBack;
            return this;
        }
        public Builder setData(List<SelectInfo> data) {
            this.data = data;
            return this;
        }

        public Builder(Context context) {
            this.context = context;
        }

        public SelectDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final SelectDialog dialog = new SelectDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.select_dialog, null);
//            ViewGroup.MarginLayoutParams params= new ViewGroup.MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            ViewGroup.MarginLayoutParams params= new ViewGroup.MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            dialog.addContentView(layout, params);
            params.leftMargin=0;
            params.rightMargin=0;

            Window dialogWindow = dialog.getWindow();
            dialogWindow.setGravity(Gravity.BOTTOM);
            dialogWindow.setWindowAnimations(R.style.actionSheetstyle);  //添加动画
//            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//            lp.x = 0;
//            lp.y = 0;
//            dialogWindow.setAttributes(lp);

            if(selectCallBack!=null){
                layout.findViewById(R.id.txt_done).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        selectCallBack.selectPosition(adapter.getSelectPosition(),adapter.getSelectStr());
                    }
                });
            }

            TextView left= (TextView) layout.findViewById(R.id.txt_left);
            TextView rigth= (TextView) layout.findViewById(R.id.txt_done);

            if (TextUtils.isEmpty(leftTxt)) {
                left.setVisibility(View.GONE);
            } else {
                left.setVisibility(View.VISIBLE);
                left.setText(leftTxt);
            }

            if (TextUtils.isEmpty(rigthTxt)) {
                rigth.setVisibility(View.GONE);
            } else {
                rigth.setVisibility(View.VISIBLE);
                rigth.setText(rigthTxt);
            }


            ListView listView= (ListView) layout.findViewById(R.id.list_view);

            listView.getLayoutParams().width= getDeviceWidth(context);
            adapter=new DialogSelectAdapter(context);
            listView.setAdapter(adapter);

            if (null!=data && data.size()>0) {
                adapter.setInfos(data);
            }

            return dialog;
        }
    }

    /**
     * 获取屏幕的宽度
     * @param context
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int getDeviceWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return manager.getDefaultDisplay().getWidth();
    }

    public interface dialogSelect{
      void selectPosition(int position, String txt);
    }

}