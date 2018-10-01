package com.commentdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author wanghuilin
 * @time 2016/5/31  14:50
 */
public class CommentDialog extends Dialog {
    private EditText editText;
    private TextView commentTV;
    private ProgressBar progressBar;

    public CommentDialog(Context context) {
        this(context, R.style.comment_style);
    }


    public CommentDialog(Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.comment_dialog_layout);
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);//设置dialog的显示位置
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE;//显示dialog的时候,就显示软键盘
        attributes.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;//弹窗获取焦点
        WindowManager windowManager = window.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        attributes.width = (int) (display.getWidth());//设置dialog的宽度占满整个屏幕
        window.setAttributes(attributes);
        initView();
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.editText);
        commentTV = (TextView) findViewById(R.id.textview);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())) {
                    commentTV.setEnabled(true);
                } else {
                    commentTV.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        commentTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.commentContent(editText.getText().toString());
                    showProgress();
                }
            }
        });

    }


    private Listener listener;

    public CommentDialog setListener(Listener listener) {
        this.listener = listener;
        return this;
    }

    public interface Listener {
        void commentContent(String content);
    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        commentTV.setClickable(false);
    }

    private void cancelProgerss() {
        progressBar.setVisibility(View.GONE);
        commentTV.setClickable(true);
    }

    public void pushCommentError() {
        cancelProgerss();

    }


}
