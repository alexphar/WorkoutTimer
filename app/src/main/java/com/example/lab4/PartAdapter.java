package com.example.lab4;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lab4.R;

import java.util.ArrayList;
import java.util.List;

public class PartAdapter extends ArrayAdapter<WorkOutPartBase> {

    static final int VIEW_TYPE_WORK_OUT = 0;
    static final int VIEW_TYPE_PAUSE = 1;
    static final int VIEW_TYPE_COUNT = 2;

    public PartAdapter(Context context, ArrayList<WorkOutPartBase> parts) {
        super(context, 0, parts);
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        WorkOutPartBase parts = getItem(position);

        if (parts instanceof WorkOutPart) {
            return VIEW_TYPE_WORK_OUT;
        } else {
            return VIEW_TYPE_PAUSE;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        WorkOutPartBase parts = getItem(position);

        if (convertView == null) {
            int layoutId = 0;
            if (getItemViewType(position) == VIEW_TYPE_WORK_OUT) {
                layoutId = R.layout.list_row_workout;
            } else {
                layoutId = R.layout.list_row_pause;
            }
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }

        TextView partName = convertView.findViewById(R.id.work_out_name);
        partName.setText(parts.getWorkOutName());

        TextView partTime = convertView.findViewById(R.id.work_out_time);
        partTime.setText(String.valueOf(parts.getWorkOutTime()));

        return convertView;
    }
}
