package library.persons;

import library.items.LibraryItem;

import java.util.List;

public class Professor extends Reader{

    private final String department;
    public Professor(String name, String surname, String readerTicket, String department) {
        super(name, surname, readerTicket);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public void borrowItem(LibraryItem item) {
        if (item.isAvailable()) {
            borrowedItems.add(item);
        }
    }

    @Override
    public void returnItem(LibraryItem item) {
        borrowedItems.remove(item);
    }

    @Override
    public List<LibraryItem> getBorrowedItems() {
        return borrowedItems;
    }

    @Override
    public String toString() {
    return "Professor{" +
                "department='" + department + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", readerTicket='" + readerTicket + '\'' +
                ", borrowedItems=" + borrowedItems +
                '}';
    }
}
