package com.archer.transitionfirebasetest.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.archer.transitionfirebasetest.Manifest;
import com.archer.transitionfirebasetest.MyApplication;
import com.archer.transitionfirebasetest.R;
import com.archer.transitionfirebasetest.common.BaseActivity;
import com.archer.transitionfirebasetest.common.BasePresenter;
import com.archer.transitionfirebasetest.domain.Community;
import com.archer.transitionfirebasetest.domain.Post;
import com.archer.transitionfirebasetest.util.Constants;
import com.archer.transitionfirebasetest.util.Helpers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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

    private Uri mCurrentImageUri;

    private MyApplication app;
    private List<String> userCommunities;
    private String selectedCommunity;

    private DatabaseReference postReference;
    private StorageReference storageReference;

    private String mCropImageUri;
    private String mAbsoluteCropImageUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (MyApplication) getApplicationContext();
        postReference = app.getPostsReference();
        storageReference = app.getStorageReference();

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
                Toast.makeText(this, mCurrentPhotoPath, Toast.LENGTH_SHORT).show();
                Log.e("TARGET", mCurrentAbsolutePhotoPath);

                Picasso.with(CreateNewPost.this).load(mCurrentPhotoPath).into(imageView);
                galleryAddPic();
            }

            if (requestCode == IMG_RESULT) {

                Uri selectedImageURI = data.getData();
                mCurrentImageUri = selectedImageURI;

                Picasso.with(CreateNewPost.this).load(selectedImageURI).into(imageView);

            }

//            if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//                Uri imageUri = CropImage.getPickImageResultUri(this, data);
//
//
//                mCropImageUri = "file:" + imageUri;
//                startCropImageActivity(imageUri);
//
//            }
//
//            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//                CropImage.ActivityResult result = CropImage.getActivityResult(data);
//                if (resultCode == RESULT_OK) {
//                    Picasso.with(CreateNewPost.this).load(result.getUri()).into(imageView);
//                    Toast.makeText(this, "Cropping successful, Sample: " + result.getSampleSize(), Toast.LENGTH_LONG).show();
//                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                    Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
//                }
//            }

        }

    }

    public void createNewPost (String imageUrl) {
        String body = content.getText().toString();
        String targetPublic = selectedCommunity;
        SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        Post newPost = new Post();
        newPost.setContent(body);
        newPost.setUsername(username);
        newPost.setCommunity(targetPublic);
        newPost.setUid(getUID());
        newPost.setUrlImage(imageUrl);

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


    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);
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
//        CropImage.startPickImageActivity(this);
    }

    public void  uploadPostWithImageGallery (View view) {
        StorageReference imagesReferences = storageReference.child(Constants.FIREBASE_STORAGE_IMAGES + mCurrentImageUri.getLastPathSegment());

        UploadTask uploadTask = imagesReferences.putFile(mCurrentImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateNewPost.this, "Error subiendo la imagen", Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String imageUrl = taskSnapshot.getDownloadUrl().toString();
                createNewPost(imageUrl);
            }
        });
    }

    public void uploadPost (View view) {
        uploadFile();
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


    private void galleryAddPic () {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }


    private void uploadFile () {
        File file = new File(mCurrentAbsolutePhotoPath);
        Uri contentUri = Uri.fromFile(file);

        StorageReference imagesReferences = storageReference.child(Constants.FIREBASE_STORAGE_IMAGES + contentUri.getLastPathSegment());

        UploadTask uploadTask = imagesReferences.putFile(contentUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateNewPost.this, "Error subiendo la imagen", Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String imageUrl = taskSnapshot.getDownloadUrl().toString();
                createNewPost(imageUrl);
            }
        });
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





























