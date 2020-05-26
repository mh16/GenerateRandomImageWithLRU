//package com.hussain.assignment.model;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//
//public class DogsModel implements Parcelable {
//
//    @SerializedName("message")
//    @Expose
//    private String message;
//    @SerializedName("status")
//    @Expose
//    private String status;
//    public final static Parcelable.Creator<DogsModel> CREATOR =
//            new Creator<DogsModel>() {
//
//
//                @SuppressWarnings({
//                        "unchecked"
//                })
//                public DogsModel createFromParcel(Parcel in) {
//                    return new DogsModel(in);
//                }
//
//                public DogsModel[] newArray(int size) {
//                    return (new DogsModel[size]);
//                }
//
//            };
//
//    protected DogsModel(Parcel in) {
//        this.message = ((String) in.readValue((String.class.getClassLoader())));
//        this.status = ((String) in.readValue((String.class.getClassLoader())));
//    }
//
//    public DogsModel() {
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeValue(message);
//        dest.writeValue(status);
//    }
//
//    public int describeContents() {
//        return 0;
//    }
//
//}
