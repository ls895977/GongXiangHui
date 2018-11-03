package com.qunxianghui.gxh.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.qunxianghui.gxh.R;

/**
 * Created by 10840 on 2015/12/4.
 */
@SuppressLint("AppCompatCustomView")
public class EditTextWithDelete extends EditText {
    Context context;
    Drawable deleteAllOn;//���̧��ʱ���ɾ��ͼ��
    Drawable deleteAllDown;//�������ʱ���ɾ��ͼ��

    public EditTextWithDelete(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    public EditTextWithDelete(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public EditTextWithDelete(Context context) {
        super(context);
        this.context = context;
        init();
    }


    private void init(){
        deleteAllOn = context.getResources().getDrawable(R.mipmap.delete);
        deleteAllDown = context.getResources().getDrawable(R.mipmap.delete_gray);
        addTextChangedListener(new TextWatcher(){//�����û�����
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                setDraw();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setDraw(){//�ػ�
        if(length() > 1){
            setCompoundDrawablesWithIntrinsicBounds(null,null,deleteAllOn,null);
          //  setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)��ʾ���ϡ��ҡ���
        }
        else{
            setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int) event.getRawX();
        int touchY = (int) event.getRawY();
        int touchX1 = (int) event.getX();
        int touchY1 = (int) event.getY();
        /*getX()�Ǳ�ʾview������������Ͻǵ�x����,
        ��getRawX()�Ǳ�ʾ�������Ļ���Ͻǵ�x����ֵ
        (ע��:�����Ļ���Ͻ����ֻ���Ļ���Ͻ�,
        ����activity�Ƿ���titleBar���Ƿ�ȫ��Ļ)
         */
        Rect rect = new Rect();
        getGlobalVisibleRect(rect);//��Rect���ؼ�������
        rect.left = rect.right - 40;//��Rect�ķ�Χ��С��ͼƬ��ʾ����ֻ�е�����ڸ�����Ż�ɾ����������

        if(length()>1){//������������ʱ�ڽ��в���
            if(rect.contains(touchX, touchY)){



                setCompoundDrawablesWithIntrinsicBounds(null,null,deleteAllDown,null);
            }else{
                setCompoundDrawablesWithIntrinsicBounds(null,null,deleteAllOn,null);
            }

            if(event.getAction() == MotionEvent.ACTION_UP){
                if(rect.contains(touchX, touchY)){
                    setText("");//���������ַ���
                }
                setDraw();//�ػ�
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
