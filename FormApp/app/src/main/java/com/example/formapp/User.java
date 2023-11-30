package com.example.formapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    public String photo;
    public String rut;
    public String name;
    public int age;

    public User(){}

    protected User(Parcel in){
        photo = in.readString();
        rut = in.readString();
        name = in.readString();
        age = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(photo);
        parcel.writeString(rut);
        parcel.writeString(name);
        parcel.writeInt(age);
    }
}
