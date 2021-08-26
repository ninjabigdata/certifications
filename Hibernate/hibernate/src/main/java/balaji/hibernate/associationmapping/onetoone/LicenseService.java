package balaji.hibernate.associationmapping.onetoone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class LicenseService {

    @Autowired
    private LicenseRepo repo;

    public License save(License license) {
        return  repo.save(license);
    }

    public License get(Integer id) {
        return repo.findById(id).orElseGet(null);
    }

    @PostConstruct
    public void test() {
        System.out.println("From LicenseService OneToOne Mapping");

        License license = new License();
        license.setType("car");
        license.setValidFrom(new Date());
        license.setValidTo(new Date());
        Person person = new Person();
        person.setFirstName("Balaji");
        person.setLastName("S");
        person.setAge(28);
        license.setPerson(person);

        System.out.println("Saved license is " + save(license));

        System.out.println("License retrieved is " +  get(license.getId()));
    }

}
