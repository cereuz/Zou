package com.zao.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zao.zou.R;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/22 17:55
 */
public class TitleBar extends FrameLayout {

    private View mView;
    private Context mContext;

    ImageView ivTitleBarBack;
    TextView tvTitleBarTitle;
    ImageView ivRight;
    TextView tvRight;
    ImageView ivLogo;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context,
                    @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
        init(context, attrs);
    }

    private void initView() {
        ivTitleBarBack = (ImageView) findViewById(R.id.tb_iv_back);
        ivRight = (ImageView) findViewById(R.id.tb_iv_right_imageview);
        ivLogo = (ImageView) findViewById(R.id.iv_titlebar_logo);
        tvTitleBarTitle = (TextView) findViewById(R.id.tb_tv_title);
        tvRight = (TextView) findViewById(R.id.tb_tv_right_textview);
    }

    private void init(Context context, AttributeSet attrs) {
        mView = View.inflate(context, R.layout.title_bar, null);
        addView(mView);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        boolean isTitleShow = typedArray.getBoolean(R.styleable.TitleBar_isTitleShow, true);
        if (isTitleShow) {
            setTitle(context, typedArray);
        }
        boolean isBackButtonShow = typedArray.getBoolean(R.styleable.TitleBar_isBackButtonShow, true);
        if (isBackButtonShow) {
            setBackImage(typedArray);
        } else {
            ivTitleBarBack.setVisibility(GONE);
        }
        boolean isRightImageViewShow = typedArray
                .getBoolean(R.styleable.TitleBar_isRightImageViewShow, false);
        if (isRightImageViewShow) {
            int rightImageRes = typedArray.getResourceId(R.styleable.TitleBar_rightImage, 0);
            if (rightImageRes != 0) {
                ivRight.setImageResource(rightImageRes);
            }
            ivRight.setVisibility(VISIBLE);
        } else {
            ivRight.setVisibility(GONE);
        }
        boolean isRightTextViewShow = typedArray
                .getBoolean(R.styleable.TitleBar_isRightTextViewShow, false);
        if (isRightTextViewShow) {
            String rightText = typedArray.getString(R.styleable.TitleBar_rightText);
            tvRight.setText(rightText);
            tvRight.setVisibility(VISIBLE);
        } else {
            tvRight.setVisibility(GONE);
        }
        int logoRes = typedArray.getResourceId(R.styleable.TitleBar_logo,0);
        if (logoRes!=0){
            ivLogo.setImageResource(logoRes);
            ivLogo.setVisibility(View.VISIBLE);
        } else {
            ivLogo.setVisibility(View.GONE);
        }
    }

    private void setBackImage(TypedArray typedArray) {
        int backButtonRes = typedArray.getInt(R.styleable.TitleBar_backButtomColor, 0);
        //todo 设置后退图片
//    if (backButtonRes == 0) {
//      ivTitleBarBack.setImageResource(R.drawable.titile_back_black);
//    } else if (backButtonRes == 1) {
//      ivTitleBarBack.setImageResource(R.drawable.title_back_white);
//    }
        ivTitleBarBack.setVisibility(VISIBLE);
    }

    private void setTitle(Context context, TypedArray typedArray) {
        String title = typedArray.getString(R.styleable.TitleBar_titleText);
        int titleTextColor = typedArray.getColor(R.styleable.TitleBar_titleTextColor,
                context.getResources().getColor(R.color.colorBlack));
        tvTitleBarTitle.setTextColor(titleTextColor);
        tvTitleBarTitle.setText(title);
    }

    public void setRightImageViewClickListener(OnClickListener onClickListener){
        ivRight.setOnClickListener(onClickListener);
    }

    public void setRightTextViewClickListener(OnClickListener onClickListener){
        tvRight.setOnClickListener(onClickListener);
    }
    public void setIvTitleBarBackClickListener(OnClickListener onClickListener){
        ivTitleBarBack.setOnClickListener(onClickListener);
    }

    public View getmView() {
        return mView;
    }

    public ImageView getIvTitleBarBack() {
        return ivTitleBarBack;
    }

    public TextView getTvTitleBarTitle() {
        return tvTitleBarTitle;
    }

    public ImageView getIvRight() {
        return ivRight;
    }

    public TextView getTvRight() {
        return tvRight;
    }

    public void setTitle(String title){
        tvTitleBarTitle.setText(title);
    }

    public void backButtonVisible(boolean isVisible){
        ivTitleBarBack.setVisibility(isVisible?VISIBLE:GONE);
    }

    public void setRightImage(int imageResourse){
        ivRight.setImageResource(imageResourse);
    }

    public void setRightImageShow(boolean isShowing){
        ivRight.setVisibility(isShowing?VISIBLE:GONE);
    }
    public void setRightTextShow(boolean isShowing){
        tvRight.setVisibility(isShowing?VISIBLE:GONE);
    }
}
