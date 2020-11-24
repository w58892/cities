package pl.edu.wsiz.w58892w58891;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter{

    Context mContext;
    LayoutInflater inflater;
    List<City> modellist;
    ArrayList<City> arrayList;

    public ListViewAdapter(Context context, List<City> modellist) {
        mContext = context;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<City>();
        this.arrayList.addAll(modellist);
    }

    public class ViewHolder{
        TextView cityName, criminal, beating, thefts, road;
    }

    @Override
    public int getCount() {
        return modellist.size();
    }

    @Override
    public Object getItem(int i) {
        return modellist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.row, null);

            holder.cityName = view.findViewById(R.id.cityName);
            holder.criminal = view.findViewById(R.id.criminal);
            holder.beating = view.findViewById(R.id.beating);
            holder.thefts = view.findViewById(R.id.thefts);
            holder.road = view.findViewById(R.id.road);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder)view.getTag();
        }
        holder.cityName.setText(modellist.get(position).getCityName());
        holder.criminal.setText(modellist.get(position).getCriminal());
        holder.beating.setText(modellist.get(position).getBeating());
        holder.thefts.setText(modellist.get(position).getThefts());
        holder.road.setText(modellist.get(position).getRoad());

        return view;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        modellist.clear();
        if (charText.length()==0){
            modellist.addAll(arrayList);
        }
        else {
            for (City city : arrayList){
                if (city.getCityName().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    modellist.add(city);
                }
            }
        }
        notifyDataSetChanged();
    }

}