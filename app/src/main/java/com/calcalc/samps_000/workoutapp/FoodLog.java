package com.calcalc.samps_000.workoutapp;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FoodLog extends ListActivity{

    ArrayList<Set<String>> foodItems = new ArrayList<Set<String>>();
    List<HashMap<String, String>> cur_list = new ArrayList<HashMap<String, String>>();

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        SharedPreferences prefs = this.getSharedPreferences("logData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        //Might be error here
        int i = 1;
        while(null != prefs.getString(String.valueOf(i) + " title", null)){
            HashMap<String, String>  item = new HashMap<String, String>();
            String title = prefs.getString(String.valueOf(i) + " title", null);
            String cal = prefs.getString(String.valueOf(i) + " cal", null);
            String no = prefs.getString(String.valueOf(i) + " no", null);
            item.put("title", title);
            item.put("cal", cal);
            item.put("no", no);
            Log.d("saves", "T: " + title);
            Log.d("saves", "C: " + cal);
            Log.d("saves", "N: " + no);
            cur_list.add(item);
            i += 1;

        }

        /*for(int a = 0; a < foodItems.size(); a++){
            Iterator iter = foodItems.get(a).iterator();
            HashMap<String, String>  item = new HashMap<String, String>();
            String title = String.valueOf(iter.next());
            String cal = String.valueOf(iter.next());
            String no = String.valueOf(iter.next());
            item.put("title", title);
            item.put("cal", cal);
            item.put("no", no);
            Log.d("saves", "T: " + title);
            Log.d("saves", "C: " + cal);
            Log.d("saves", "N: " + no);
            cur_list.add(item);
        }
        */

        Log.d("saves", "here5");

        String[] from = new String[] {"title", "cal", "no"};
        int[] to = new int[] {R.id.logFoodTitle, R.id.logFoodCal};
        ListAdapter adapter = new SimpleAdapter(this, cur_list, R.layout.log_list_item, from, to);
        setListAdapter(adapter);
        /*setListAdapter(new ArrayAdapter<String>(this, R.layout.log_list_item, cur_array));
        ListView lv = getListView();*/
        //setAdapter();



/*        ArrayAdapter<Set<String>> listAdapter
                = new ArrayAdapter<Set<String>>(this, R.layout.log_list_item, foodItems);

        ListView logList = (ListView) findViewById(android.R.id.list);
        logList.setAdapter(listAdapter);
        */

    }

    /*public class FoodAdapter extends ArrayAdapter<Set<String>>{

        private class ViewHolder{
            TextView title;
            TextView cal;
        }

        public FoodAdapter(Context context, ArrayList<Set<String>> foods) {
            super(context, R.layout.log_list_item);
            Log.d("saves", "here4");
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.d("saves", "here2");
            Set<String> item = getItem(position);

            ViewHolder viewHolder;
            if(convertView == null) {
                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.log_list_item, parent, false);
                viewHolder.title = (TextView) convertView.findViewById(R.id.logFoodTitle);
                viewHolder.cal = (TextView) convertView.findViewById(R.id.logFoodCal);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Log.d("saves", "here3");
            Iterator<String> it = item.iterator();
            String title = it.next().toString();
            String cal = it.next().toString();

            Log.d("savesA", title);
            Log.d("savesA", cal);

            viewHolder.title.setText(title);
            viewHolder.cal.setText(cal);

            return convertView;
        }

    }
    */

    /*public void setAdapter() {
        Log.d("saves", "here");
        FoodAdapter adapter = new FoodAdapter(this, foodItems);
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);
    }
*/


}
