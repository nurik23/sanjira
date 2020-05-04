package kg.sanjyra.util.startPersonFilling;

public class PersonDto {
    private String fio;
    private String birthDate;
    private String place;
    private String mail;
    private String dadName;
    private String momName;
    private boolean female;

    public boolean isFemale() {
        return female;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public String getDadName() {
        return dadName;
    }

    public void setDadName(String dadName) {
        this.dadName = dadName;
    }

    public String getMomName() {
        return momName;
    }

    public void setMomName(String momName) {
        this.momName = momName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public PersonDto() {
    }

    public PersonDto(String fio) {
        this.fio = fio;
    }

    public PersonDto(String fio, String birthDate, String place) {
        this.fio = fio;
        this.birthDate = birthDate;
        this.place = place;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "fio='" + fio + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", place='" + place + '\'' +
                ", mail='" + mail + '\'' +
                ", dadName='" + dadName + '\'' +
                ", momName='" + momName + '\'' +
                ", female=" + female +
                '}';
    }
}
