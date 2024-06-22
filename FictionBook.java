package library.items;

public class FictionBook extends Book {

    private String genre;
    public FictionBook(String title, int year, boolean available,
                       String author, String ibsn,
                       String genre, double cost) {
        super(title, year, available, author, ibsn, cost);
        setGenre(genre);
    }

    public void setGenre(String genre) {
        if (genre.isEmpty()) {
            throw new IllegalArgumentException("Не передан жанр книги");
        }
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String getBookDetails() {
        return super.getBookDetails() + ", genre: " + genre;
    }
}
