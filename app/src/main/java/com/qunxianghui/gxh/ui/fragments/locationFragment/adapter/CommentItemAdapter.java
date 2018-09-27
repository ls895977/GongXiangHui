package com.qunxianghui.gxh.ui.fragments.locationFragment.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.location.CommentBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.UserUtil;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentItemAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<CommentBean> mList;
    private CommentRecallListener commentRecallListener;

    public void setCommentRecallListener(CommentRecallListener commentRecallListener) {
        this.commentRecallListener = commentRecallListener;
    }

    public CommentItemAdapter(Context context, List<CommentBean> mList, ListView listView) {
        this.mList = mList;
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 删除item
     */
    public void deleteItemView(int position) {
        mList.remove(position);
        notifyDataSetChanged();
    }

    public void refreshData(List<CommentBean> cBeans) {
        this.mList = cBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_comment, parent, false);
            holder.name = convertView.findViewById(R.id.name);
            holder.content = convertView.findViewById(R.id.content);
            holder.ll_comment_view = convertView.findViewById(R.id.ll_comment_view);
            holder.ll_comment_selflist = convertView.findViewById(R.id.ll_comment_selflist);
            holder.tv_item_reply_lb = convertView.findViewById(R.id.tv_item_reply_lb);
            holder.tv_item_replyed = convertView.findViewById(R.id.tv_item_replyed);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        showView(holder, position, parent);
        UserUtil user = UserUtil.getInstance();
        CommentBean comment = mList.get(position);
        if (!TextUtils.isEmpty(user.mNick) && user.mNick.equalsIgnoreCase(comment.getMember_name())) {
            holder.ll_comment_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog dialog = new AlertDialog.Builder(v.getContext()).setTitle("是否删除评论").setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            /*删除评论*/
                            OkGo.<String>post(Constant.DELETE_DISCUSS_URL).
                                    params("id", mList.get(position).getId())
                                    .execute(new JsonCallback<String>() {
                                        @Override
                                        public void onSuccess(Response<String> response) {
                                            deleteItemView(position);

                                        }
                                    });
                        }
                    }).setNeutralButton("否", null).create();
                    dialog.show();
                    dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.GRAY);
                    dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setTextColor(Color.RED);

                }
            });
        } else {
            holder.ll_comment_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commentRecallListener.recommentContentListener(position, mList.get(position), holder.content);
                }
            });
        }
        return convertView;
    }

    private void showView(final ViewHolder holder, final int position, ViewGroup parent) {
        if (!TextUtils.isEmpty(mList.get(position).getMember_reply_name())) {
            holder.tv_item_reply_lb.setVisibility(View.VISIBLE);
            holder.tv_item_replyed.setVisibility(View.VISIBLE);
            holder.name.setText(mList.get(position).getMember_name());


            holder.tv_item_reply_lb.setText("回复");
            holder.tv_item_replyed.setText(mList.get(position).getMember_reply_name()+":");
        } else {
            holder.tv_item_reply_lb.setVisibility(View.GONE);
            holder.tv_item_replyed.setVisibility(View.GONE);
            holder.name.setText(mList.get(position).getMember_name()+":");
        }

        String contentTitle ="";
        if(!TextUtils.isEmpty(mList.get(position).getMember_name())){
            contentTitle += holder.name.getText().toString();
        }
        if(!TextUtils.isEmpty(mList.get(position).getMember_reply_name()) &&
                !TextUtils.isEmpty(holder.tv_item_reply_lb.getText())){
            contentTitle += holder.tv_item_reply_lb.getText();
            contentTitle += holder.tv_item_replyed.getText();
        }
        SpannableStringBuilder textBuilder = new SpannableStringBuilder();
        if(contentTitle.length() > 0){
            for (int i = 0; i < contentTitle.length(); i++) {
                Pattern   pa   =   Pattern.compile( "^[\u4e00-\u9fa5]*$ ");
                Matcher matcher   =   pa.matcher(""+contentTitle.charAt(i));
                if(matcher.find()) {
                    textBuilder.append("\t\t");
                } else{
                    textBuilder.append("\t");
                }
            }
        }

        if(contentTitle.contains("回复")){
            contentTitle = contentTitle + "    ";
        } else{
            contentTitle+=" ";
        }

        SpannableStringBuilder span = new SpannableStringBuilder(contentTitle + mList.get(position).getContent());
        span.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0, contentTitle.length(),
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        holder.content.setText(span);

    }

    public static class ViewHolder {
        TextView name, content, tv_item_reply_lb, tv_item_replyed;
        LinearLayout ll_comment_selflist;
        LinearLayout ll_comment_view;
    }

    public interface CommentRecallListener {
        void recommentContentListener(int position, CommentBean commentBean, TextView topLocation);
    }

}
