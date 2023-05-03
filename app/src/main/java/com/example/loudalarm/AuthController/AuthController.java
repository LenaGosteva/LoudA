package com.example.loudalarm.AuthController;

import android.net.Uri;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class AuthController {
    private final FirebaseAuth auth;

    public AuthController() {
        this.auth = FirebaseAuth.getInstance();

    }

    public boolean isAuth() {
        return auth.getCurrentUser() != null;
    }

    public FirebaseUser getUser() {
        return auth.getCurrentUser();
    }

    public void registerUser(String email, String password, OnCompleteListener<AuthResult> listener) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(listener);
        FirebaseUser user = auth.getCurrentUser();

    }

    public void updateIcon(Uri uri) {
        FirebaseUser user = auth.getCurrentUser();
        user.updateProfile(new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri).build()
        );
    }

    public void updateName(String name) {
        FirebaseUser user = auth.getCurrentUser();
        user.updateProfile(new UserProfileChangeRequest.Builder()
                .setDisplayName(name).build()
        );
    }

    public void enterUser(String email, String password, OnCompleteListener<AuthResult> listener) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(listener);
    }

    public void singOut() {
        auth.signOut();
    }

}
