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

public class MapListViewAdapter extends BaseAdapter{

    Context mContext;
    LayoutInflater inflater;
    List<City> modellist;
    ArrayList<City> arrayList;

    public MapListViewAdapter(Context context, List<City> modellist) {
        mContext = context;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<City>();
        this.arrayList.addAll(modellist);
    }

    public class ViewHolder{
        TextView cityName2;
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
    public View getView(final int postition, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.search_row, null);
            holder.cityName2 = view.findViewById(R.id.cityName);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder)view.getTag();

        }
        holder.cityName2.setText(modellist.get(postition).getCityName());

        return view;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        modellist.clear();
        if (charText.length()!=0){

            int i = 0;
            for (City city : arrayList){
                if(i==3)
                    break;
                if (city.getCityName().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    modellist.add(city);
                    i++;
                }
            }
        }
        notifyDataSetChanged();
    }

}