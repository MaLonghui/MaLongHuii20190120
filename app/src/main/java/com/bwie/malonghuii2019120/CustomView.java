package com.bwie.malonghuii2019120;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomView extends RelativeLayout {
    @BindView(R.id.del_img)
    ImageView delImg;
    @BindView(R.id.edit_num)
    EditText editNum;
    @BindView(R.id.add_img)
    ImageView addImg;
    private AddOrDelClickListener addOrDelClickListener;

    public void setAddOrDelClickListener(AddOrDelClickListener addOrDelClickListener) {
        this.addOrDelClickListener = addOrDelClickListener;
    }

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = View.inflate(context, R.layout.custom_layout, null);
        ButterKnife.bind(this,view);
        editNum.setText("20");
        addView(view);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setNum(int num){
        if (num>0){
            editNum.setText(num+"");
        }else{
            setToast();
        }

    }
    public int getNum(){
        String string = editNum.getText().toString();
        int num = Integer.parseInt(string);
        return num;

    }

    private void setToast() {
        Toast.makeText(getContext(), "不能小于0", Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.del_img, R.id.add_img})
    public void onViewClicked(View view) {
        String string = editNum.getText().toString();
        switch (view.getId()) {
            case R.id.del_img:
                if (string.length()<=0){
                    toast();
                }else{
                    addOrDelClickListener.delClickListener(view);
                }

                break;
            case R.id.add_img:
                if (string.length()<=0){
                    toast();
                }else{
                    addOrDelClickListener.addClickListener(view);
                }

                break;
        }
    }

    private void toast() {
        Toast.makeText(getContext(), "不能为空", Toast.LENGTH_SHORT).show();
    }

    //定义接口
    public interface AddOrDelClickListener{
        //点击加减的监听
        void addClickListener(View view);
        void delClickListener(View view);
    }
}
