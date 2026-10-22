package com.example.sem4_tema;

import android.os.Parcel;
import android.os.Parcelable;

public class TikTok implements Parcelable {
    private String creatorName;
    private String category;
    private boolean isMonetized;
    private String targetAudience;
    private float rating;
    private boolean isPublic;

    public TikTok(String creatorName, String category, boolean isMonetized, String targetAudience, float rating, boolean isPublic) {
        this.creatorName = creatorName;
        this.category = category;
        this.isMonetized = isMonetized;
        this.targetAudience = targetAudience;
        this.rating = rating;
        this.isPublic = isPublic;
    }

    protected TikTok(Parcel in) {
        creatorName = in.readString();
        category = in.readString();
        isMonetized = in.readByte() != 0;
        targetAudience = in.readString();
        rating = in.readFloat();
        isPublic = in.readByte() != 0;
    }

    public static final Creator<TikTok> CREATOR = new Creator<TikTok>() {
        @Override
        public TikTok createFromParcel(Parcel in) {
            return new TikTok(in);
        }

        @Override
        public TikTok[] newArray(int size) {
            return new TikTok[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(creatorName);
        dest.writeString(category);
        dest.writeByte((byte) (isMonetized ? 1 : 0));
        dest.writeString(targetAudience);
        dest.writeFloat(rating);
        dest.writeByte((byte) (isPublic ? 1 : 0));
    }

    @Override
    public String toString() {
        return "TikTok Video:\n" +
                "• Creator: " + creatorName + "\n" +
                "• Category: " + category + "\n" +
                "• Monetized: " + (isMonetized ? "Yes" : "No") + "\n" +
                "• Audience: " + targetAudience + "\n" +
                "• Rating: " + rating + "★\n" +
                "• Public: " + (isPublic ? "Yes" : "No");
    }
}