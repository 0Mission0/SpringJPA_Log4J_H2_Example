package idv.mission.example.SpringJPA_Example;

public interface CustomerService {
    public void save(Customer customer);

    public void save(String firstName, String lastName);

    public Customer findOne(long id);
}
