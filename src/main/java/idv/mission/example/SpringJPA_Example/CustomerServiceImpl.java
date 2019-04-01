package idv.mission.example.SpringJPA_Example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    public void save(Customer customer) {
        repository.save(customer);
    }

    public void save(String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName);
        save(customer);
    }

    public Customer findOne(long id) {
        return repository.findOne(id);
    }

}
