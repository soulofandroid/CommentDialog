package com.commentdialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
/**
 * @author wanghuilin
 * @time 2016/5/31  10:29
 * <p/>
 * 详情评论视图
 */
public class CommentView extends LinearLayout {
    public CommentView(Context context) {
        this(context, null);
    }

    public CommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.comment_view_layout, this);
    }
}
