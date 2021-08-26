package balaji.hibernate.associationmapping.onetomany;

import org.springframework.data.repository.CrudRepository;

public interface PhoneNumberManyToOneRepo extends CrudRepository<PhoneNumberManyToOne, Integer> {
}
