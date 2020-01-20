package com.app.androidx_project;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

import com.app.androidx_project.model.Chapter;
import com.app.androidx_project.model.Questions;
import com.app.androidx_project.model.Topics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = findViewById(R.id.tv_jsoondata1);
        t2 = findViewById(R.id.tv_jsoondata2);
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        boolean flag = ni!=null && ni.isConnectedOrConnecting();
        if(flag)
            test();
            //    getDataUsingObserver();
     //   getData();
    }

public void getData(){
        ApiInterface api  = null;
        api = RestService.getRestService().create(ApiInterface.class);
         Call<Posts> callSync = api.getPosts(1);

         callSync.enqueue(new Callback<Posts>() {
             @Override
             public void onResponse(Call<Posts> call, Response<Posts> response) {
                 t1.setText(response.message());
             }

             @Override
             public void onFailure(Call<Posts> call, Throwable t) {
                 t1.setText(t.getMessage());
             }
         });
}

   public void getDataUsingObserver(){
       ApiInterface api  = null;
       api = RestService.getRetrofit2().create(ApiInterface.class);

       api.getCommentById().subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<Comments>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(Comments comments) {
                       System.out.print("----------onNext()"+comments.toString());
                       t1.setText(comments.toString());
                   }

                   @Override
                   public void onError(Throwable e) {
                       System.out.print("----------onError()");
                       t1.setText(e.getMessage());
                       e.printStackTrace();
                   }

                   @Override
                   public void onComplete() {
                    System.out.print("----------onComplete()");
                   }
               });
   }

   public  void test(){
       ApiInterface api  = null;
       api = RestService.getRestService().create(ApiInterface.class);
       Call<List<Comments>> callSync = api.getComments();

       callSync.enqueue(new Callback<List<Comments>>() {
           @Override
           public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
               System.out.print("----------onNext()"+response.toString());
               t1.setText(response.message());
           }

           @Override
           public void onFailure(Call<List<Comments>> call, Throwable t) {
               t1.setText(t.getMessage());
           }
       });


   }














    public String loadJSONFromAsset() {
        /* try {
            String jsonLocation = AssetJSONFile("test.json", getApplicationContext());
          //  JSONObject formArray = (new JSONObject()).getJSONObject("id");
          //  String formule = formArray.getString("qid");
          //  String url = formArray.getString("question");
          //  t1.setText( "/n/n"+loadJSONFromAsset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        t2.setText(getJsonData().toString() );*/
        String json = null;
        Questions questions=null;

        JSONObject objQuestions;
        try {
            InputStream is = getAssets().open("test.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONArray jasonQuestionArray = new JSONArray(json);
          //  questionsArray = new Questions[jasonQuestionArray.length()];
            for(int j = 0; j<jasonQuestionArray.length(); j++) {
                objQuestions = jasonQuestionArray.getJSONObject(j);
                questions = new Questions();
                questions.setId(objQuestions.getString("id"));
                questions.setQid(objQuestions.getString("qid"));
                questions.setQuestion(objQuestions.getString("question"));
                questions.setAnswer(objQuestions.getString("answer"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return json + questions.toString();
    }
    public Chapter getJsonData(){
        Chapter chapter = new Chapter();
        String jsonString;
        JSONObject obj,objTopic,objQuestions;
        Topics topics;
        Topics[] topicsArray;
        Questions questions;
        Questions[] questionsArray;

        try{
            InputStream is = getAssets().open("jsinterview.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");

            //      String jsonLocation = AssetJSONFile("formules.json", getApplicationContext());


            JSONArray jasonChapterArray = new JSONArray(jsonString);
            for(int r = 0; r<jasonChapterArray.length(); r++) {
                obj =jasonChapterArray.getJSONObject(r);
                chapter.setId(obj.getInt("id"));
                chapter.setType(obj.getString("type"));
                chapter.setName(obj.getString("name"));
                chapter.setQuestionimage(obj.getString("questionimage"));
                chapter.setAnswerimage(obj.getString("answerimage"));

                JSONArray jasonTopicArray = obj.getJSONArray("data");
               // JSONArray jasonTopicArray = new JSONArray(obj.get("data").toString());
                topicsArray = new Topics[jasonTopicArray.length()];
                for (int i = 0; i < jasonTopicArray.length(); i++) {
                    objTopic = jasonTopicArray.getJSONObject(i);
                    topics = new Topics();
                    topics.setId(objTopic.getString("id"));
                    topics.setType(objTopic.getString("type"));
                    topics.setName(objTopic.getString("name"));

                    JSONArray jasonQuestionArray = objTopic.getJSONArray("data");
                   // JSONArray jasonQuestionArray = new JSONArray(obj.get("data").toString());
                    questionsArray = new Questions[jasonQuestionArray.length()];
                    for (int j = 0; j < jasonQuestionArray.length(); j++) {
                        objQuestions = jasonQuestionArray.getJSONObject(j);
                        questions = new Questions();
                        questions.setId(objQuestions.getString("id"));
                        questions.setQid(objQuestions.getString("qid"));
                        questions.setQuestion(objQuestions.getString("question"));
                        questions.setAnswer(objQuestions.getString("answer"));
                        //topics.setQuestions(questions);
                        questionsArray[j] = questions;
                    }
                    topics.setQuestions(questionsArray);
                    topicsArray[i] = topics;
                }

                chapter.setTopics(topicsArray);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return  chapter;
    }
    public static String AssetJSONFile (String filename, Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();

        return new String(formArray);
    }
}
