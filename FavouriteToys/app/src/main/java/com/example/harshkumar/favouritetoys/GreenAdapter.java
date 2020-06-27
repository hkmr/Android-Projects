package com.example.harshkumar.favouritetoys;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {

    private static final String TAG = GreenAdapter.class.getSimpleName();
    private int mNumberItems;

    private static int viewHolderCount;

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }

    public GreenAdapter(int numberOfItems){
        mNumberItems = numberOfItems;
        viewHolderCount = 0;
    }

    @NonNull
    @Override
    public GreenAdapter.NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_list_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem,viewGroup,shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        viewHolder.viewHolderIndex.setText("ViewHolder index: "+viewHolderCount);

        int backgroundColorForViewHolder = ColorUtils
                .getViewHolderBackgroundColorFromInstance(context, viewHolderCount);
        viewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder);

        viewHolderCount++;
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: "
                + viewHolderCount);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder numberViewHolder, int position) {
        Log.d(TAG,"#"+position);
        numberViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    class NumberViewHolder extends RecyclerView.ViewHolder {

        TextView listItemNumberView;
        TextView viewHolderIndex;

        public NumberViewHolder(View itemView){

            super(itemView);
            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
            viewHolderIndex = (TextView) itemView.findViewById(R.id.tv_view_holder_instance);

        }

        void bind(int listIndex){

            listItemNumberView.setText(String.valueOf(listIndex));
        }

    }
}
