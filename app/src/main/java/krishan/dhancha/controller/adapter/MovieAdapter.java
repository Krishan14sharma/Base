package krishan.dhancha.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import krishan.dhancha.R;
import krishan.dhancha.helper.CircleTransform;
import krishan.dhancha.model.Movie;

/**
 * A custom array adapter.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {

    /**
     * Current context
     */
    protected Context mContext;

    private LayoutInflater mInflater;

    // -------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------

    public MovieAdapter(Context context, List<Movie> objects) {
        super(context, 0, objects);
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    // -------------------------------------------------------------
    //  getView()
    // -------------------------------------------------------------

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // A ViewHolder keeps references to children views to avoid unneccessary calls
        // to findViewById() on each row.
        ViewHolder holder;

        // When convertView is not null, we can reuse it directly, there is no need
        // to reinflate it. We only inflate a new View when the convertView supplied
        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.list_item_movielist, parent, false);

            // Creates a ViewHolder and store references to the two children views
            // we want to bind data to.
            holder = new ViewHolder();

            // TODO store references to your views
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            holder.imageView=(ImageView)convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);

        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) convertView.getTag();
        }

        Movie item = getItem(position);


        if (item != null){
            // TODO Bind your data efficiently with the holder.
            holder.textView.setText(item.getMovieTitle());
            Picasso.with(getContext()).load(item.getMovieCover()).transform(new CircleTransform(item.getImdbCode())).into(holder.imageView);
        }


        return convertView;
    }

    // -------------------------------------------------------------
    //  ViewHolder
    // -------------------------------------------------------------

    private static class ViewHolder {

        // TODO define members for each view in the item layout
        //public TextView text;
        TextView textView;
        ImageView imageView;
    }


    // -------------------------------------------------------------
    //  Getters and Setters
    // -------------------------------------------------------------


    public Context getContext() {
        return mContext;
    }

    private void changeData()
    {

    }
}
