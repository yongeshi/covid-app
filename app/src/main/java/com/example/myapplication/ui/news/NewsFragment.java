package com.example.myapplication.ui.news;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.ui.news.api.ApiClient;
import com.example.myapplication.ui.news.api.ApiInterface;
import com.example.myapplication.ui.news.models.Article;
import com.example.myapplication.ui.news.models.News;
import com.example.myapplication.ui.news.models.Result;
import com.google.gson.JsonArray;

import org.apache.commons.text.WordUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener {

    public static final String API_KEY = "4e6c5ec9-0c25-42f5-9738-5134600dd6b5";
    private RecyclerView recyclerView;
    private Article articles;
    private List<Result> results = new ArrayList<>();
    private Adapter adapter;
    private TextView topHeadline;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout errorLayout;
    private ImageView errorImage, weatherIcon;
    private TextView errorTitle, errorMessage, weatherTemperature, weatherDescription, weatherCity;
    private Button btnRetry;
    MainActivity mainActivity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_news, container, false);
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        getWeather();

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mainActivity = (MainActivity) context;
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstance) {

        super.onActivityCreated(savedInstance);

        swipeRefreshLayout = getActivity().findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.loadingScroller);

        topHeadline = getActivity().findViewById(R.id.topheadlines);
        recyclerView = getActivity().findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        onLoadingSwipeRefresh();

        errorLayout = getActivity().findViewById(R.id.errorLayout);
        errorImage = getActivity().findViewById(R.id.errorImage);
        errorTitle = getActivity().findViewById(R.id.errorTitle);
        errorMessage = getActivity().findViewById(R.id.errorMessage);
        btnRetry = getActivity().findViewById(R.id.btnRetry);

        weatherIcon = getActivity().findViewById(R.id.weather_image);
        weatherTemperature = getActivity().findViewById(R.id.temperature);
        weatherDescription = getActivity().findViewById(R.id.weather_description);
        weatherCity = getActivity().findViewById(R.id.weather_city);
    }

    @Override
    public void onRefresh() {
        LoadJson(false);
    }

    public void LoadJson(boolean retry) {
        String location = mainActivity.getCurrCity();

        errorLayout.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(true);

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        if (location == null || retry) {
            location = "los angeles";
        }

        String[] tempKeywords = {location, "covid"};
        String concept = "https://en.wikipedia.org/wiki/Coronavirus";
        String country = "https://en.wikipedia.org/wiki/United_States";

        Call<News> call;
        call = apiInterface.getLocalSearch(
                tempKeywords,
                "title",
                "eng",
                "articles",
                country,
                concept,
                "date",
                "20",
                "-1",
                "skipDuplicates",
                API_KEY
        );

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && Objects.requireNonNull(response.body()).getArticles().getResults().size() != 0) {
                    if (!results.isEmpty()) {
                        results.clear();
                    }

                    articles = response.body().getArticles();
                    adapter = new Adapter(articles, getActivity());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    initListener();

                    topHeadline.setVisibility(View.VISIBLE);
                } else {
                    topHeadline.setVisibility(View.INVISIBLE);

                    showErrorMessage(
                            R.drawable.no_result,
                            "No results found within your area...",
                            "Press the button to see LA news instead!");
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<News> call, Throwable throwable) {
                topHeadline.setVisibility(View.INVISIBLE);
                swipeRefreshLayout.setRefreshing(false);

                showErrorMessage(
                        R.drawable.oops,
                        "Oops..",
                        "Network failure, Please Try Again\n"+
                                throwable.toString());
            }
        });
    }

    //Call NewsDetailActivity when item is selected
    private void initListener() {
        adapter.setOnItemClickListener((Adapter.OnItemClickListener) (view, position) -> {
            ImageView imageView = view.findViewById(R.id.img);
            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);

            Result result = articles.getResults().get(position);
            intent.putExtra("url", result.getUrl());
            intent.putExtra("title", result.getTitle());
            intent.putExtra("img", result.getImage());
            intent.putExtra("date", result.getPublishedAt());
            intent.putExtra("source", result.getSource().getTitle());
            intent.putExtra("author", result.getAuthor());

            Pair<View, String> pair = Pair.create((View) imageView,
                    ViewCompat.getTransitionName(imageView));
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(getActivity(), pair);

            startActivity(intent, optionsCompat.toBundle());
        });
    }

    private void onLoadingSwipeRefresh() {
        swipeRefreshLayout.post(
                () -> LoadJson(false)
        );
    }

    private void showErrorMessage(int imageView, String title, String message){

        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setVisibility(View.GONE);
        }

        errorImage.setImageResource(imageView);
        errorTitle.setText(title);
        errorMessage.setText(message);

        btnRetry.setOnClickListener(v -> LoadJson(true));
    }

    private void getWeather() {
        OkHttpClient client = new OkHttpClient();

        String url;
        String city = mainActivity.getCurrCity();
        if (city == null) { city = "Los Angeles"; }

        url = "https://api.openweathermap.org/data/2.5/weather?q="+ city +"&appid=0ecc105e1d5c38e6bed33998ea13aecd&units=imperial";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //okhttp3.Response response = client.newCall(request).execute();
        String finalCity = city;
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                System.out.println("Failed");
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                String responseData = response.body().string();
                try {
                    JSONObject json = new JSONObject(responseData);
                    JSONArray array = json.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);

                    String description = object.getString("description");
                    String icons = object.getString("icon");

                    JSONObject temp = json.getJSONObject("main");
                    Double temperature = temp.getDouble("temp");

                    description = WordUtils.capitalize(description);

                    String tempVal = Math.round(temperature) + "°C";
                    setText(weatherTemperature, tempVal);
                    setText(weatherDescription, description);
                    setText(weatherCity, finalCity);
                    setImage(weatherIcon, icons);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void setText(final TextView text, final String value) {
        getActivity().runOnUiThread(() -> text.setText(value));
    }

    private void setImage(final ImageView imageView, final String value) {
        getActivity().runOnUiThread(() -> {
            switch (value) {
                case "01d":
                case "01n":
                    imageView.setImageDrawable(
                            ResourcesCompat.getDrawable(getResources(), R.drawable.d01d_icon, null));
                    break;
                case "02d":
                case "02n":
                    imageView.setImageDrawable(
                            ResourcesCompat.getDrawable(getResources(), R.drawable.d02d_icon, null));
                    break;
                case "03d":
                case "03n":
                    imageView.setImageDrawable(
                            ResourcesCompat.getDrawable(getResources(), R.drawable.d03d_icon, null));
                    break;
                case "04d":
                case "04n":
                    imageView.setImageDrawable(
                            ResourcesCompat.getDrawable(getResources(), R.drawable.d04d_icon, null));
                    break;
                case "09d":
                case "09n":
                    imageView.setImageDrawable(
                            ResourcesCompat.getDrawable(getResources(), R.drawable.d09d_icon, null));
                    break;
                case "10d":
                case "10n":
                    imageView.setImageDrawable(
                            ResourcesCompat.getDrawable(getResources(), R.drawable.d10d_icon, null));
                    break;
                case "11d":
                case "11n":
                    imageView.setImageDrawable(
                            ResourcesCompat.getDrawable(getResources(), R.drawable.d11d_icon, null));
                    break;
                case "13d":
                case "13n":
                    imageView.setImageDrawable(
                            ResourcesCompat.getDrawable(getResources(), R.drawable.d13d_icon, null));
                    break;
                default:
                    imageView.setImageDrawable(
                            ResourcesCompat.getDrawable(getResources(), R.drawable.weather_icon, null));
            }
        });
    }
}