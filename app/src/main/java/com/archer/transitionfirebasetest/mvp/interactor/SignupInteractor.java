package com.archer.transitionfirebasetest.mvp.interactor;

import android.support.annotation.NonNull;

import com.archer.transitionfirebasetest.io.callback.SignupServerCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * Created by archer on 14-01-17.
 */

public class SignupInteractor {
    private SignupServerCallback presenter;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public SignupInteractor(SignupServerCallback presenter) {
        this.presenter = presenter;
    }

    public void createUserWithEmailAndPassword (final String username, String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        presenter.onFailedSignup();
                    } else {
                        FirebaseUser user = task.getResult().getUser();
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(username)
                                .build();

                        user.updateProfile(profileUpdates);
                        presenter.onSuccessSignup();
                    }
                }
            });
    }
}




























