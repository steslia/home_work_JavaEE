package profile.entity;

public class Human {
    private String name;
    private String surname;
    private Integer age;
    private String sex;
    private String country;

    public Human(String name, String surname, Integer age, String sex, String country) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Анкета: " +
                "имя: " + name +
                ", фамилия: " + surname +
                ", возраст: " + age +
                ", пол: " + sex;
    }
}
