package library.persons;

import library.interfaces.Searchable;
import library.items.LibraryItem;

import java.util.ArrayList;
import java.util.List;

public abstract class Reader implements Searchable {

    protected String name;
    protected String surname;
    protected String readerTicket;
    protected List<LibraryItem> borrowedItems;

    public Reader(String name, String surname, String readerTicket) {
        setName(name);
        setSurname(surname);
        setReaderTicket(readerTicket);
        this.borrowedItems = new ArrayList<>();
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Не передано имя читателя");
        }
        this.name = name;
    }

    public void setSurname(String surname) {
        if (surname.isEmpty()) {
            throw new IllegalArgumentException("Не передана фамилия читателя");
        }
        this.surname = surname;
    }

    public void setReaderTicket(String readerTicket) {
        if (readerTicket.isEmpty()) {
            throw new IllegalArgumentException("Не передан номер читательского билета");
        }
        this.readerTicket = readerTicket;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getReaderTicket() {
        return readerTicket;
    }

    @Override
    public boolean matches(String surname) {
        return this.surname.equalsIgnoreCase(surname);
    }

    public abstract void borrowItem(LibraryItem item);
    public abstract void returnItem(LibraryItem item);
    public abstract List<LibraryItem> getBorrowedItems();

    @Override
    public String toString() {
        return "Reader{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", readerTicket='" + getReaderTicket() + '\'' +
                ", borrowedItems=" + borrowedItems +
                '}';
    }
}
