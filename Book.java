package library.items;

import library.persons.Reader;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Book extends LibraryItem {

    protected String author;
    protected String ibsn;

    public Book(String title, int year, boolean available, String author, String ibsn,
                double cost) {
        super(title, year, available, cost);
        setAuthor(author);
        setIbsn(ibsn);
    }

    public void setAuthor(String author) {
        if (author.isEmpty()) {
            throw new IllegalArgumentException("Не введён автор");
        }
        this.author = author;
    }

    public void setIbsn(String ibsn) {
        if (ibsn.isEmpty()) {
            throw new IllegalArgumentException("Не введён ibsn");
        }
        this.ibsn = ibsn;
    }

    public String getAuthor() {
        return author;
    }

    public String getIbsn() {
        return ibsn;
    }

    public String getBookDetails() {
        return "title: " + title + ", year: " + year + ", author: " + author + "ibsn: " + ibsn;
    }

    @Override
    public boolean matches(String author) {
        return this.author.equalsIgnoreCase(author);
    }

    @Override
    public double calculateFineForDays(LocalDate borrowDate, LocalDate returnDate) {

        long daysOverdue;
        double fine = 0;

        // Вычисление количества дней просрочки
        Period period = Period.between(borrowDate, returnDate);
        daysOverdue = period.getDays() + 31L * period.getMonths() + 365L * period.getYears();

        if (daysOverdue > fixedCountDayReturnItem) {
            fine = calculateFine(daysOverdue);
        }
        return fine;
    }

    @Override
    public double calculateFine(long daysOverdue) {
        return (daysOverdue - fixedCountDayReturnItem) * fixedPenaltyPercentage * cost;
    }


    @Override
    public void borrowItem(Reader reader) {
        if (isAvailable()) {
            borrowers.add(reader);
            borrowDate = LocalDate.now();
            available = false;
        }
    }

    @Override
    public void returnItem() {
        returnDate = LocalDate.now();
        available = true;
    }

    @Override
    public List<Reader> getBorrowers() {
        return borrowers;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", ibsn='" + ibsn + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", available=" + available +
                ", cost=" + cost +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
