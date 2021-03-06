package com.g2k.calendy.utils;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.g2k.calendy.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Adapter class for recycleview in Calendars fragment
 *
 * @author Yiğit Yalın, Mehmet Kağan İlbak
 * @version 2021/05/04
 */
public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    private ArrayList<Calendar> calendarData;
    private Drawable[] backgrounds;
    private CalendarClickListener calendarClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView calendarNameTextField;

        public ViewHolder(View view) {
            super(view);

            calendarNameTextField = view.findViewById(R.id.calendar_name);
        }

        public TextView getCalendarNameTextField() {
            return calendarNameTextField;
        }

        @Override
        public void onClick(View view) {
            if (calendarClickListener != null) {
                calendarClickListener.onCalendarClick(view, getAdapterPosition());
            }
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView.
     */
    public CalendarAdapter(ArrayList<Calendar> dataSet, Drawable[] drawables) {
        calendarData = dataSet;
        backgrounds = drawables;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.calendars_list_element, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getCalendarNameTextField().setText(calendarData.get(position).getName());
        viewHolder.getCalendarNameTextField().setBackground(pickRandomBackground());
        viewHolder.getCalendarNameTextField().setOnClickListener(viewHolder);
    }

    @Override
    public int getItemCount() {
        return calendarData.size();
    }

    /**
     * gets the data at click position
     * @param id is the click position
     * @return the data at click position
     */
    public Calendar getItem(int id) {
        return calendarData.get(id);
    }

    /**
     * sets the click listener for recycler view elements
     *
     * @param calendarClickListener is the click listener to be added
     */
    public void setCalendarClickListener(CalendarClickListener calendarClickListener) {
        this.calendarClickListener = calendarClickListener;
    }

    /**
     * parent activity implements this to set what will happen on click
     */
    public interface CalendarClickListener {
        void onCalendarClick(View view, int position);
    }

    /**
     * picks a random background for each calendar
     * @return a random background
     */
    public Drawable pickRandomBackground() {
        Random random = new Random();
        int randomIndex = random.nextInt(backgrounds.length);
        return backgrounds[randomIndex];
    }
}
