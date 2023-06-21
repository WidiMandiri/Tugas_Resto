package com.widi.tugasresto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

// NIM : 10120064
// NAMA : Widi Malikul Mulky
// KELAS : IF-2
public class IntroViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<ScreenItem> mListScreen;

    public IntroViewPagerAdapter(Context mContext, List<ScreenItem> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View layoutScreen = inflater.inflate(R.layout.layout_screen, container, false);

        IntroViewHolder viewHolder = new IntroViewHolder(layoutScreen);
        ScreenItem screenItem = mListScreen.get(position);

        viewHolder.title.setText(screenItem.getTitle());
        viewHolder.description.setText(screenItem.getDescription());
        viewHolder.imgSlide.setImageResource(screenItem.getScreenImg());

        container.addView(layoutScreen);

        return layoutScreen;
    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    static class IntroViewHolder {
        ImageView imgSlide;
        TextView title;
        TextView description;

        IntroViewHolder(View view) {
            imgSlide = view.findViewById(R.id.intro_img);
            title = view.findViewById(R.id.intro_title);
            description = view.findViewById(R.id.intro_description);
        }
    }
}
