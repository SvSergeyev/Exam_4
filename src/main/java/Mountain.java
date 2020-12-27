import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/*
3. Получение Гор по названию страны
*/
@Entity
@NamedQueries({
        @NamedQuery(name = "Mountain.getByCountry", query = "SELECT m FROM Mountain m WHERE m.country = :country")
})
public class Mountain extends BaseIdentify {
    /*
    ####Гора:
 - название (не менее 4 символов);
 - страна (не менее 4 символов);
 - высота (не менее 100 метров);
     */
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
}
