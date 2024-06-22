package library.persons;

import library.items.LibraryItem;

import java.util.List;

public class Student extends Reader{

    private final String studentId;
    public Student(String name, String surname, String readerTicket, String studentId) {
        super(name, surname, readerTicket);
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
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
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", readerTicket='" + readerTicket + '\'' +
                ", borrowedItems=" + borrowedItems +
                '}';
    }
}
