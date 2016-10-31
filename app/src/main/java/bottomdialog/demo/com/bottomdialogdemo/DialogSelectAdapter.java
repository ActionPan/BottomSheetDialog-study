package bottomdialog.demo.com.bottomdialogdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Action on 16/9/23.
 */
public class DialogSelectAdapter extends BaseAdapter {

    private List<SelectInfo> infos=new ArrayList<>();
    private int selectPosition=0;

    private Context mContext;

    public DialogSelectAdapter(Context mContext){
        this.mContext=mContext;
    }

    public void setInfos(List<SelectInfo> infos) {
        if (infos!=null && infos.size()>0) {
            this.infos.clear();
            this.infos.addAll(infos);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public Object getItem(int i) {
        return infos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view= LayoutInflater.from(mContext).inflate(R.layout.dialog_listview_item,viewGroup,false);

        TextView txt= (TextView) view.findViewById(R.id.txt_language);
        TextView checkbox= (TextView) view.findViewById(R.id.img_checkbox);

        SelectInfo info=infos.get(i);
        if (getSelectPosition()==i && i==0) {
            info.setSelected(true);
        }

        txt.setText(info.getInfo());

        if (info.isSelected()) {
            checkbox.setBackgroundResource(R.drawable.tick_on);
            txt.setTextColor(mContext.getResources().getColor(R.color.gray_color_02));

        } else {
            checkbox.setBackgroundResource(R.drawable.tick_off);
            txt.setTextColor(mContext.getResources().getColor(R.color.gray_color_05));

        }
        view.setTag(i);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position= (int) view.getTag();
                infos.get(position);
                for (SelectInfo info:infos) {
                    info.setSelected(false);
                }
                infos.get(position).setSelected(true);
                notifyDataSetChanged();
                setSelectPosition(position);
            }
        });

        return view;
    }

    public int getSelectPosition() {
        return selectPosition;
    }
    public String getSelectStr(){
        return infos.get(selectPosition).getInfo();
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }
}
