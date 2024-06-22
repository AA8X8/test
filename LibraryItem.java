package library.items;

import library.interfaces.Searchable;
import library.persons.Reader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class LibraryItem implements Searchable {

    protected String title;
    protected int year;
    protected boolean available;
    protected List<Reader> borrowers;
    protected final double cost;
    protected LocalDate borrowDate;
    protected LocalDate returnDate;

    protected static int fixedCountDayReturnItem = 31;
    protected static double fixedPenaltyPercentage = 0.01;

    public LibraryItem(String title, int year, boolean available, double cost) {
        setTitle(title);
        setYear(year);
        this.available = available;
        this.borrowers = new ArrayList<>();
        this.cost = cost;
    }

    public void setTitle(String title) {
        if (title.isEmpty()) {
            throw new IllegalArgumentException("Не передано название книги");
        }
        this.title = title;
    }

    public void setYear(int year) {
        if (year < 1445 || year > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Неверно указан год издания книги");
        }
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public boolean isAvailable() {
        return available;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }

    public double getCost() {
        return cost;
    }
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    @Override
    public boolean matches(String title) {
        return this.title.equalsIgnoreCase(title);
    }

    public abstract double calculateFineForDays(LocalDate borrowDate, LocalDate returnDate);
    public abstract double calculateFine(long daysOverdue);
    public abstract void borrowItem(Reader reader);
    public abstract void returnItem();
    public abstract List<Reader> getBorrowers();

    @Override
    public String toString() {
        return "LibraryItem{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", available=" + available +
                ", borrowers=" + getBorrowers() +
                '}';
    }
}
