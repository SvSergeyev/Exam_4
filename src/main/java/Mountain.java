import javax.persistence.Column;
import javax.persistence.Entity;

/*
3. Получение Гор по названию страны
*/
@Entity
public class Mountain extends BaseIdentify {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private int height;

    public Mountain() {
    }

    public Mountain(String name, String country, int height) {
        setName(name);
        setCountry(country);
        setHeight(height);
    }

    public void setName(String name) {
        if (name != null && name.length() >= 4) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Название горы не может быть короче четырех символов.");
        }
    }

    public void setCountry(String country) {
        if (country != null && country.length() >= 4) {
            this.country = country.toLowerCase();
        } else {
            throw new IllegalArgumentException("Название страны не может быть короче четырех символов.");
        }
    }

    public void setHeight(int height) {
        if (height >= 100) {
            this.height = height;
        } else {
            throw new IllegalArgumentException("Высота не может быть меньше 100 метров.");
        }
    }

    @Override
    public String toString() {
        return "Mountain{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", height=" + height +
                '}';
    }
}
