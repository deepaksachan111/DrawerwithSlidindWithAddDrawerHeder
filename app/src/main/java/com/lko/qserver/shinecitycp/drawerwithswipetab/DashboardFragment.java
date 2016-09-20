package com.lko.qserver.shinecitycp.drawerwithswipetab;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lko.qserver.shinecitycp.ModalData.DashboardData;
import com.lko.qserver.shinecitycp.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ratan on 7/29/2015.
 */
public class DashboardFragment extends Fragment {
    private ListView mLinearListView;

    ArrayList<DashboardData> mArrayListData = new ArrayList<DashboardData>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_layout, null);

        mLinearListView = (ListView)view. findViewById(R.id.gallarty);
        String contact_url = "http://rsmile.quaeretech.com/RealtorSmile.svc/GetContactRecords/rahulmiddha.eng@gmail.com" ;
        new ContactAsyncTask().execute(contact_url);



     /*   ArrayList<Item> mArrayListData = new ArrayList<Item>();
        mArrayListData.add(new Item("Manish", "Srivastava"));
        mArrayListData.add(new Item("Sachin", "Tendulker"));
        mArrayListData.add(new Item("Ricky", "Pointing"));
        mArrayListData.add(new Item("Manish", "Srivastava"));
        mArrayListData.add(new Item("Sachin", "Tendulker"));
        mArrayListData.add(new Item("Ricky", "Pointing"));
        mArrayListData.add(new Item("Manish", "Srivastava"));
        mArrayListData.add(new Item("Sachin", "Tendulker"));
        mArrayListData.add(new Item("Ricky", "Pointing"));
        mArrayListData.add(new Item("Manish", "Srivastava"));
        mArrayListData.add(new Item("Sachin", "Tendulker"));
        mArrayListData.add(new Item("Ricky", "Pointing"));
        mArrayListData.add(new Item("Manish", "Srivastava"));
        mArrayListData.add(new Item("Sachin", "Tendulker"));
        mArrayListData.add(new Item("Ricky", "Pointing"));*/

        /***
         * adding item into listview
         */

       /* for (int i = 0; i < mArrayListData.size(); i++) {
            *//**
             * inflate items/ add items in linear layout instead of listview
             *//*
            // inflaters = null;
            LayoutInflater inflaters = (LayoutInflater) getActivity()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View mLinearView = inflaters.inflate(R.layout.row, null);

            *//**
             * getting id of row.xml
             *//*


            TextView mFirstName = (TextView) mLinearView
                    .findViewById(R.id.textViewName);
            TextView mLastName = (TextView) mLinearView
                    .findViewById(R.id.textViewLastName);

            *//**
             * set item into row
             *//*
            String fName = mArrayListData.get(i).getContactName();
            String lName = mArrayListData.get(i).getContact_mobile();

            String s = fName;
            String l = lName;

            mFirstName.setText(fName);
            mLastName.setText(lName);

            mLinearListView.addView(mLinearView);

        }*/

      return view;
    }


    public class ContactAsyncTask extends AsyncTask<String, Void, String> {
          ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading Details ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String response = null;
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httppost = new HttpGet(url);

            try {
                HttpResponse httpResponse = httpClient.execute(httppost);
                HttpEntity httpEntity = httpResponse.getEntity();
                response = EntityUtils.toString(httpEntity);
                Log.v("Contact  Response ", response);
                // String response = jsonResponse;


            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(" Exception is caught here ......." + e.toString());
            }
            return response;
        }


        @Override
        protected void onPostExecute(String ss) {

              pDialog.dismiss();
            if (ss == null) {
                Toast.makeText(getActivity(), "Internet not available ", Toast.LENGTH_LONG).show();
            } else


                try {

                    JSONArray jsonArray = new JSONArray(ss);
                    int noOfObjects = jsonArray.length();
                    Log.v("Number of json Obj " + noOfObjects, "   pd Objects.....");
                    // pd.dismiss();
                    for (int j = 0; j < jsonArray.length(); j++) {

                        JSONObject jObj = jsonArray.getJSONObject(j);
                        //   Log.v("No of times " + j, "shhhhhhhhh");
                        // int k = j + 1;
                        // detailNo = ""+k;
                        String contactName = jObj.getString("FirstName");
                        String contact_id = jObj.getString("PK_ContactID");
                        String contact_type = jObj.getString("ContactType");
                        String contact_mobile = jObj.getString("Mobile");
                        String contact_email = jObj.getString("Email");
                        String contact_response_code = jObj.getString("ResponseCode");

                        mArrayListData.add(new DashboardData(contactName,contact_id,contact_type,contact_mobile,contact_email,contact_response_code));


                        MyAdapter myAdapter = new MyAdapter(getActivity(),R.layout.row,mArrayListData);
                        mLinearListView.setAdapter(myAdapter);

                    }
                  /*  for (int i = 0; i < mArrayListData.size(); i++) {
                        *//**
                         * inflate items/ add items in linear layout instead of listview
                         *//*
                        // inflaters = null;
                        LayoutInflater inflaters = (LayoutInflater) getActivity()
                                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View mLinearView = inflaters.inflate(R.layout.row, null);

                        *//**
                         * getting id of row.xml
                         *//*


                        TextView mFirstName = (TextView) mLinearView
                                .findViewById(R.id.textViewName);
                        TextView mLastName = (TextView) mLinearView
                                .findViewById(R.id.textViewLastName);

                        *//**
                         * set item into row
                         *//*
                        String fName = latest.get(i);{

                        }
                        String lName = mArrayListData.get(i).getContact_mobile();

                        String s = fName;
                        String l = lName;

                        mFirstName.setText(fName);
                        mLastName.setText(lName);

                        mLinearListView.addView(mLinearView);
*/
                   // }


                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println(" Exception is caught here ......." + e.toString());
                }






        }
    }


    public class MyAdapter extends ArrayAdapter {

        Activity aa;

        ArrayList<DashboardData> arrayList;

        MyAdapter(Activity c,int r, ArrayList<DashboardData> dataArrayList){
            super(c,r,dataArrayList);
            arrayList = dataArrayList;
            aa = c;
        }



        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return arrayList;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub


            if(convertView == null) {
               convertView = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.row, null);

            }
            TextView listTextView = (TextView) convertView.findViewById(R.id.textView1);
            DashboardData dashboardData =  arrayList.get(position);
            listTextView.setText(dashboardData.getContactName());

            return convertView;
        }


    }



}










    /*public class Adapter extends ArrayAdapter {
        Context context;
        LayoutInflater inflater;
        private String[] mSimpleListValues = new String[]{"Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2"};
        int count = 0;

        public Adapter(Context context,ArrayList<String> arrayList) {
            super(context,0,arrayList);
            this.context = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mSimpleListValues.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;
            View view = convertView;
            if (view == null) {
                view = inflater.inflate(R.layout.dashboard_layout_listview, null);
                holder = new Holder();
                holder.tvSSID = (TextView) view.findViewById(R.id.textView1);


                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }
            holder.tvSSID.setText(mSimpleListValues[position]);
            view.setLayoutParams(new LinearLayout.LayoutParams(135, 60));
            return view;
        }

        class Holder {
            TextView tvSSID;
        }
    }*/


