package com.example.kyrgyzpedigree;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable{

    public static final String TABLE_NAME = "persons";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_MESTOJITELSTVA= "mestojitelstva";
    public static final String COLUMN_GODROJDENIYA = "godrojdeniya";
    public static final String COLUMN_ROD = "rod";
    public static final String COLUMN_PODROD = "podrod";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_EMAIL + " TEXT,"
                    + COLUMN_MESTOJITELSTVA + " TEXT,"
                    + COLUMN_GODROJDENIYA + " TEXT,"
                    + COLUMN_ROD + " TEXT,"
                    + COLUMN_PODROD + " TEXT"
                    + ")";

    private int id;
    private String name;
    private String email;
    private String mestojitelstva;
    private String godrojdeniya;
    private String rod;
    private String podrod;

    public Person() {
    }

    public Person(int id, String name, String email, String mestojitelstva, String godrojdeniya, String rod, String podrod) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mestojitelstva = mestojitelstva;
        this.godrojdeniya = godrojdeniya;
        this.rod = rod;
        this.podrod = podrod;
    }

    public Person(String name, String email, String mestojitelstva, String godrojdeniya, String rod, String podrod) {
        this.name = name;
        this.email = email;
        this.mestojitelstva = mestojitelstva;
        this.godrojdeniya = godrojdeniya;
        this.rod = rod;
        this.podrod = podrod;
    }

    protected Person(Parcel in) {
        id = in.readInt();
        name = in.readString();
        email = in.readString();
        mestojitelstva = in.readString();
        godrojdeniya = in.readString();
        rod = in.readString();
        podrod = in.readString();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(mestojitelstva);
        dest.writeString(godrojdeniya);
        dest.writeString(rod);
        dest.writeString(podrod);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMestojitelstva() {
        return mestojitelstva;
    }

    public void setMestojitelstva(String mestojitelstva) {
        this.mestojitelstva = mestojitelstva;
    }

    public String getGodrojdeniya() {
        return godrojdeniya;
    }

    public void setGodrojdeniya(String godrojdeniya) {
        this.godrojdeniya = godrojdeniya;
    }

    public String getRod() {
        return rod;
    }

    public void setRod(String rod) {
        this.rod = rod;
    }

    public String getPodrod() {
        return podrod;
    }

    public void setPodrod(String podrod) {
        this.podrod = podrod;
    }

    @Override
    public String toString() {
        return
                        "ID=" + id +
                        "\n"+"ФИО: " + name +
                                "\n"+"Почта: " + email +
                        "\n"+"Адрес: " + mestojitelstva +
                        "\n"+"Год рождения: " + godrojdeniya +
                        "\n"+"Род: " + rod +
                        "\n"+"Подрод: " + podrod;
    }
}
