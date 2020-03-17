package com.example.kyrgyzpedigree;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

public class Person implements Parcelable {

    public static final String TABLE_NAME = "persons";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_MESTOJITELSTVA = "mestojitelstva";
    public static final String COLUMN_GODROJDENIYA = "godrojdeniya";
    public static final String COLUMN_NAME_DAD = "namedad";
    public static final String COLUMN_NAME_MOM = "namemom";
    public static final String COLUMN_PODROD = "podrod";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_EMAIL + " TEXT,"
                    + COLUMN_MESTOJITELSTVA + " TEXT,"
                    + COLUMN_GODROJDENIYA + " TEXT,"
                    + COLUMN_NAME_DAD + " TEXT,"
                    + COLUMN_NAME_MOM + " TEXT,"
                    + COLUMN_PODROD + " TEXT"
                    + ")";

    private int id;
    private String name;
    private String email;
    private String mestojitelstva;
    private String godrojdeniya;
    private String namedad;
    private String namemom;
    private String podrod;

    public Person() {
    }

    public Person(int id, String name, String email, String mestojitelstva, String godrojdeniya, String namedad, String namemom, String podrod) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mestojitelstva = mestojitelstva;
        this.godrojdeniya = godrojdeniya;
        this.namedad = namedad;
        this.namemom = namemom;
        this.podrod = podrod;
    }

    public Person(String name, String email, String mestojitelstva, String godrojdeniya, String namedad, String namemom, String podrod) {
        this.name = name;
        this.email = email;
        this.mestojitelstva = mestojitelstva;
        this.godrojdeniya = godrojdeniya;
        this.namedad = namedad;
        this.namemom = namemom;
        this.podrod = podrod;
    }


    public Person(Map<String, Object> map) {
        this.id = (int) map.get("id");
        this.name = (String) map.get("name");
        this.email = (String) map.get("email");
        this.mestojitelstva = (String) map.get("mestojitelstva");
        this.godrojdeniya = (String) map.get("godrojdeniya");
        this.namedad = (String) map.get("namedad");
        this.namemom = (String) map.get("namemom");
    }

    protected Person(Parcel in) {
        id = in.readInt();
        name = in.readString();
        email = in.readString();
        mestojitelstva = in.readString();
        godrojdeniya = in.readString();
        namedad = in.readString();
        namemom = in.readString();
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
        dest.writeString(namedad);
        dest.writeString(namemom);
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

    public String getNamedad() {
        return namedad;
    }

    public void setNamedad(String namedad) {
        this.namedad = namedad;
    }

    public String getNamemom() {
        return namemom;
    }

    public void setNamemom(String namemom) {
        this.namemom = namemom;
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
                "ID : " + id +
                        "\n" + "Full name: " + name +
                        "\n" + "Email : " + email +
                        "\n" + "Address : " + mestojitelstva +
                        "\n" + "Year of birth : " + godrojdeniya +
                        "\n" + "Dad's name : " + namedad +
                        "\n" + "Mom's name : " + namemom +
                        "\n" + "Podrod : " + podrod;
    }
}
