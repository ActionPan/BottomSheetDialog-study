package bottomdialog.demo.com.bottomdialogdemo;

/**
 * Created by action on 16/9/8.
 */
public class SelectInfo {
    private String info;
    private boolean selected;

    public SelectInfo(String info, boolean selected){
        this.info=info;
        this.selected=selected;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
