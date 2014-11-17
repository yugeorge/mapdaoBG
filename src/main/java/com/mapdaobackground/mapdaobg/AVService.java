package com.mapdaobackground.mapdaobg;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.RequestPasswordResetCallback;
import com.avos.avoscloud.SignUpCallback;

/**
 * Created by yu on 2014/11/17.
 */
public class AVService {
    public static void signUp(String username, String password, String email, SignUpCallback signUpCallback) {
        AVUser user = new AVUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.signUpInBackground(signUpCallback);
    }

    public static void logout() {
        AVUser.logOut();
    }

    public static void requestPasswordReset(String email, RequestPasswordResetCallback callback) {
        AVUser.requestPasswordResetInBackground(email, callback);
    }

}
