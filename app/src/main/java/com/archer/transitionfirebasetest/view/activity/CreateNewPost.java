package com.archer.transitionfirebasetest.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.archer.transitionfirebasetest.MyApplication;
import com.archer.transitionfirebasetest.R;
import com.archer.transitionfirebasetest.common.BaseActivity;
import com.archer.transitionfirebasetest.common.BasePresenter;
import com.archer.transitionfirebasetest.domain.Community;
import com.archer.transitionfirebasetest.domain.Post;
import com.archer.transitionfirebasetest.util.Helpers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class CreateNewPost extends BaseActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 100;
    private static final int IMG_RESULT = 200;
    @BindView(R.id.content)
    EditText content;

    @BindView(R.id.selected_image)
    ImageView imageView;

    @BindView(R.id.spinner)
    MaterialSpinner spinner;

    @BindView(R.id.button)
    Button button;

    private String mCurrentPhotoPath;
    private String mCurrentAbsolutePhotoPath;

    private MyApplication app;
    private List<String> userCommunities;
    private String selectedCommunity;
    private DatabaseReference postReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (MyApplication) getApplicationContext();
        postReference = app.getPostsReference();

        userCommunities = getUserCommunitiesNames(getDummieCommunities());
        spinner.setItems(userCommunities);

        selectedCommunity = spinner.getItems().get(0).toString();

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                selectedCommunity = item.toString();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Picasso.with(CreateNewPost.this).load(mCurrentPhotoPath).into(imageView);
                galleryAddPic();
            }

            if (requestCode == IMG_RESULT) {

                Uri selectedImageURI = data.getData();

                Picasso.with(CreateNewPost.this).load(selectedImageURI).into(imageView);
            }
        }

    }

    public void createNewPost (View view) {
        String body = content.getText().toString();
        String targetPublic = selectedCommunity;
        SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        Post newPost = new Post();
        newPost.setContent(body);
        newPost.setUsername(username);
        newPost.setCommunity(targetPublic);
        newPost.setUid(getUID());

        canEditInformation(false);

        postReference.push().setValue(newPost)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Some goes wrong. Try again", Toast.LENGTH_SHORT).show();
                    } else {
                        Helpers.navigate(CreateNewPost.this, MainActivity.class);
                    }
                }
            });
    }

    private void canEditInformation (boolean edit) {
        content.setEnabled(edit);
        button.setEnabled(edit);
    }

    private List<String> getUserCommunitiesNames (List<Community> communities) {
        List<String> communityNames = new ArrayList<>();

        for (Community community : communities) {
            String name = community.getName();
            communityNames.add(name);
        }

        return communityNames;
    }

    private List<Community> getDummieCommunities () {

        List<Community> communities = new ArrayList<>();

        Community community1 = new Community();
        community1.setName("Cosplay Plus");

        Community community2 = new Community();
        community2.setName("Anime lovers");

        Community community3 = new Community();
        community3.setName("Publica");

        communities.add(community1);
        communities.add(community2);
        communities.add(community3);

        return communities;
    }

    /**
     * Methods for take a photo
     */

    private File createImageFile () throws IOException {
        String timeStamps    = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamps + "_";
        File   storageDir    = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        mCurrentAbsolutePhotoPath = image.getAbsolutePath();

        return image;
    }


    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }



    /**
     * OnClick methods
     */

    public void takePhoto (View view) {
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePhotoIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;

            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(
                        CreateNewPost.this,
                        "com.archer.transitionfirebasetest",
                        photoFile);

                takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePhotoIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    public void selectPhotoFromGallery (View view) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(intent, IMG_RESULT);
    }




    @Override
    public int getLayout() {
        return R.layout.activity_create_new_post;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}





























