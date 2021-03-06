package cz.fi.muni.pa165.dogbarber.service;

import cz.fi.muni.pa165.dogbarber.dao.CustomerDao;
import cz.fi.muni.pa165.dogbarber.entity.Customer;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.exception.DogBarberException;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.Set;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Penaz
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    
	@Autowired
    private CustomerDao customerDao;

    @Autowired
    private DogService dogService;

    @Override
    public Customer findById(Long Id) {
        return customerDao.findById(Id);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.getAllCustomers();
    }

    @Override
    public void addDog(Dog dog, Customer customer) {
        if (customer.getAllDogs().contains(dog)) {
            throw new DogBarberException("Customer already has this dog. Customer: " + customer.getId() + ", dog: " + dog.getId());
        } else {
            customer.addDog(dog);
            customerDao.updateCustomer(customer);
        }
    }

    @Override
    public void removeDog(Dog dog, Customer customer) {
        if (customer.getAllDogs().contains(dog)) {
            customer.removeDog(dog);
            customerDao.updateCustomer(customer);
        } else {
            throw new DogBarberException("Customer doesnt own this dog. Customer: " + customer.getId() + ", dog: " + dog.getId());
        }
    }

    @Override
    public void deleteCustomer(Customer c) {
        customerDao.deleteCustomer(c);
    }

    @Override
    public void registerCustomer(Customer c, String password) {
        c.setPassword(createHash(password));
        customerDao.createCustomer(c);
    }

    @Override
    public boolean authenticate(Customer c, String password) {
        return validatePassword(password, c.getPassword());
    }

    @Override
    public double getTotalPrice(Customer customer) {
        double price = 0;
        
        Set<Dog> dogs = customerDao.findById(customer.getId()).getAllDogs();
        for (Dog d : dogs) {
            double priceDec = dogService.getTotalPrice(d.getId());
            price +=priceDec;
        }
        return price;
    }

    //see  https://crackstation.net/hashing-security.htm#javasourcecode
    private static String createHash(String password) {
        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 24;
        final int PBKDF2_ITERATIONS = 1000;
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validatePassword(String password, String correctHash) {
        if (password == null) {
            return false;
        }
        if (correctHash == null) {
            throw new IllegalArgumentException("password hash is null");
        }
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }

    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }

    @Override
    public Customer findByEmail(String email) {
        return customerDao.findByEmail(email);
    }

    @Override
    public Customer update(Customer cust) {
        return customerDao.updateCustomer(cust);
    }
}
