package com.sergiogomeza.rayar;

import android.net.Uri;

/**
 * Created by Sergio on 10/11/2015.
 */
public class contact {
    private String _name,_phone,_email;
    private Uri _imageURI;
    private int _id;

    public contact(int _id, String _name, String _phone, String _email, Uri _imageURI) {
        this._id = _id;
        this._name = _name;
        this._phone = _phone;
        this._email = _email;
        this._imageURI = _imageURI;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public Uri get_imageURI() {
        return _imageURI;
    }

    public void set_imageURI(Uri _imageURI) {
        this._imageURI = _imageURI;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
