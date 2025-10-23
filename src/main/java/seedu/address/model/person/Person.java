package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.product.Product;

/**
 * Represents a Person in ClientHub.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Company company;

    // Data fields
    private final Address address;
    private final Status status;
    private final Set<Product> products = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Company company, Email email, Address address, Status status,
            Set<Product> products) {
        requireAllNonNull(name, phone, company, email, address, status, products);
        this.name = name;
        this.phone = phone;
        this.company = company;
        this.email = email;
        this.address = address;
        this.status = status;
        this.products.addAll(products);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Company getCompany() {
        return company;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Returns an immutable product set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && company.equals(otherPerson.company)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && status.equals(otherPerson.status)
                && products.equals(otherPerson.products);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, company, email, address, status, products);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("company", company)
                .add("email", email)
                .add("address", address)
                .add("status", status)
                .add("products", products)
                .toString();
    }

}
