package com.goodfellows.morssenger;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<MessageBubble> {

    private Activity activity;
    private List<MessageBubble> messages;

    public MessageAdapter(Activity context, int resource, List<MessageBubble> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.messages = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        int layoutResource = 0; // determined by view type
        MessageBubble messageBubble = getItem(position);
        int viewType = getItemViewType(position);

        if (messageBubble.myMessage()) {
            layoutResource = R.layout.my_message;
        }

        else {
            layoutResource = R.layout.their_message;
        }

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        }

        else {
            convertView = inflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        // Set message content
        holder.message.setText(messageBubble.getContent());

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        // return the total number of view types. this value should never change
        // at runtime. Value 2 is returned because of left and right views.
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        // return a value between 0 and (getViewTypeCount - 1)
        return position % 2;
    }

    private class ViewHolder {
        private TextView message;
        public ViewHolder(View v) {
            message = (TextView) v.findViewById(R.id.message_body);
        }
    }
}